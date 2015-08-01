package com.wysiwyg;

import com.wysiwyg.dao.TextDao;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Created by Ioana Popescu on 7/8/2015.
 */
public class WicketApplication extends WebApplication {
    private TextDao textDao;

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}
