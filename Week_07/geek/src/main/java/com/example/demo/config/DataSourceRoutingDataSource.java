package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class DataSourceRoutingDataSource extends AbstractRoutingDataSource {

    public static AtomicBoolean MASTER_STATUS = new AtomicBoolean(true);

    private static List<Object> MASTER_KEYS = new ArrayList<>();
    private static AtomicInteger MASTER_INDEX = new AtomicInteger(0);
    private static List<Object> SLAVER_KEYS = new ArrayList<>();
    private static AtomicInteger SLAVER_INDEX = new AtomicInteger(0);

    /*
     * 关键点：用于切换数据源
     * */
    @Override
    protected Object determineCurrentLookupKey() {
        if (MASTER_STATUS.get()) {
            return getNextMaster();
        } else {
            return getNextSlaver();
        }
    }

    public void setMasterKeys(Set<Object> masterKeys) {
        MASTER_KEYS.addAll(masterKeys);
    }

    public void setSlaverKeys(Set<Object> slaverKeys) {
        SLAVER_KEYS.addAll(slaverKeys);
    }

    /**
     * 获取下一个主库的key
     */
    private Object getNextMaster() {
        if (MASTER_KEYS.size() == 1) {
            return MASTER_KEYS.get(0);
        }
        int index = MASTER_INDEX.getAndAdd(1);
        return MASTER_KEYS.get(index % MASTER_KEYS.size());
    }

    /**
     * 获取下一个从库的key
     */
    private Object getNextSlaver() {
        if (SLAVER_KEYS.size() == 1) {
            return SLAVER_KEYS.get(0);
        }
        int index = SLAVER_INDEX.getAndAdd(1);
        return SLAVER_KEYS.get(index % SLAVER_KEYS.size());
    }
}
