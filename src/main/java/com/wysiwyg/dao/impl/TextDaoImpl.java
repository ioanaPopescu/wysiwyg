package com.wysiwyg.dao.impl;

import com.wysiwyg.dao.TextDao;
import com.wysiwyg.model.UIText;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ioana Popescu on 8/1/2015.
 */
@Repository
public class TextDaoImpl implements TextDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public UIText findById(int id) {
        return (UIText) getCurrentSession().get(UIText.class, id);
    }

    public UIText findByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(UIText.class);
        criteria.add(Restrictions.eq("name", name));
        return (UIText) criteria.uniqueResult();
    }

    public List<UIText> findAll() {
        return getCurrentSession().createCriteria(UIText.class).list();
    }

    public void persistText(UIText uiText){
        getCurrentSession().persist(uiText);
    }
}
