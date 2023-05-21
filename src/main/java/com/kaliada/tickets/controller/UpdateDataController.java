package com.kaliada.tickets.controller;

import com.kaliada.tickets.services.impl.RyanairService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class UpdateDataController {

    private final RyanairService ryanairService;

    @GetMapping("/update")
    public void updateData() {
        ryanairService.updateData();
    }
}
