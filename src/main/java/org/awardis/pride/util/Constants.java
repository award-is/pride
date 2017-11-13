package org.awardis.pride.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^((?=.*\\d)(?=.*[A-z]).{6,60})$";
    public static final Set<String> IMAGE_EXTENSION =
            Stream.of("jpeg", "jpg", "png", "bmp", "svg", "gif").collect(Collectors.toSet());

    //--------------------------------------Dropbox----------------------------------------------------------
    public static final String DROPBOX_ACCESS_TOKEN = "YWdxrzwzgEAAAAAAAAADLZ8DVok6A_6gsn5MZAto_AbLDueAXGvSYoZTYQ2AhnDK";
    public static final String DROPBOX_APP_KEY = "zcqnx2ltv4qlhdn";
}
