package com.github.nithril;

import java.nio.ByteBuffer;

/**
 * Created by nlabrot on 14/05/15.
 */
public class WrappedPackedObject extends AbstractPackedObject implements PackedObject {

    protected int index = 0;

    protected ByteBuffer buffer;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public int getA() {
        return buffer.getInt(index+0);
    }

    @Override
    public int getB() {
        return buffer.getInt(index+4);
    }

    @Override
    public int getC() {
        return buffer.getInt(index+8);
    }

    @Override
    public int getD() {
        return buffer.getInt(index+12);
    }
}
