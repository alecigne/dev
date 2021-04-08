package net.lecigne.codingkatas.ck0019.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCHelperFactory {

    private static final Properties PROPERTIES;

    static {
        InputStream inputStream = null;
        PROPERTIES = new Properties();
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static final String H2_DRIVER = getProperty("h2.driver");
    private static final String H2_URL = getProperty("h2.url");
    private static final String H2_USER = getProperty("h2.user");
    private static final String H2_PASSWORD = getProperty("h2.password");

    private static final String MARIADB_DRIVER = getProperty("mariadb.driver");
    private static final String MARIADB_URL = getProperty("mariadb.url");
    private static final String MARIADB_USER = getProperty("mariadb.user");
    private static final String MARIADB_PASSWORD = getProperty("mariadb.password");

    public enum JDBCTypes {
        H2, MARIADB
    }

    public static JDBCHelper getJDBCHelper(JDBCTypes type) {
        JDBCHelper jdbc = null;
        if (type == JDBCTypes.H2) {
            jdbc = new JDBCHelper(H2_DRIVER, H2_URL, H2_USER, H2_PASSWORD);
        } else if (type == JDBCTypes.MARIADB) {
            jdbc = new JDBCHelper(MARIADB_DRIVER, MARIADB_URL, MARIADB_USER, MARIADB_PASSWORD);
        }
        return jdbc;
    }

}
