package com.example.ruleseng.converter;

import com.example.ruleseng.entities.CustomRule;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RuleConverter {

    public Rule convertToRule(CustomRule customRule) {
        return new MVELRule()
                .name(customRule.getName())
                .description(String.format("channel: %s, description: %s",
                                customRule.getChannel(),
                                customRule.getDescription()))
                .priority(customRule.getPriority())
                .when(customRule.getCondition())
                .then(customRule.getAction());
    }

}
