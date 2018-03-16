package com.rxStore;

import java.util.LinkedHashMap;
import java.util.Map;

public class RxStore {

    private static final RxStore INSTANCE = new RxStore();
    private Map<String, Object> data = new LinkedHashMap<>();

    public static RxStore getInstance() {
        return INSTANCE;
    }

    public boolean contains(String id) {
        return data.containsKey(id);
    }

    public void save(String id, Object object) {
        data.put(id, object);
    }

    public Object get(String id) {
        return data.get(id);
    }

    public void remove(String id) {
        if (data.containsKey(id)) {
            data.remove(id);
        }
    }

    public void removeAll(String key) {
        for (String k : data.keySet()) {
            if (k.toLowerCase().contains(key.toLowerCase())) {
                data.remove(k);
            }
        }
    }
}