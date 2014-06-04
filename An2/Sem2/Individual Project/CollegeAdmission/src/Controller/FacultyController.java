package Controller;

import Model.AdmittedCandidate;
import Model.Candidate;
import Model.FailedCandidate;
import Model.Section;
import Repository.RepoInterface;
import Utils.MyException;
import Utils.Validator;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by mihai on 19.05.2014.
 */
public class FacultyController extends Observable{

    private RepoInterface repository;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public FacultyController(RepoInterface repository) {
        this.repository = repository;
    }

    public ArrayList<Candidate> getAllCandidates(){
        return this.repository.getCandidateList();
    }

    public ArrayList<Section> getAllSections(){
        return this.repository.getSectionList();
    }

    public void addCandidate(Candidate candidate) throws MyException {
        Validator.validateCandidate(candidate, getAllCandidates(), getAllSections());
        this.repository.addCandidate(candidate);
        this.notifyObservers(this);
    }

    public void addSection(Section section) throws MyException {
        Validator.validateSection(section, getAllSections());
        this.repository.addSection(section);
        this.notifyObservers(this);
    }

    public void removeCandidate(Candidate candidate){
        this.repository.removeCandidate(candidate);
        this.notifyObservers(this);
    }

    public void removeSection(Section section){
        this.repository.removeSection(section);
        this.notifyObservers(this);
    }

    public Candidate getCandidateByCNP(String cnp){
        ArrayList<Candidate> list = this.repository.getCandidateList();
        for (Candidate candidate : list){
            if (candidate.getCnp().equals(cnp)){
                return candidate;
            }
        }
        return null;
    }

    public Section getSectionByName(String name){
        ArrayList<Section> list = this.repository.getSectionList();
        for (Section section : list){
            if (section.getName().equals(name)){
                return section;
            }
        }
        return null;
    }

    public void generateResults(){

        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<Candidate> candidates = new ArrayList<Candidate>(this.repository.getCandidateList());

        for (Object section : this.repository.getSectionList()){
            Section section1 = (Section) section;
            sections.add(new Section(section1.getName(), section1.getPositions()));
        }

        ArrayList<Candidate> admittedCandidates = new ArrayList<Candidate>();
        ArrayList<Candidate> rejectedCandidates = new ArrayList<Candidate>();

        Collections.sort(candidates, Candidate.candidateComparator);

        for (Candidate candidate : candidates){

            boolean distributed = false;

            for (String option : candidate.getSections()){

                for (Section section : sections){
                    if (option.equals(section.getName()) && section.getPositions() > 0){
                        section.setPositions(section.getPositions() - 1);
                        AdmittedCandidate admittedCandidate = new AdmittedCandidate(candidate.getCnp(), candidate.getName(),
                                candidate.getAddress(), candidate.getBacAverage(), candidate.getHighSchoolAverage(),
                                candidate.getSections(), option);
                        admittedCandidates.add(admittedCandidate);
                        distributed = true;
                        break;
                    }
                }

                if (distributed){
                    break;
                }

            }

            if (!distributed){
                FailedCandidate failedCandidate = new FailedCandidate(candidate.getCnp(), candidate.getName(),
                        candidate.getAddress(), candidate.getBacAverage(), candidate.getHighSchoolAverage(),
                        candidate.getSections());
                rejectedCandidates.add(failedCandidate);
            }

        }

        this.repository.setAdmittedCandidates(admittedCandidates);
        this.repository.setFailedCandidates(rejectedCandidates);

    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Observable observable){

        ArrayList list = new ArrayList();
        list.add(this.repository.getCandidateList());
        list.add(this.repository.getSectionList());

        for (Observer observer : this.observers){
            observer.update(observable, list);
        }

    }

}
