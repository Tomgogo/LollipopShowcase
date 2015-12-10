package com.pardus;

/**
 * Created by Tom on 2015/11/23.
 */
public class DBManager {
    private DBManager() {
    }

    private static class DBManagerHolder {
        private static DBManager instance = new DBManager();
    }

    public DBManager getInstance() {
        return DBManagerHolder.instance;
    }
}
