package com.redeheavy.heavycore.exceptions;
public class SuperUpdaterContradictionException extends RuntimeException {

    public SuperUpdaterContradictionException() {
        super("Double contradiction detected: can't delete and rename a file at same time, this maybe cause NullPointerException or FileNotFoundException.");
    }

}
