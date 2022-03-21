package com.example.ruleseng.controllers;

import com.example.ruleseng.models.Person;
import com.example.ruleseng.services.RulesExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestRuleController {

    private final RulesExecutorService rulesExecutorService;

//    @PostMapping(value = "/check-drinking-status/{legality}")
//    public ResponseEntity<?> process(@RequestBody Person person, @PathVariable("legality") Boolean legality) {
//        return new ResponseEntity(rulesExecutorService.executeRules(person, legality), HttpStatus.OK);
//    }

    @PostMapping(value = "/is-it-raining/{isRaining}")
    public ResponseEntity<?> justify(@PathVariable("isRaining") Boolean isRaining) {
        return new ResponseEntity(rulesExecutorService.executeRules(isRaining), HttpStatus.OK);
    }
}
