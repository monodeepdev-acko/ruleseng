package com.example.ruleseng.models;

import com.example.ruleseng.entities.CustomRule;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Data
public class Person {
    private String name;
    private Integer age;
    private String stageCompleted;

    public Boolean isAdult() {
        return age > 18;
    }

    public Boolean isOld() {
        return age > 60;
    }

    public void setModifiedValue(String stageCompleted) {
        this.stageCompleted = stageCompleted;
    }

    private List<CustomRule> createNode(int nodesCount, int trueModVal) {
        List<CustomRule> customRules = new ArrayList<>();

        List<String> availableChannels = Arrays.asList(new String[]{
                "buy", "endorsement", "renewal"});
        Random random = new Random();

        for (int i = 0; i < nodesCount; i++) {
            String condition;
            if (i % trueModVal == 0) {
                condition = "rain == true";
            } else {
                condition = "rain == false";
            }

            String channel = availableChannels.get(random.nextInt(availableChannels.size()));
            String action;
            if (random.nextInt(2) == 1)
                action = "referenceData.setResult(true)";
            else
                action = "referenceData.setResult(false)";

            CustomRule r = new CustomRule("rule" + (i+1),
                    channel,
                    channel + ": rule num: " + (i+1), i+1,
                    condition, action);
            customRules.add(r);
        }
        return customRules;
    }

}
