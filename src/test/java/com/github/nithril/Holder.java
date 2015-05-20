package com.github.nithril;

/**
 * Created by nlabrot on 19/05/15.
 */
public class Holder {
    public static final int NB = 16384-1;
    public static WrappedPackedObject packedObject = new WrappedPackedObject(new int[NB]);


    public static int value = 1;

    public static int get(){
        return (value++)&NB;
    }


}
