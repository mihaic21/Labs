package Controller;

import Model.Symbol;
import Utils.CodificationDefinitions;
import Model.SymbolTable;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shobo on 20.10.2014.
 */
public class Analyser {

    private String sourceFileName = "program.txt";
    private String errorsFileName = "errors.txt";
    private String pifFileName = "pif.txt";
    private String identifiersSTFileName = "identifiers.txt";
    private String constantsSTFileName = "constants.txt";

    private CodificationDefinitions codificationDefinitions;

    private SymbolTable identifiersSymbolTable;
    private SymbolTable constantsSymbolTable;


    public Analyser() {

        this.codificationDefinitions = new CodificationDefinitions();

        this.identifiersSymbolTable = new SymbolTable();
        this.constantsSymbolTable = new SymbolTable();

        this.cleanAllFiles();

        this.run();

    }

    private void run(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFileName));
            String line;
            int lineNo = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineNo ++;
                String newLine = this.organizeLineWithSpaces(line);
                newLine = this.deleteMultipleSpaces(newLine);
                if (!this.parseLine(newLine)){
                    String info = String.format("Lexical error at line %d", lineNo);
                    appendLineInFile(info, errorsFileName);
                }
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String organizeLineWithSpaces(String startingLine){

        String line = this.replaceTabsWithSpaces(startingLine);

        String newCode = new String();
        String finalCode = new String();
        int i = 0;

        //  add spaces before and after separators
        while (i < line.length()){

            if (codificationDefinitions.isSeparator(line.charAt(i))){
                newCode += " ";
                newCode += line.charAt(i);
                newCode += " ";
            } else {
                newCode += line.charAt(i);
            }
            i++;
        }

        i = 0;

        //  add spaces before and after operators
        while (i < newCode.length()){
            String operator;
            if (i == newCode.length() - 1){
                operator = new String();
                operator += newCode.charAt(i);
                if (codificationDefinitions.isOperator(operator)){
                    finalCode += " ";
                    finalCode += operator;
                } else {
                    finalCode += newCode.charAt(i);
                }
            } else {
                operator = new String();
                operator += newCode.charAt(i);
                operator += newCode.charAt(i + 1);
                if (codificationDefinitions.isOperator(operator)){
                    finalCode += " ";
                    finalCode += operator;
                    finalCode += " ";
                    i++;
                } else {
                    operator = new String();
                    operator += newCode.charAt(i);
                    if (codificationDefinitions.isOperator(operator)){
                        finalCode += " ";
                        finalCode += operator;
                        finalCode += " ";
                    } else {
                        finalCode += newCode.charAt(i);
                    }
                }
            }
            i++;
        }

        return finalCode.toString();
    }

    private String replaceTabsWithSpaces(String code){

        String finalCode = new String(code);

        finalCode.replaceAll("[\\t]"," ");

        return finalCode;

    }

    private String deleteMultipleSpaces(String line){

        if (line.length() < 1){
            return null;
        }

        String finalCode = new String();

        int i = 1;

        if (line.charAt(0) != ' '){
            finalCode += line.charAt(0);
        }

        while (i < line.length()) {

            if (i > 0){
                if ((line.charAt(i) != ' ') || ((line.charAt(i) == ' ') && (line.charAt(i - 1) != ' '))) {
                    finalCode += line.charAt(i);
                }
            } else {
                finalCode += line.charAt(i);
            }

            i++;
        }

        return finalCode;
    }

    private Boolean parseLine(String line){

        String[] tokens = line.split(" ");

        for (String token : tokens){

            if (codificationDefinitions.codeForString(token) == null){

                if (this.identifiersSymbolTable.getSymbolForIdentifier(token) == null){
                    //  it isn't in the identifiers table => is it an identifier or not?

                    if (this.isValidIdentifier(token)){
                        //  add to identifierSymbolTable and write to pif and identifierST

                        Symbol symbol = this.identifiersSymbolTable.addIdentifier(token);
                        String info = String.format("%d %s", symbol.getCode(), symbol.getIdentifier());
                        this.appendLineInFile(info, identifiersSTFileName);
                        info = String.format("%s %d %d", symbol.getIdentifier(), 0, symbol.getCode());
                        this.appendLineInFile(info, pifFileName);

                    } else {

                        if (this.constantsSymbolTable.getSymbolForIdentifier(token) == null){
                            //  isn't in the constants table => is it a constant or not?

                            if (this.isValidConstant(token)){
                                //  add to constantsSymbolTable and write to pif and constantsST

                                Symbol symbol = this.constantsSymbolTable.addIdentifier(token);
                                String info = String.format("%d %s", symbol.getCode(), symbol.getIdentifier());
                                this.appendLineInFile(info, constantsSTFileName);
                                info = String.format("%s %d %d", symbol.getIdentifier(), 1, symbol.getCode());
                                this.appendLineInFile(info, pifFileName);

                            } else {
                                //  return if lexical error is found; nothing else on the line will be parsed
                                return false;
                            }
                        } else {
                            String info = String.format("%s %d %d", token, 1,
                                    this.constantsSymbolTable.getSymbolForIdentifier(token).getCode());
                            this.appendLineInFile(info, pifFileName);
                        }
                    }

                } else {
                    String info = String.format("%s %d %d", token, 0,
                            this.identifiersSymbolTable.getSymbolForIdentifier(token).getCode());
                    this.appendLineInFile(info, pifFileName);
                }

            } else {
                //  write the code to pif (codificationTableCode symbolTablePosition)
                String info = String.format("%s %d %d", token, codificationDefinitions.codeForString(token), -1);
                this.appendLineInFile(info, pifFileName);

            }
        }
        return true;
    }

    private Boolean isValidIdentifier(String symbol){

        if (symbol.length() > 250){
            return false;
        }
        String regex = "(\\s|^)([a-zA-Z]+[0-9a-zA-Z]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(symbol);
        if (!matcher.matches()){
            return false;
        }

        return true;
    }

    private Boolean isValidConstant(String symbol){

        String regex = "((\\s|^)([+-]?[1-9]+[0-9]*))|((\\s|^)([+-]?[0-9]+\\.[0-9]+[1-9]))|((\\s|^)0)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(symbol);
        if (!matcher.matches()){
            return false;
        }

        return true;
    }

    private void appendLineInFile(String line, String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(line);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cleanAllFiles(){
        try {
            FileWriter fileWriter = new FileWriter(constantsSTFileName);
            fileWriter.close();
            fileWriter = new FileWriter(identifiersSTFileName);
            fileWriter.close();
            fileWriter = new FileWriter(pifFileName);
            fileWriter.close();
            fileWriter = new FileWriter(errorsFileName);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
