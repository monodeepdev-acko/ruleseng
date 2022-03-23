package com.example.ruleseng.controllers;

import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.services.RulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
public class RulesController {

    private final RulesService rulesService;

    @GetMapping("/")
    public ResponseEntity<?> getAllRules(@RequestParam Map<String, String> requestParams) {

        List<CustomRule> results = new ArrayList<>();
        if (Objects.isNull(requestParams)) {
            results = rulesService.findAll();
        } else if (requestParams.containsKey("name")) {
            results.add(rulesService.findByName(requestParams.get("name")));
        } else if (requestParams.containsKey("channel")) {
            results.addAll(rulesService.findByChannel(requestParams.get("channel")));
        } else if (requestParams.containsKey("id")) {
            results.add(rulesService.findById(requestParams.get("id")));
        }
        return new ResponseEntity(rulesService, HttpStatus.OK);
    }

    @PostMapping("/")
    public List<CustomRule> createRule(@RequestBody List<CustomRule> customRules) {
        return rulesService.saveAll(customRules);
    }
}
