package com.example.ruleseng.listeners;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.models.PbMock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicallyAttachRulesListener implements RulesEngineListener {

    private final PbMock pbMockData = new PbMock();
    private final RuleConverter ruleConverter;

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {

        log.info("registering dynamic rules before evaluation");

//        PbMock pbMockData = facts.get("referenceData");

        for(CustomRule rule : pbMockData.getAvailableRules()) {
            rules.register(ruleConverter.convertToRule(rule));
        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {
        log.info("de-registering dynamic rules before evaluation");

//        PbMock pbMockData = facts.get("referenceData");

        for(CustomRule rule : pbMockData.getAvailableRules()) {
            rules.unregister(rule.getName());
        }
    }
}
