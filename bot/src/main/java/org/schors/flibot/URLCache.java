package org.schors.flibot;

import jersey.repackaged.com.google.common.cache.Cache;
import jersey.repackaged.com.google.common.cache.CacheBuilder;

import java.util.HashMap;
import java.util.Map;

public class URLCache {

    private Map<String, Cache<String, String>> users = new HashMap<>();

    public URLCache() {
    }

    public String putNewURL(String userName, String url) {
        Cache<String, String> cache = users.get(userName);
        if (cache == null) {
            cache = CacheBuilder.newBuilder().maximumSize(1000).build();
            users.put(userName, cache);
        }
        String id = Integer.toHexString(url.hashCode());
        cache.put(id, url);
        return id;
    }

    public String getURL(String userName, String id) {
        Cache<String, String> cache = users.get(userName);
        if (cache != null) {
            return cache.getIfPresent(id);
        }
        return null;
    }
}
