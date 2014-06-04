package Tests;

import Controller.FacultyController;
import Model.Candidate;
import Model.Section;
import Repository.RepoInterface;
import Repository.Repository;
import Utils.MyException;

import java.util.ArrayList;

/**
 * Created by mihai on 03.06.2014.
 */
public class UnitTest {

    private String candidatesFileName = "testCandidates.txt";
    private String sectionsFileName = "testSections.txt";
    private String admittedFileName = "testAdmitted.txt";
    private String failedFileName = "testFailed.txt";

    private RepoInterface repository;
    private FacultyController controller;

    public UnitTest() throws MyException {

        this.repository = new Repository(candidatesFileName, sectionsFileName, admittedFileName, failedFileName);
        this.controller = new FacultyController(repository);
        this.runTests();

    }

    private void runTests() throws MyException {

        //loading and adding sections
        if (controller.getAllSections().size() != 0){
            throw new MyException("Error loading sections");
        }

        controller.addSection(new Section("Section1", 1));
        controller.addSection(new Section("Section2", 1));

        if (controller.getAllSections().size() != 2){
            throw new MyException("Error adding sections");
        }


        //loading and adding candidates
        if (controller.getAllCandidates().size() != 0){
            throw new MyException("Error loading candidates");
        }

        ArrayList<String> options = new ArrayList<String>();
        options.add(((ArrayList<Section>)repository.getSectionList()).get(0).getName());
        controller.addCandidate(new Candidate("1941123352646", "testName", "address", 9, 10, options));

        options = new ArrayList<String>();
        options.add(((ArrayList<Section>) repository.getSectionList()).get(1).getName());
        options.add(((ArrayList<Section>)repository.getSectionList()).get(0).getName());
        controller.addCandidate(new Candidate("1883723132749", "testName2", "address2", 8, 8, options));

        options = new ArrayList<String>();
        options.add(((ArrayList<Section>) repository.getSectionList()).get(1).getName());
        controller.addCandidate(new Candidate("2881121174829", "testName3", "address3", 9, 9, options));

        if (controller.getAllCandidates().size() != 3){
            throw new MyException("Error adding candidate");
        }


        //get candidates containing
        if (repository.getCandidatesContaining("test", controller.getAllCandidates()).size() !=
                controller.getAllCandidates().size()){
            throw new MyException("Error getting candidates containing");
        }


        //generating results
        controller.generateResults();

        if (repository.getFailedCandidates().size() != 1){
            throw new MyException("Error generating results");
        }

        if (repository.getAdmittedCandidates().size() != 2){
            throw new MyException("Error generating results");
        }


        //removing candidates
        controller.removeCandidate(controller.getCandidateByCNP("1941123352646"));
        controller.removeCandidate(controller.getAllCandidates().get(0));
        controller.removeCandidate(controller.getAllCandidates().get(0));

        if (controller.getAllCandidates().size() != 0){
            throw new MyException("Error removing candidate");
        }


        //removing sections
        controller.removeSection(controller.getSectionByName("Section1"));
        controller.removeSection(controller.getAllSections().get(0));

        if (controller.getAllSections().size() != 0){
            throw new MyException("Error removing sections");
        }

    }

}
