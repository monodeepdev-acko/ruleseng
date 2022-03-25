package com.example.ruleseng.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Context {

    private Map<String, Object> data;

    @JsonIgnore
    private List<String> customRuleIds;

    public Object get(String key) {
        if (!data.containsKey(key) || !data.containsValue(key))
            return null; // TODO: replace this with proper error handling
        return data.get(key);
    }
}
