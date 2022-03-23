package com.example.ruleseng.services;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.listeners.DynamicallyAttachRulesListener;
import com.example.ruleseng.listeners.MyRuleListener;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class RulesExecutorService {

    private final RuleConverter ruleConverter;
    private ConcurrentMap<String, RulesEngine> ruleEngineStore = null;
    private final DynamicallyAttachRulesListener dynamicallyAttachRulesListener;
    private final RulesService rulesService;

    private final String BUY_JOURNEY_KEY = "buy_journey_engine";
    private final String ENDORSEMENT_JOURNEY_KEY = "endorsement_journey_engine";
    private final String RENEWAL_JOURNEY_KEY = "renewal_journey_engine";

    @PostConstruct
    public void initializeRuleEngines() {

        ruleEngineStore = new ConcurrentHashMap<>();

        DefaultRulesEngine buyJourneyRulesEngine = new DefaultRulesEngine();
        buyJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        buyJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(BUY_JOURNEY_KEY, buyJourneyRulesEngine);

        DefaultRulesEngine endorsementJourneyRulesEngine = new DefaultRulesEngine();
        endorsementJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        endorsementJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(ENDORSEMENT_JOURNEY_KEY, endorsementJourneyRulesEngine);

        DefaultRulesEngine renewalJourneyRulesEngine = new DefaultRulesEngine();
        renewalJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        renewalJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(RENEWAL_JOURNEY_KEY, renewalJourneyRulesEngine);
    }

    public RulesEngine getRulesEngine(String key) {
        return ruleEngineStore.get(key);
    }

    public Rules registerDatabaseRules(String channel) {
        Rules rules = new Rules();

        for (CustomRule customRule : rulesService.findByChannel(channel)) {
            rules.register(ruleConverter.convertToRule(customRule));
        }
        return rules;
    }

    public Facts createFacts(Object contextObj) {
        Facts facts = new Facts();
        facts.put("context", contextObj);
        return facts;
    }

    public Object execute(String channel, Object contextObj) {
        Facts facts = createFacts(contextObj);
        Rules rules = registerDatabaseRules(channel);
        getRulesEngine(channel).fire(rules, facts);
        return contextObj;
    }
}
