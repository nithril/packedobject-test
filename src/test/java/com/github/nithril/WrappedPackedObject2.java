package com.github.nithril;

import java.nio.ByteBuffer;

/**
 * Created by nlabrot on 14/05/15.
 */
public class WrappedPackedObject2 extends AbstractPackedObject implements PackedObject {

    protected int index = 0;

    protected ByteBuffer buffer;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public int get() {
        return buffer.getInt(index+4);
    }


}
