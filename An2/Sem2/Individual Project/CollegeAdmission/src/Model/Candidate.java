package Model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by mihai on 19.05.2014.
 */
public class Candidate{

    private String cnp;
    private String name;
    private String address;
    private double bacAverage;
    private double highSchoolAverage;
    private ArrayList<String> sections;

    public Candidate() {
    }

    public Candidate(String cnp, String name, String address, double bacAverage, double highSchoolAverage, ArrayList<String> sections) {
        this.cnp = cnp;
        this.name = name;
        this.address = address;
        this.bacAverage = bacAverage;
        this.highSchoolAverage = highSchoolAverage;
        this.sections = sections;
    }

    public double getFinalGrade(){
        return (this.bacAverage + this.highSchoolAverage)/2;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBacAverage() {
        return bacAverage;
    }

    public void setBacAverage(double bacAverage) {
        this.bacAverage = bacAverage;
    }

    public double getHighSchoolAverage() {
        return highSchoolAverage;
    }

    public void setHighSchoolAverage(double highSchoolAverage) {
        this.highSchoolAverage = highSchoolAverage;
    }


    public ArrayList<String> getSections() {
        return sections;
    }

    public void setSections(ArrayList<String> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        String stringOptions = "";

        for (int counter = 0; counter < sections.size()-1; counter++){
            stringOptions += sections.get(counter);
            stringOptions += ",";
        }

        stringOptions += sections.get(sections.size() - 1);
        return cnp + ";" +
                name + ";" +
                address + ";" +
                bacAverage + ";" +
                highSchoolAverage + ";" +
                stringOptions;
    }

    public static Comparator<Candidate> candidateComparator = new Comparator<Candidate>() {
        @Override
        public int compare(Candidate candidate, Candidate candidate2) {
            if (candidate.getFinalGrade() < candidate2.getFinalGrade()){
                return 1;
            } else {
                return -1;
            }
        }
    };
}
