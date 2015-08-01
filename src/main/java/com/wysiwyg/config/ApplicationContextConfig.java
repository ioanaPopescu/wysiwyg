package com.wysiwyg.config;

import com.wysiwyg.WicketApplication;
import com.wysiwyg.dao.TextDao;
import com.wysiwyg.dao.impl.TextDaoImpl;
import com.wysiwyg.service.TextService;
import com.wysiwyg.service.impl.TextServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration file. It also imports the configuration
 * for the persistence.
 *
 * Created by Ioana Popescu on 7/12/2015.
 */
@Configuration
@Import(PersistenceConfig.class)
public class ApplicationContextConfig{
    @Autowired
    PersistenceConfig persistenceConfig;

    @Bean
    public WicketApplication application() {
        return new WicketApplication();
    }

    @Bean(name = "textDao")
    public TextDao getTextDao() {
        return new TextDaoImpl();
    }

    @Bean(name = "textService")
    public TextService getTextService() {
        return new TextServiceImpl();
    }

}
