package com.example.ruleseng.services;

import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.repositories.CustomRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RulesService {

    private final CustomRuleRepository ruleRepository;

    public List<CustomRule> findAll() {

        List<CustomRule> rules = new ArrayList<>();
        for (CustomRule rule : ruleRepository.findAll()) {
            rules.add(rule);
        }
        return rules;
    }

    public List<CustomRule> findByChannel(String channel) {

        List<CustomRule> rules = new ArrayList<>();
        for (CustomRule rule : ruleRepository.findByChannel(channel)) {
            rules.add(rule);
        }
        return rules;
    }

    public CustomRule findById(String id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public CustomRule findByName(String name) {
        return ruleRepository.findByName(name).orElse(null);
    }

    public List<CustomRule> saveAll(List<CustomRule> rules) {
        List<CustomRule> results = new ArrayList<>();
        for (CustomRule rule: rules) {
            rule.setId(UUID.randomUUID().toString());
            results.add(ruleRepository.save(rule));
        }
        return results;
    }

    public void delete(Map<String, String> filterParams) {

        if (Objects.isNull(filterParams) || filterParams.size() == 0) {
            ruleRepository.deleteAll();
        } else if (filterParams.containsKey("id")) {
            ruleRepository.deleteById(findById(filterParams.get("id")).getId());
        } else if (filterParams.containsKey("name")) {
            ruleRepository.deleteById(findById(filterParams.get("name")).getId());
        }
    }
}
