package com.example.ruleseng.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyRuleListener implements RuleListener {

    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
//        log.info("beforeEvaluate: rule: {}, facts: {}", rule, facts);
        return true;
    }

    @Override
    public void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {
//        log.info("afterEvaluate: rule: {}, facts: {}: result: {}", rule, facts, evaluationResult);
    }

    @Override
    public void beforeExecute(Rule rule, Facts facts) {
//        log.info("beforeExecute: rule: {}, facts: {}", rule, facts);
    }

    @Override
    public void onSuccess(Rule rule, Facts facts) {
        log.info("rule: {} SUCCEEDED for facts: {}", rule.getName(), facts);
    }

    @Override
    public void onFailure(Rule rule, Facts facts, Exception exception) {
        log.info("rule: {} FAILED for facts: {}: exp: {}", rule, facts, exception.toString());
    }
}
