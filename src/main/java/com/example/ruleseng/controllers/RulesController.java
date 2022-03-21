package com.example.ruleseng.controllers;

import com.example.ruleseng.entities.CustomRule;
import com.example.ruleseng.services.RulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
public class RulesController {

    private final RulesService rulesService;

    @GetMapping("/")
    public ResponseEntity<?> getAllRules() {
        return new ResponseEntity(rulesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/channel/{channelName}")
    public ResponseEntity<?> getRule(@PathVariable("channelName") String channelName) {
        return new ResponseEntity(rulesService.findByChannel(channelName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public CustomRule createRule(@RequestBody CustomRule customRule) {
        return rulesService.save(customRule);
    }
}
