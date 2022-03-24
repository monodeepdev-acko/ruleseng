package com.example.ruleseng.listeners;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.models.Context;
import com.example.ruleseng.repositories.CustomRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicallyAttachRulesListener implements RulesEngineListener {

    private final RuleConverter ruleConverter;
    private final CustomRuleRepository customRuleRepository;

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {

        log.info("registering dynamic rules before evaluation");
//        Context context = facts.get("context");
//
//        for (String ruleId : context.getCustomRuleIds()) {
//            CustomRule rule = customRuleRepository.findById(ruleId).orElse(null);
//            if (Objects.isNull(rule))
//                rules.register(ruleConverter.convertToRule(rule));
//        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {
        log.info("de-registering dynamic rules before evaluation");
    }
}
