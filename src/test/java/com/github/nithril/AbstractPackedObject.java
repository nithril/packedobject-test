package com.github.nithril;

import java.nio.ByteBuffer;

/**
 * Created by nlabrot on 16/05/15.
 */
public abstract class AbstractPackedObject implements PackedObject {
    protected int index = 0;
    protected ByteBuffer buffer;


    public void setIndex(int index) {
        this.index = index;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public int sum(){
        return get();
    }
}
