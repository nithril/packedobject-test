package com.github.nithril;

/**
 * Created by nlabrot on 16/05/15.
 */
public abstract class AbstractPackedObject implements PackedObject {

    @Override
    public int sum(){
        return get();
    }

   /* @Override
    public int defaultSum() {
        return getA() + getB() + getC() + getD();
    }*/
}
