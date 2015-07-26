package com.wysiwyg;

import com.wysiwyg.model.UIText;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
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
    Form form;
    Button boldButton;
    Button italicButton;
    Button underlineButton;
    TextArea uiText;
    UIText text = new UIText();

    Model<String> styleModel = new Model<String>("");

    public HomePage() {
        this(null);
    }

    public HomePage(PageParameters parameters) {
        super(parameters);

        initializeElements();
        form = new Form("form");

        //formatting buttons
        form.add(populateFontDropDownChoice(text));
        form.add(populateSizeDropDownChoice(text));
        uiText.add(AttributeModifier.append("style", styleModel));
        form.add(boldButton);
        form.add(italicButton);
        form.add(underlineButton);
        // text area
        form.add(uiText);
        add(form);
    }

    public void initializeElements() {
        boldButton = new Button("boldButton") {
            @Override
            public void onSubmit() {
                text.setBold(!text.isBold());
                updateStyleModel();
            }
        };
        italicButton = new Button("italicButton") {
            @Override
            public void onSubmit() {
                text.setItalic(!text.isItalic());
                updateStyleModel();
            }
        };
        underlineButton = new Button("underlineButton") {
            @Override
            public void onSubmit() {
                text.setUnderline(!text.isUnderline());
                updateStyleModel();
            }
        };


        uiText = new TextArea<String>("uiText", Model.of(""));
        uiText.add(new AjaxFormComponentUpdatingBehavior("onkeyup") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                text.setText(uiText.getConvertedInput().toString());
            }
        });
    }

    public DropDownChoice populateFontDropDownChoice(final UIText text) {
        DropDownChoice<String> fonts = new DropDownChoice<String>("font",
                new PropertyModel<String>(text, "font"),
                new LoadableDetachableModel<List<String>>() {
                    @Override
                    protected List<String> load() {
                        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
                    }
                }) {
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

            @Override
            protected void onSelectionChanged(String newSelection) {
                text.setFont(newSelection);
                updateStyleModel();
            }
        };
        return fonts;
    }

    public DropDownChoice populateSizeDropDownChoice(final UIText text) {
        DropDownChoice<Integer> sizes = new DropDownChoice<Integer>("size",
                new PropertyModel<Integer>(text, "size"),
                new LoadableDetachableModel<List<Integer>>() {
                    @Override
                    protected List<Integer> load() {
                        Integer[] fontSizes = {6, 8, 10, 12, 16, 20, 24, 32, 48, 72};
                        return Arrays.asList(fontSizes);
                    }
                }) {
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

            @Override
            protected void onSelectionChanged(Integer newSelection) {
                text.setSize(newSelection);
                updateStyleModel();
            }
        };
        return sizes;
    }

    private void updateStyleModel() {
        StringBuilder builder = new StringBuilder();
        if (text.getFont() != null) {
            builder.append("font-family:").append(text.getFont()).append(";");
        }
        if (text.getSize() != null) {
            builder.append("font-size:").append(text.getSize()).append("px").append(";");
        }
        if (text.isBold()){
            builder.append("font-weight:bold;");
        }
        if (text.isItalic()){
            builder.append("font-style: italic;");
        }
        if (text.isUnderline()){
            builder.append("text-decoration: underline;");
        }
        //todo the rest of the fields
        styleModel.setObject(builder.toString());
        uiText.setConvertedInput(text.getText());
    }
}
