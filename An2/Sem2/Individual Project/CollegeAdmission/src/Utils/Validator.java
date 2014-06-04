package Utils;

import Model.Candidate;
import Model.Section;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by mihai on 19.05.2014.
 */
public class Validator {

    public static void validateCandidate(Candidate candidate, ArrayList<Candidate> candidatesList, ArrayList<Section> optionsList) throws MyException {

        if (candidate.getCnp().length() != 13){
            throw new MyException("Invalid CNP");
        }
        try{
            Double.parseDouble(candidate.getCnp());
        } catch (NumberFormatException e){
            throw new MyException("CNP must only contain digits");
        }
        if (candidate.getName().length() < 1){
            throw new MyException("Invalid name");
        }
        if (candidate.getAddress().length() < 1){
            throw new MyException("Invalid address");
        }
        if ((candidate.getBacAverage() > 10) || (candidate.getBacAverage() < 1)){
            throw new MyException("Invalid bac average");
        }
        if ((candidate.getHighSchoolAverage() > 10) || (candidate.getHighSchoolAverage() < 1)){
            throw new MyException("Invalid high school average");
        }
        if (candidate.getSections().size() < 1){
            throw new MyException("No options selected");
        }

        for (String option : candidate.getSections()){
            boolean found = false;
            for (Section section : optionsList){
                if (section.getName().equalsIgnoreCase(option)){
                    found = true;
                }
            }
            if (!found){
                throw new MyException("Invalid option");
            }
        }

        for (int i = 0; i < candidate.getSections().size() - 1; i++){
            for (int j = i+1; j < candidate.getSections().size() - 1; j++){
                if (candidate.getSections().get(i) == candidate.getSections().get(j)){
                    throw new MyException("An option must only be selected once");
                }
            }
        }

        for (Candidate cand : candidatesList){
            if (cand.getCnp().equals(candidate.getCnp())){
                throw new MyException("Candidate already in");
            }
        }

    }

    public static void validateSection(Section section, ArrayList<Section> sectionsList) throws MyException {

        if (section.getName().length() < 1){
            throw new MyException("Invalid name");
        }

        if (section.getPositions() < 1){
            throw new MyException("Invalid number of positions");
        }

        for (Section section1 : sectionsList){
            if (section.getName().equals(section1.getName())){
                throw new MyException("Section already exists");
            }
        }

    }

}
