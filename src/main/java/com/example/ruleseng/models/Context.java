package com.example.ruleseng.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Context {

    private Map<String, Object> dataMap;

    private Map<String, Object> resultsMap;

    @JsonIgnore
    private List<String> customRuleIds;

    public Object get(String key) {
        if (!dataMap.containsKey(key) || !dataMap.containsValue(key))
            return null; // TODO: replace this with proper error handling
        return dataMap.get(key);
    }

    public void set(String key, Object val) {
        if (Objects.isNull(resultsMap))
            resultsMap = new HashMap<>();
        resultsMap.put(key, val);
    }
}
