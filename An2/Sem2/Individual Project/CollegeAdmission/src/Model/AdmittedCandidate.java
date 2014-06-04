package Model;

import java.util.ArrayList;

/**
 * Created by mihai on 20.05.2014.
 */
public class AdmittedCandidate extends Candidate {

    private String finalSection;

    public AdmittedCandidate(String cnp, String name, String address, double bacAverage, double highSchoolAverage, ArrayList<String> options, String finalSection) {
        super(cnp, name, address, bacAverage, highSchoolAverage, options);
        this.finalSection = finalSection;
    }

    public String getFinalSection() {
        return finalSection;
    }

    @Override
    public String toString() {
        return this.getCnp() + ";" + this.getName() + ";" + this.finalSection;
    }
}
