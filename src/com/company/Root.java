package com.company;

public class Root {
    @Autoinjection
    Tree test;

    public void print() {
        test.print();
    }
}
