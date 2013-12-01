package com.company;

import Controller.Controller;
import Model.Graph;
import UI.Console;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Controller ctrl = new Controller(graph);
        Console console = new Console(ctrl);
    }
}
