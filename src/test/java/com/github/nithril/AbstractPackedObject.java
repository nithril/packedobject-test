package com.github.nithril;

import java.util.List;

/**
 * Created by nlabrot on 16/05/15.
 */
public abstract class AbstractPackedObject implements PackedObject {
    protected int index = 0;
    protected List<Integer> buffer;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBuffer(List<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public int sum(){
        return get();
    }
}
