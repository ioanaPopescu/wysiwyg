package com.wysiwyg.model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Ioana Popescu on 7/26/2015.
 */
public class UIText implements Serializable{
    private String font;
    private Integer size;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private AlignmentType alignmentType;
    private String text;

    public UIText() {
        this.font = "Times New Roman";
        this.size = 12;
        this.alignmentType = AlignmentType.LEFT;
        this.text = "Enter text...";
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public AlignmentType getAlignmentType() {
        return alignmentType;
    }

    public void setAlignmentType(AlignmentType alignmentType) {
        this.alignmentType = alignmentType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
