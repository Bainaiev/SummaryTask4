package ua.nure.bainaiev.SummaryTask4.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.exception.FileProcessingException;
import ua.nure.bainaiev.SummaryTask4.util.constant.SettingsAndFolderPaths;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains all queries  used in repositories and stored in specified properties file.
 *
 * @see ua.nure.bainaiev.SummaryTask4.annotation.Repository
 */
public final class QueryStorage {
    private static final String SQL_FILE = SettingsAndFolderPaths.getSqlFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryStorage.class);
    private static final Properties PROPERTIES = new Properties();

    private QueryStorage() {
    }

    static {
        try (InputStream resource = QueryStorage.class.getResourceAsStream(SQL_FILE)) {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}'", SQL_FILE);
            throw new FileProcessingException("Cannot load file: '" + SQL_FILE + "'", e);
        }
    }

    /**
     * Gets a query by {@code key}.
     *
     * @param key key of the query to find
     * @return found query
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
