package com.example.ruleseng.services;

import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.repositories.CustomRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<CustomRule> saveAll(List<CustomRule> rules) {
        List<CustomRule> results = new ArrayList<>();
        for (CustomRule rule: rules) {
            results.add(ruleRepository.save(rule));
        }
        return results;
    }

    public void delete(String name) {
        ruleRepository.deleteById(name);
    }
}
