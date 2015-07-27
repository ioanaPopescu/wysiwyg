package com.wysiwyg.model;

/**
 * Created by Ioana Popescu on 7/27/2015.
 */
public enum AlignmentType {
    LEFT("left"),
    CENTER("center"),
    JUSTIFY("justify");

    String value;

    AlignmentType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}
