package com.smcpartners.shape.shared.dto.common;

import com.smcpartners.shape.shared.utils.Dtohelpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible:<br/>
 * 1. Base class for request and response
 * <p>
 * Created by johndestefano on 10/7/15.
 * <p>
 * Changes:<b/>
 */
public abstract class RequestResponse {

    protected Map<String, Object> map;

    public RequestResponse() {
        map = new HashMap<>();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Map<String, Object> add(String key, Object value) {
        map.put(key, value);
        return map;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public <T> T getValue(String key, Class<T> clazz) throws Exception {
        return Dtohelpers.getValue(map, clazz, key);
    }

}
