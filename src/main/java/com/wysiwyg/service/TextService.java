package com.wysiwyg.service;

import com.wysiwyg.model.UIText;
import org.apache.wicket.markup.html.form.TextArea;

import java.util.List;

/**
 * Service class to manage <code>UIText</code> objects.
 *
 * Created by Ioana Popescu on 8/1/2015.
 */
public interface TextService {
    /**
     * Method used to extract an <code>UIText</code> object
     * from the database using its id.
     *
     * @param id
     * @return
     */
    UIText findById(int id);

    /**
     * Method used to extract <code>UIText</code> object
     * from the database using its name.
     *
     * @param name
     * @return
     */
    UIText findByName(String name);

    /**
     * Method used to list all objects from the database
     * of type <code>UIText</code>.
     *
     * @return
     */
    List<UIText> findAll();

    /**
     * Method used to persist a new <code>UIText</code>
     * object into the database.
     *
     * @param uiText
     */
    void persistText(UIText uiText);
}
