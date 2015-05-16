package com.github.nithril;

/**
 * Created by nlabrot on 14/05/15.
 */
public interface PackedObject {

    int getA();
    int getB();
    int getC();
    int getD();

    int sum();

    default int defaultSum(){
        return getA() + getB() + getC() + getD();
    }
}
