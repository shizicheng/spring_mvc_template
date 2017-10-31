package com.hotcloud.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public final class ConfigUtil {
    public static final String HOME_CHARSET = "UTF-8";
    public static Map<String, String> config = new HashMap<>();

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(is);
            Iterator<Map.Entry<Object, Object>> iter = prop.entrySet().iterator();
            Map.Entry<Object, Object> next;
            while (iter.hasNext()) {
                next = iter.next();
                String key = next.getKey().toString();
                Object value = next.getValue();
                config.put(key, (String) value);
            }

            switch (Common.intval(config.get("set"))) {
                case 0:
                    break;
                case 1:
                    config.put("siteUrl", config.get("siteUrl_test"));
                    config.put("dbSchema", config.get("dbSchema_test"));
                    config.put("dbDriver", config.get("dbDriver_test"));
                    config.put("dbHost", config.get("dbHost_test"));
                    config.put("dbName", config.get("dbName_test"));
                    config.put("dbPort", config.get("dbPort_test"));
                    config.put("dbUser", config.get("dbUser_test"));
                    config.put("dbPw", config.get("dbPw_test"));
                    config.put("REDIS_HOST", config.get("REDIS_HOST_TEST"));
                    config.put("REDIS_PSW", config.get("REDIS_PSW_TEST"));
                    break;
                case 2:
                    config.put("siteUrl", config.get("siteUrl_dev"));
                    config.put("dbSchema", config.get("dbSchema_dev"));
                    config.put("dbDriver", config.get("dbDriver_dev"));
                    config.put("dbHost", config.get("dbHost_dev"));
                    config.put("dbName", config.get("dbName_dev"));
                    config.put("dbPort", config.get("dbPort_dev"));
                    config.put("dbUser", config.get("dbUser_dev"));
                    config.put("dbPw", config.get("dbPw_dev"));
                    config.put("REDIS_HOST", config.get("REDIS_HOST_DEV"));
                    config.put("REDIS_PSW", config.get("REDIS_PSW_DEV"));
                    break;
                case 3:
                    config.put("siteUrl", config.get("siteUrl_local"));
                    config.put("dbSchema", config.get("dbSchema_local"));
                    config.put("dbDriver", config.get("dbDriver_local"));
                    config.put("dbHost", config.get("dbHost_local"));
                    config.put("dbName", config.get("dbName_local"));
                    config.put("dbPort", config.get("dbPort_local"));
                    config.put("dbUser", config.get("dbUser_local"));
                    config.put("dbPw", config.get("dbPw_local"));
                    config.put("REDIS_HOST", config.get("REDIS_HOST_LOCAL"));
                    config.put("REDIS_PSW", config.get("REDIS_PSW_LOCAL"));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
