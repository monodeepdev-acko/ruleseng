package com.example.ruleseng.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RuleEngineConfig {

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
