package com.example.ruleseng.listeners;

import com.example.ruleseng.services.RulesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppendRulesFromDbListener implements RulesEngineListener {

    private final RulesService rulesService;

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {
        log.info("about to register");
        if (!Objects.isNull(facts.get("use-db-rules")) && facts.get("use-db-rules") == Boolean.TRUE) {
            rulesService.registerChannelRules("test-channel");
        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {
        rulesService.unregisterChannelRules("test-channel");
    }
}
