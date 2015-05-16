package com.github.nithril;

import java.nio.ByteBuffer;

/**
 * Created by nlabrot on 14/05/15.
 */
public class WrappedPackedObject2 extends AbstractPackedObject implements PackedObject {


    @Override
    public int get() {
        return buffer.getInt(index);
    }
}
