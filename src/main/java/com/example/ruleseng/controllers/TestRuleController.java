package com.example.ruleseng.controllers;

import com.example.ruleseng.services.RulesExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class TestRuleController {

    private final RulesExecutorService rulesExecutorService;

//    @PostMapping(value = "/is-it-raining/{isRaining}")
//    public ResponseEntity<?> justify(@PathVariable("isRaining") Boolean isRaining) {
//        return new ResponseEntity(rulesExecutorService.executeRules(isRaining), HttpStatus.OK);
//    }
}
