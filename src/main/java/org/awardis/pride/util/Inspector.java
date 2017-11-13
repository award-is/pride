package org.awardis.pride.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Inspector {
    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        Inspector.messageSource = messageSource;
    }

    public static boolean isValidPageSizePair(Integer page, Integer size) {
        return (page == null || size != null) && (page != null || size == null);
    }

    public static boolean isImageFile(String filename) {
        String fileExtension = FilenameUtils.getExtension(filename);
        return Constants.IMAGE_EXTENSION.contains(fileExtension);
    }

    public static void checkUserAccessPermission(Long sourceId, Long principalId) {
        if (!principalId.equals(sourceId)) {
            String message = messageSource.getMessage("error.access.forbidden", null, Locale.ENGLISH);
            throw new SecurityException(message);
        }
    }

    public static void checkAccountActivation(AccountStatus status) {
        if (status == AccountStatus.UNCONFIRMED) {
            String message = messageSource.getMessage("error.email.not_confirm", null, Locale.ENGLISH);
            throw new SecurityException(message);
        }
    }
}
