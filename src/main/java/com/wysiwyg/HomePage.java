package com.wysiwyg;

import com.wysiwyg.model.AlignmentType;
import com.wysiwyg.model.StyleConstants;
import com.wysiwyg.model.UIText;
import com.wysiwyg.service.TextService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.StringUtils;

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
    Button alignLeft;
    Button alignCenter;
    Button alignJustify;
    Button saveDB;
    TextArea uiText;
    UIText text = new UIText();

    @SpringBean(name = "textService")
    TextService service;

    public UIText getText() {
        return text;
    }

    public Model<String> getStyleModel() {
        return styleModel;
    }

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
        form.add(alignCenter);
        form.add(alignLeft);
        form.add(alignJustify);
        form.add(saveDB);
        // text area
        form.add(uiText);
        add(form);
        // to add default values
        updateStyleModel();
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

        alignLeft = new Button("alignLeftButton") {
            @Override
            public void onSubmit() {
                text.setAlignmentType(AlignmentType.LEFT);
                updateStyleModel();
            }
        };

        alignJustify = new Button("alignJustifyButton") {
            @Override
            public void onSubmit() {
                text.setAlignmentType(AlignmentType.JUSTIFY);
                updateStyleModel();
            }
        };

        alignCenter = new Button("alignCenterButton") {
            @Override
            public void onSubmit() {
                text.setAlignmentType(AlignmentType.CENTER);
                updateStyleModel();
            }
        };

        saveDB = new Button("saveDBButton") {
            @Override
            public void onSubmit() {
                service.persistText(text);
            }
        };

        uiText = new TextArea<String>("uiText", Model.of(""));
        uiText.add(new AjaxFormComponentUpdatingBehavior("onkeyup") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Object input = uiText.getConvertedInput();
                text.setText(StringUtils.isEmpty(input) ? "Enter text..." : input.toString());
            }

            @Override
            protected boolean disableFocusOnBlur() {
                return false;
            }

            @Override
            public void onEvent(Component component, IEvent<?> event) {
                super.onEvent(component, event);
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
                        return Arrays.asList(StyleConstants.FONT_SIZES);
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
            builder.append(StyleConstants.FONT_FAMILY).append(text.getFont()).append(";");
        }
        if (text.getSize() != null) {
            builder.append(StyleConstants.FONT_SIZE).append(text.getSize()).append("px").append(";");
        }
        if (text.isBold()) {
            builder.append(StyleConstants.BOLD);
        }
        if (text.isItalic()) {
            builder.append(StyleConstants.ITALIC);
        }
        if (text.isUnderline()) {
            builder.append(StyleConstants.UNDERLINE);
        }

        //TODO I want it with switch!!
        if (AlignmentType.JUSTIFY.equals(text.getAlignmentType())) {
            builder.append(StyleConstants.JUSTIFY);
        }
        if (AlignmentType.CENTER.equals(text.getAlignmentType())) {
            builder.append(StyleConstants.CENTER);
        }
        if (AlignmentType.LEFT.equals(text.getAlignmentType()) || text.getAlignmentType() == null) {
            builder.append(StyleConstants.LEFT);
        }
        //todo the rest of the fields
        styleModel.setObject(builder.toString());
        uiText.setConvertedInput(text.getText());
    }
}
