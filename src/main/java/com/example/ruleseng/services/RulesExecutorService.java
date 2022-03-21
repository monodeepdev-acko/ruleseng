package com.example.ruleseng.services;

import com.example.ruleseng.models.PbMock;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RulesExecutorService {

    private final RulesEngine rulesEngineSingleton;
//    public Person executeRules(Person person, Boolean legality) {
//
//        Facts facts = new Facts();
//        facts.put("person", person);
//
//        Rules rules;
//        if (legality) {
//            rules = rulesMap.get("legal-person");
//        } else {
//            rules = rulesMap.get("illegal-person");
//        }
//
//        rulesEngine.fire(rules, facts);
//        return person;
//    }

    public PbMock executeRules(Boolean isRaining) {

        PbMock pbMock = new PbMock(100, 5);
        Facts facts = new Facts();
        facts.put("rain", isRaining);
        facts.put("referenceData", pbMock);
        rulesEngineSingleton.fire(new Rules(), facts);
//        Map<Rule, Boolean> results = rulesEngineSingleton.check(rules, facts);
        return pbMock;
    }

//    public PbMock executeRulesOnNewEngine(Boolean isRaining) {
//
//        PbMock pbMock = new PbMock(10000, 500);
//        Facts facts = new Facts();
//        facts.put("rain", isRaining);
//        facts.put("referenceData", pbMock);
//
//        createNewEngines().fire(rules, facts);
//        return pbMock;
//    }
}
