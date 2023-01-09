package com.gerasimchuk.maxqueryexample.controller;

import com.gerasimchuk.maxqueryexample.service.DatabaseQueryWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
public class ExampleController {

    private final DatabaseQueryWriter databaseQueryWriter;

    @PostMapping
    public void makeQuery(@RequestParam int n){
        databaseQueryWriter.generateQueryAndFetch(n);
    }
}
