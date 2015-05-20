package com.github.nithril;

/**
 * Created by nlabrot on 14/05/15.
 */
public class WrappedPackedObject extends AbstractPackedObject implements PackedObject {

    public WrappedPackedObject(int[] buffer) {
        this.buffer = buffer;
    }

    @Override
    public int get() {
        return buffer[index];
    }
}
