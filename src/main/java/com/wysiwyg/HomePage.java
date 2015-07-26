package com.wysiwyg;

import com.wysiwyg.model.UIText;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ioana Popescu on 7/8/2015.
 */
public class HomePage extends WebPage {
    public HomePage() {
        this(null);
    }

    public HomePage(PageParameters parameters) {
        super(parameters);

        UIText text = new UIText();
        add(populateFontDropDownChoice(text));
        add(populateSizeDropDownChoice(text));
        Button boldButton = new Button("boldButton"){
            public void onSubmit(){
                info("Bold that text!!");
            }
        };
        add(boldButton);

        Button italicButton = new Button("italicButton"){
            public void onSubmit(){
                System.out.println("Italic text!");
            }
        };
        add(italicButton);

        Button underlineButton = new Button("underlineButton"){
            public void onSubmit(){
                System.out.println("Underline text!");
            }
        };
        add(underlineButton);

        TextArea<String> uiText = new TextArea<String>("uiText", Model.of(""));
        add(uiText);
    }

    public DropDownChoice populateFontDropDownChoice(UIText text) {
        return new DropDownChoice<String>("font",
                new PropertyModel<String>(text, "font"),
                new LoadableDetachableModel<List<String>>() {
                    @Override
                    protected List<String> load() {
                        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
                    }
                });
    }

    public DropDownChoice populateSizeDropDownChoice(UIText text) {
        return new DropDownChoice<Integer>("size",
                new PropertyModel<Integer>(text, "size"),
                new LoadableDetachableModel<List<Integer>>() {
                    @Override
                    protected List<Integer> load() {
                        Integer[] fontSizes = {6, 8, 10, 12, 16, 20, 24, 32, 48, 72};
                        return Arrays.asList(fontSizes);
                    }
                });
    }
}
