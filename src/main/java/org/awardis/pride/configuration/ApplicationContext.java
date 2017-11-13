package org.awardis.pride.configuration;

import org.awardis.pride.dao.UserDao;
import org.awardis.pride.dao.UserDaoImpl;
import org.awardis.pride.cloud.CloudStorageClient;
import org.awardis.pride.cloud.DropboxClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import static org.awardis.pride.util.Constants.DROPBOX_ACCESS_TOKEN;
import static org.awardis.pride.util.Constants.DROPBOX_APP_KEY;

@Configuration
public class ApplicationContext {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }

    @Bean
    public CloudStorageClient cloudStorageClient() {
        return new DropboxClient(DROPBOX_APP_KEY, DROPBOX_ACCESS_TOKEN);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
