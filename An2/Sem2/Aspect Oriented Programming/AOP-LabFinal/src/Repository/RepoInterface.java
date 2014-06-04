package Repository;

import Model.Candidate;
import Model.Section;

import java.util.ArrayList;

/**
 * Created by mihai on 19.05.2014.
 */
public interface RepoInterface {

    public ArrayList getCandidateList();
    public ArrayList getSectionList();
    public ArrayList getAdmittedCandidates();
    public ArrayList getFailedCandidates();
    public void setAdmittedCandidates(ArrayList list);
    public void setFailedCandidates(ArrayList list);
    public void addCandidate(Candidate candidate);
    public void addSection(Section section);
    public void removeCandidate(Candidate candidate);
    public void removeSection(Section section);
    public ArrayList getCandidatesContaining(String name, ArrayList list);
    public void loadCandidatesFromFile(String fileName);
    public void loadSectionsFromFile(String fileName);
    public void writeListToFile(ArrayList list, String fileName);

}
