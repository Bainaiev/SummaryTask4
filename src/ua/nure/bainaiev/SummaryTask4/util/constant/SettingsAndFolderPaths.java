package ua.nure.bainaiev.SummaryTask4.util.constant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.exception.FileProcessingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class SettingsAndFolderPaths {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsAndFolderPaths.class);

    private static final String SQL_FILE = "/ua/nure/bainaiev/SummaryTask4/settings/query.properties";
    private static final String DATABASE_CONFIG_FILE = "/ua/nure/bainaiev/SummaryTask4/settings/db.properties";


    public static String getSqlFile() {
        return SQL_FILE;
    }

    public static String getDatabaseConfigFile() {
        return DATABASE_CONFIG_FILE;
    }


}
