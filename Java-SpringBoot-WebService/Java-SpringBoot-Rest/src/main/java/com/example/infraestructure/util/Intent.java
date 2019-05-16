package com.example.infraestructure.util;

import java.util.HashMap;
import java.util.Map;

public class Intent {

    private Map<String, Object> param;

    public Intent() {
        param = new HashMap<>();
    }

    public void putExtra(String key, Object value) {
        param.put(key, value);
    }

    public Object getExtra(String key) {
        return param.get(key);
    }
}
