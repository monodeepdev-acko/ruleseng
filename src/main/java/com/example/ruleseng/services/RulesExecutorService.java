package com.example.ruleseng.services;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.listeners.DynamicallyAttachRulesListener;
import com.example.ruleseng.listeners.MyRuleListener;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class RulesExecutorService {

    private final RuleConverter ruleConverter;
    private ConcurrentMap<String, RulesEngine> ruleEngineStore = null;
    private final DynamicallyAttachRulesListener dynamicallyAttachRulesListener;
    private final RulesService rulesService;

    private final String BUY_ENGINE_KEY = "buy_engine_engine";
    private final String ENDORSEMENT_ENGINE_KEY = "endorsement_engine_engine";
    private final String RENEWAL_ENGINE_KEY = "renewal_engine_engine";

    private final String CONTEXT_KEY = "context";
    private final String RESULTS_KEY = "results";
    private final String ADDITIONAL_RULES = "additional_rules";


    @PostConstruct
    public void initializeRuleEngines() {

        ruleEngineStore = new ConcurrentHashMap<>();

        DefaultRulesEngine buyJourneyRulesEngine = new DefaultRulesEngine();
//        buyJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        buyJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(BUY_ENGINE_KEY, buyJourneyRulesEngine);

        DefaultRulesEngine endorsementJourneyRulesEngine = new DefaultRulesEngine();
        endorsementJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        endorsementJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(ENDORSEMENT_ENGINE_KEY, endorsementJourneyRulesEngine);

        DefaultRulesEngine renewalJourneyRulesEngine = new DefaultRulesEngine();
//        renewalJourneyRulesEngine.registerRulesEngineListener(dynamicallyAttachRulesListener);
        renewalJourneyRulesEngine.registerRuleListener(new MyRuleListener());
        ruleEngineStore.putIfAbsent(RENEWAL_ENGINE_KEY, renewalJourneyRulesEngine);
    }

    public RulesEngine getRulesEngine(String key) {
        return ruleEngineStore.get(key);
    }

    public Facts createFacts(Map<String, Object> contextObj) {
//    public Facts createFacts(Context contextObj) {
        Facts facts = new Facts();
        facts.put(CONTEXT_KEY, contextObj);
        facts.put(RESULTS_KEY, new HashMap<>());

        if (contextObj.containsKey(ADDITIONAL_RULES)) {
            facts.put(ADDITIONAL_RULES, contextObj.get(ADDITIONAL_RULES));
        }

        return facts;
    }

    public Rules registerRules(String channel) {
        Rules rules = new Rules();
        List<CustomRule> customRuleList = rulesService.findByChannel(channel);
        for (CustomRule customRule : customRuleList) {
            Rule rule = ruleConverter.convertToRule(customRule);
            rules.register(rule);
        }
        return rules;
    }

    public Object execute(String channel, Map<String, Object> contextObj) {
//    public Object execute(String channel, Context contextObj) {
        Facts facts = createFacts(contextObj);
        Rules rules = registerRules(channel);
        RulesEngine engine = getRulesEngine(channel);
        if (!Objects.isNull(engine))
            engine.fire(rules, facts);
        return contextObj;
    }
}
