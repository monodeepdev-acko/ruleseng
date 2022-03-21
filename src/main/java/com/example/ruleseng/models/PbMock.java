package com.example.ruleseng.models;

import com.example.ruleseng.entities.CustomRule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class PbMock {
    @JsonIgnore
    private List<CustomRule> availableRules;
    private Boolean result;

    public PbMock(int nodesCount, int trueModVal) {
        this.availableRules = createNode(nodesCount, trueModVal);
        this.result = false;
    }

    public PbMock() {
        this.availableRules = createNode(10, 5);
        this.result = false;
    }

    private List<CustomRule> createNode(int nodesCount, int trueModVal) {
        List<CustomRule> customRules = new ArrayList<>();

        for (int i = 0; i < nodesCount; i++) {
            String condition;
            if (i % trueModVal == 0) {
                condition = "rain == true";
            } else {
                condition = "rain == false";
            }

            CustomRule r = new CustomRule("rule" + (i+1), "test-channel",
                    "desc: rule num: " + (i+1), i+1,
                    condition, String.format("referenceData.setResult(true)", (i+1)));
            customRules.add(r);
        }
        return customRules;
    }
}
