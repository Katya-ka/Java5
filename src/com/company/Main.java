package com.company;

public class Main {

    public static void main(String[] args) {
        Inject injector = new Inject();
        Root test = injector.inject(new Root());
        test.print();
    }
}
