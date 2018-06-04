package com.rxStore;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.subjects.PublishSubject;

public class RxStore {

    private static final RxStore INSTANCE = new RxStore();
    private Map<String, Object> data = new LinkedHashMap<>();
    private Map<String, PublishSubject<Object>> index = new LinkedHashMap<>();

    public static RxStore getInstance() {
        return INSTANCE;
    }

    public boolean contains(String id) {
        return data.containsKey(id);
    }

    public void save(String key, Object value) {
        data.put(key, value);
        notifyValueChanged(key);
    }

    public PublishSubject<Object> get(String key) {
        index.put(key, PublishSubject.create());
        return index.get(key);
    }

    public void remove(String key) {
        if (data.containsKey(key)) {
            data.remove(key);
        }
        if (index.containsKey(key)) {
            index.remove(key);
        }
    }

    public void removeAll(String keyPart) {
        for (String k : data.keySet()) {
            if (k.toLowerCase().contains(keyPart.toLowerCase())) {
                data.remove(k);
            }
        }
        for (String k : index.keySet()) {
            if (k.toLowerCase().contains(keyPart.toLowerCase())) {
                index.remove(k);
            }
        }
    }

    private void notifyValueChanged(String key) {
        index.get(key).onNext(data.get(key));
    }
}