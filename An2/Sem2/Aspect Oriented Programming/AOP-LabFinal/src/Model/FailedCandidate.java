package Model;

import java.util.ArrayList;

/**
 * Created by mihai on 20.05.2014.
 */
public class FailedCandidate extends Candidate {

    public FailedCandidate(String cnp, String name, String address, double bacAverage, double highSchoolAverage, ArrayList<String> sections) {
        super(cnp, name, address, bacAverage, highSchoolAverage, sections);
    }

    @Override
    public String toString() {
        return this.getCnp() + ";" + this.getName();
    }
}
