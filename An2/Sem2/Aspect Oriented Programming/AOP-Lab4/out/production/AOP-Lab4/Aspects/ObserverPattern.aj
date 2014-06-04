package Aspects;

import Controller.Controller;
import UI.HomeForm;
import Utils.ChangesSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 19.05.2014.
 */
public aspect ObserverPattern {

    declare parents: Controller implements Subject;
    declare parents: HomeForm implements Observer;

    private List<Observer> Subject.observers = new ArrayList<Observer>();

    public void Subject.addObserver(Observer obs) {
        System.out.println("Adding observer");
        observers.add(obs);
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

    pointcut observed(Controller controller): execution(@ChangesSubject * * (..)) && this(controller);


    Controller ctrl;
//    //adding observer
//    after(Controller controller, HomeForm homeForm): initialization(UI.HomeForm.new(*)) && this(homeForm) && args(controller){
//        controller.addObserver(homeForm);
//        ctrl = controller;
//    }
//
//    //observer notification
//    after(Controller controller) returning: observed(controller){
//        System.out.println("Inside ObserverAspect: notifyObservers");
//        controller.notifyA("");
//    }

    //observer action
//    public void HomeForm.update(Object arg0) {
//        System.out.println("Inside ObserverAspect: HomeForm.update");
//        fillTable(null);
//    }

//    //removing observer
//    after(HomeForm homeForm): execution(* HomeForm.close()) && this(homeForm){
//        ctrl.removeObserver(homeForm);
//    }

}
