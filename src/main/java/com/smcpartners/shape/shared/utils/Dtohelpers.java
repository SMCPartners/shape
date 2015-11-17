package com.smcpartners.shape.shared.utils;

import com.smcpartners.shape.shared.dto.common.UsecaseResponse;

import java.util.Map;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/7/15.
 * <p>
 * Changes:<b/>
 */
public class Dtohelpers {

    public static <T> T getValue(Map<String, Object> map, Class<T> clazz, String key) throws Exception {
        // Get the value
        Object value = map.get(key);

        // Is it valid
        if (value == null) {
            throw new Exception("Value not in map.");
        }

        // Type cast it
        return clazz.cast(value);
    }
}
