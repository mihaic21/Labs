package edu.cs.ubbcluj.ro.utils;


import edu.cs.ubbcluj.ro.model.Concession;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hwpf.HWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ConcessionWriter {

    public static void writeConcession(Concession c, String path, String templateFile){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFile));
            HWPFDocument doc = new HWPFDocument(fs);
            Map<String, String> paramsMap = prepareParameters(c);
            List<String> names = new ArrayList<>(paramsMap.keySet());
            for (String name : names)
                doc = replaceText(doc, name, paramsMap.get(name));
            saveWord(path, doc);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> prepareParameters(Concession c) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(c.getDate());
        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("$con-nr", c.getNumber().toString());
        resultMap.put("$day", Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
        resultMap.put("$mon", Integer.toString(cal.get(Calendar.MONTH)));
        resultMap.put("$year", Integer.toString(cal.get(Calendar.YEAR)));
        resultMap.put("$con-name", c.getOwner().getLastName() + " " + c.getOwner().getFirstName());
        resultMap.put("$con-cnp", Integer.toString(c.getOwner().getId()));
        resultMap.put("$con-address", c.getOwner().getAddress());
        resultMap.put("$graveyard", c.getGrave().getParcel().getGraveyard().getName());
        resultMap.put("$parcel", Integer.toString(c.getGrave().getParcel().getNumber()));
        resultMap.put("$grave", c.getGrave().getNumber().toString());
        resultMap.put("$surface", Float.toString(c.getGrave().getSurface()));

        if (c.getReceipts().size() > 0) {
            cal.setTime(c.getReceipts().get(0).getEndingDate());
            resultMap.put("$receipt-end", Integer.toString(cal.get(Calendar.YEAR)));
            resultMap.put("$expire", cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH));
            cal.setTime(c.getReceipts().get(0).getStartingDate());
            resultMap.put("$receipt-start", Integer.toString(cal.get(Calendar.YEAR)));
            resultMap.put("$receipt-nr", c.getReceipts().get(0).getReceiptNumber().toString());
            resultMap.put("$receipt-day", Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
            resultMap.put("$receipt-mon", Integer.toString(cal.get(Calendar.MONTH)));
        }
        return resultMap;
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
        Range r1 = doc.getRange();

        for (int i = 0; i < r1.numSections(); ++i ) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    private static void saveWord(String filePath, HWPFDocument doc) throws IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            doc.write(out);
        }
        finally{
            if (out != null)
                out.close();
        }
    }
}
