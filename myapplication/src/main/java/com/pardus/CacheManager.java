package com.pardus;

/**
 * Created by Tom on 2015/11/23.
 */
public class CacheManager {
    private CacheManager() {
    }

    private static class CacheManagerHolder {
        private static CacheManager instance = new CacheManager();
    }

    public CacheManager getInstance() {
        return CacheManagerHolder.instance;
    }
}
