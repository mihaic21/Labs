package Controller;

import Model.Candidate;
import Repository.RepoInterface;
import Utils.MyException;

import java.util.ArrayList;

/**
 * Created by mihai on 27.05.2014.
 */
public class CandidateController {

    private RepoInterface repository;

    public CandidateController(RepoInterface repository) {
        this.repository = repository;
    }

    public ArrayList<Candidate> getAllCandidates(){
        return this.repository.getCandidateList();
    }

    public ArrayList<Candidate> getAllAdmittedCandidates() throws MyException {
        if (this.repository.getAdmittedCandidates().isEmpty() && this.repository.getFailedCandidates().isEmpty()){
            throw new MyException("Results are not in yet!");
        }

        if (this.repository.getAdmittedCandidates().isEmpty()){
            return null;
        }
        return this.repository.getAdmittedCandidates();
    }

    public ArrayList<Candidate> getAllFailedCandidates() throws MyException {
        if (this.repository.getAdmittedCandidates().isEmpty() && this.repository.getFailedCandidates().isEmpty()){
            throw new MyException("Results are not in yet!");
        }

        if (this.repository.getFailedCandidates().isEmpty()){
            return null;
        }
        return this.repository.getFailedCandidates();
    }

    public ArrayList<Candidate> getCandidatesContaining(String name, ArrayList list){
        if (this.repository.getCandidatesContaining(name, list).isEmpty()){
            return null;
        } else {
            return this.repository.getCandidatesContaining(name, list);
        }
    }

}
