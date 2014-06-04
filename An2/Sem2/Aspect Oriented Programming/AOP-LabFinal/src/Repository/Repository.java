package Repository;

import java.io.*;
import java.util.ArrayList;

import Model.Candidate;
import Model.Section;
import Utils.MyException;
import Utils.Validator;

/**
 * Created by mihai on 19.05.2014.
 */
public class Repository implements RepoInterface {

    private ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
    private ArrayList<Section> sectionsList = new ArrayList<Section>();

    private ArrayList<Candidate> admittedCandidates = new ArrayList<Candidate>();
    private ArrayList<Candidate> failedCandidates = new ArrayList<Candidate>();

    private String candidatesFileName;
    private String sectionsFileName;
    private String admittedFileName;
    private String failedFileName;


    public Repository(String candidatesFileName, String sectionsFileName, String admittedFileName, String failedFileName) {
        this.candidatesFileName = candidatesFileName;
        this.sectionsFileName = sectionsFileName;
        this.admittedFileName = admittedFileName;
        this.failedFileName = failedFileName;

        this.loadSectionsFromFile(this.sectionsFileName);
        this.loadCandidatesFromFile(this.candidatesFileName);
    }

    @Override
    public ArrayList getCandidateList() {
        return this.candidatesList;
    }

    @Override
    public ArrayList getSectionList() {
        return this.sectionsList;
    }

    @Override
    public ArrayList getAdmittedCandidates() {
        return this.admittedCandidates;
    }

    @Override
    public ArrayList getFailedCandidates() {
        return this.failedCandidates;
    }

    @Override
    public void setAdmittedCandidates(ArrayList list) {
        this.admittedCandidates = new ArrayList<Candidate>(list);
        this.writeListToFile(admittedCandidates, admittedFileName);
    }

    @Override
    public void setFailedCandidates(ArrayList list) {
        this.failedCandidates = new ArrayList<Candidate>(list);
        this.writeListToFile(failedCandidates, failedFileName);
    }

    @Override
    public void addCandidate(Candidate candidate) {
        this.candidatesList.add(candidate);
        this.writeListToFile(this.candidatesList, this.candidatesFileName);
    }

    @Override
    public void addSection(Section section) {
        this.sectionsList.add(section);
        this.writeListToFile(this.sectionsList, this.sectionsFileName);
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        ArrayList<Candidate> newCandidatesList = new ArrayList<Candidate>();
        for (Candidate cand : this.candidatesList){
            if (!cand.getCnp().equals(candidate.getCnp())){
                newCandidatesList.add(cand);
            }
        }
        this.candidatesList = new ArrayList<Candidate>(newCandidatesList);
    }

    @Override
    public void removeSection(Section section) {
        ArrayList<Section> newSectionsList = new ArrayList<Section>();
        for (Section sect : this.sectionsList){
            if (!sect.getName().equals(section.getName())){
                newSectionsList.add(sect);
            }
        }
        this.sectionsList = new ArrayList<Section>(newSectionsList);
    }

    @Override
    public ArrayList getCandidatesContaining(String name, ArrayList list) {
        ArrayList<Candidate> finalList = new ArrayList<Candidate>();

        ArrayList<Candidate> candidates = new ArrayList<Candidate>(list);

        for (Candidate candidate : candidates){

            if (candidate.getName().toLowerCase().contains(name.toLowerCase())){
                finalList.add(candidate);
            }

        }

        return finalList;
    }

    @Override
    public void loadCandidatesFromFile(String fileName) {

        try{
            ArrayList<Candidate> newCandidatesList = new ArrayList<Candidate>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(";");
                String cnp = tokens[0];
                String name = tokens[1];
                String address = tokens[2];
                double bacAverage = Double.parseDouble(tokens[3]);
                double highSchoolAverage = Double.parseDouble(tokens[4]);
                String allOptions = tokens[5];
                String[] stringOptions = allOptions.split(",");
                ArrayList<String> options = new ArrayList<String>();
                for (String string : stringOptions){
                    options.add(string);
                }
                Candidate candidate = new Candidate(cnp, name, address, bacAverage, highSchoolAverage, options);
                Validator.validateCandidate(candidate, this.candidatesList, this.sectionsList);
                newCandidatesList.add(candidate);
                this.candidatesList = new ArrayList<Candidate>(newCandidatesList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadSectionsFromFile(String fileName) {

        try{
            ArrayList<Section> newSectionsList = new ArrayList<Section>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(";");
                String name = tokens[0];
                int positions = Integer.parseInt(tokens[1]);
                Section section = new Section(name, positions);
                Validator.validateSection(section, this.sectionsList);
                newSectionsList.add(section);
            }
            this.sectionsList = new ArrayList<Section>(newSectionsList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeListToFile(ArrayList list, String fileName) {

        try{
            FileWriter fileWriter = new FileWriter(fileName);
            for (Object object : list){
                fileWriter.write(object.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
