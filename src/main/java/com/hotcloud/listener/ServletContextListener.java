package com.hotcloud.listener;

import com.hotcloud.redis.JedisPoolFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;

public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static Logger Log = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Log.info("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Log.info("contextDestroyed");
        JedisPoolFactory.getInstance().close();
    }
}
