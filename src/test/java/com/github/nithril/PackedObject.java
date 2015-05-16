package com.github.nithril;

/**
 * Created by nlabrot on 14/05/15.
 */
public interface PackedObject {

    int get();

    int sum();

    default int defaultSum(){
        return get();
    }
}
