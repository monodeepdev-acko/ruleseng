package com.example.ruleseng.controllers;

import com.example.ruleseng.services.RulesExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class TestRuleController {

    private final RulesExecutorService rulesExecutorService;

    @PostMapping(value = "/test-rule/{channel}")
    public ResponseEntity<?> justify(@PathVariable("channel") String channel, @RequestBody Map<String, Object> context) {
        return new ResponseEntity(rulesExecutorService.execute(channel, context),
                HttpStatus.OK);
    }
}
