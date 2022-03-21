package com.example.ruleseng.config;

import com.example.ruleseng.converter.RuleConverter;
import com.example.ruleseng.listeners.AppendRulesFromDbListener;
import com.example.ruleseng.listeners.PreAttachDynamicRulesListener;
import com.example.ruleseng.listeners.MyRuleListener;
import com.example.ruleseng.services.RulesService;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RuleEngineConfig {

    private final RuleConverter ruleConverter;
    private final RulesService rulesService;

    @Bean
    public PreAttachDynamicRulesListener getRuleEngineListener() {
        return new PreAttachDynamicRulesListener(ruleConverter);
    }

    @Bean
    public AppendRulesFromDbListener getDbRuleEngineListener() {
        return new AppendRulesFromDbListener(rulesService);
    }

    @Bean
    public RulesEngine rulesEngine() {
        DefaultRulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.registerRuleListener(new MyRuleListener());
        rulesEngine.registerRulesEngineListener(getRuleEngineListener()); // for dynamic attaching of rules

        // attach the pre-existing rules from DB here just at the moment of creation of the rules engine
        return rulesEngine;
    }

    @Bean
    public Rules getRules() {
        return new Rules();
    }

//    @Bean
//    public Map<String, Rules> getRulesMap() throws Exception {
//        Map<String, Rules> rulesMap = new HashMap<>();
//
//        BufferedReader bufferedReader;
//
//        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
//
//        for (String channel :getChannelsFromAnySource()) {
//
//            String ruleFilePath = String.format("classpath:rules/%s-rules.json", channel);
//            bufferedReader = new BufferedReader(
//                    new FileReader(ResourceUtils.getFile(ruleFilePath)));
//            Rules rules = ruleFactory.createRules(bufferedReader);
//            rulesMap.put(channel, rules);
//            bufferedReader.close();
//        }
//        return rulesMap;
//    }

//    // Read channels information from any given source
//    private String[] getChannelsFromAnySource() {
//        return channels;
//    }
}
