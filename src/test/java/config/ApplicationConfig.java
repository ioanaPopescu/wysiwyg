package config;

import com.wysiwyg.PersistenceConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApplicationConfig {
    @Autowired
    PersistenceConfig persistenceConfig;
}
