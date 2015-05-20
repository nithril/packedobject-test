package com.github.nithril;

/**
 * Created by nlabrot on 14/05/15.
 */
public interface PackedObject {
    int get();
    int sum();
    void setIndex(int index);
    default int defaultSum() {
        return get();
    }
}
