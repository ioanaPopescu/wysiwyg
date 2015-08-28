package com.wysiwyg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ioana Popescu on 7/26/2015.
 */
@Entity
@Table(name = "TEXT")
public class UIText implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FONT_FAMILY", nullable = false)
    private String font;

    @Column(name = "FONT_SIZE", nullable = false)
    private Integer size;

    @Column(name = "BOLD")
    private boolean bold;

    @Column(name = "ITALIC")
    private boolean italic;

    @Column(name = "UNDERLINE")
    private boolean underline;

    @Column(name = "UI_TEXT", nullable = false)
    private String text;

    @Column(name = " o mai p", nullable = false)
    private String name;

    @Transient
    private AlignmentType alignmentType;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
