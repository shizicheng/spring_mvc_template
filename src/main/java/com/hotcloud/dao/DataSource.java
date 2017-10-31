package com.hotcloud.dao;

import com.hotcloud.util.ConfigUtil;
import com.mchange.v2.c3p0.AbstractComboPooledDataSource;

import javax.naming.Referenceable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.DriverManager;

public final class DataSource extends AbstractComboPooledDataSource implements Serializable, Referenceable {
    private static final long serialVersionUID = 1L;
    private static final short VERSION = 2;

    public DataSource() {
        init();
    }

    public DataSource(boolean autoregister) {
        super(autoregister);
        init();
    }

    public DataSource(String configName) {
        super(configName);
        init();
    }

    private void init() {
        try {
            String dbHost = ConfigUtil.config.get("dbHost");
            String dbPort = ConfigUtil.config.get("dbPort");
            String dbName = ConfigUtil.config.get("dbName");
            String dbUser = ConfigUtil.config.get("dbUser");
            String dbPw = ConfigUtil.config.get("dbPw");
            String dbSchema = ConfigUtil.config.get("dbSchema");
            String dbDriver = ConfigUtil.config.get("dbDriver");

            setDriverClass(dbDriver);
            setJdbcUrl("jdbc:" + dbSchema + "://" + dbHost + ":" + dbPort + "/" + dbName);
            setUser(dbUser);
            setPassword(dbPw);
            setInitialPoolSize(3);
            setMaxPoolSize(100);
            setMinPoolSize(3);
            setMaxIdleTime(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeShort(2);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        short version = ois.readShort();
        switch (version) {
            case 2:
                return;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

    @Override
    public void close() {
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver(getJdbcUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.close();
    }

}
