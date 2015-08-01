package com.wysiwyg.service.impl;

import com.wysiwyg.dao.TextDao;
import com.wysiwyg.model.UIText;
import com.wysiwyg.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Ioana Popescu on 8/1/2015.
 */
@Service
@Transactional
public class TextServiceImpl implements TextService {
    @Autowired
    private TextDao textDao;

    public UIText findById(int id) {
        return textDao.findById(id);
    }

    public UIText findByName(String name) {
        return textDao.findByName(name);
    }

    public List<UIText> findAll() {
        return textDao.findAll();
    }

    public void persistText(UIText uiText){
        textDao.persistText(uiText);
    }
}
