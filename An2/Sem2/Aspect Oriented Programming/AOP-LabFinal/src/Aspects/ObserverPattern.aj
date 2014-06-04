package Aspects;

import Controller.*;
import UI.FacultyForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 02.06.2014.
 */
public aspect ObserverPattern {

    declare parents: FacultyController implements Subject;
    declare parents: FacultyForm implements Observer;

    private List<Observer> Subject.observers = new ArrayList<Observer>();

    public void Subject.addObserver(Observer observer){
        System.out.println("Adding observer");
        observers.add(observer);
    }

    public void Subject.removeObserver(Observer obs) {
        System.out.println("Removing observer");
        observers.remove(obs);
    }

    public void Subject.notifyA(Object o) {
        for (Observer ob : observers) {
            ob.update(o);
        }
    }

    pointcut observed(FacultyController controller): execution(@ChangesSubject * * (..)) && this(controller);

    FacultyController ctrl;

    //adding observer
    after(FacultyController controller, FacultyForm form): initialization(UI.FacultyForm.new(*)) && this(form) && args(controller){
        controller.addObserver(form);
        ctrl = controller;
    }

    //observer notification
    after(FacultyController controller) returning: observed(controller){
        System.out.println("Inside ObserverAspect: notifyObservers");
        controller.notifyObservers("");
    }

    //observer action
    public void FacultyForm.update(Object arg0) {
        System.out.println("Inside ObserverAspect: HomeForm.update");
        fillTable(null);
    }

    //removing observer
    after(FacultyForm form): execution(* FacultyForm.close()) && this(form){
        ctrl.removeObserver(homeForm);
    }

}
