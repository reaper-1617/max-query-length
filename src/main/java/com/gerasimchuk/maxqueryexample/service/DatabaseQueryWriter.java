package com.gerasimchuk.maxqueryexample.service;

import com.gerasimchuk.maxqueryexample.domain.ExampleEntity;
import com.gerasimchuk.maxqueryexample.domain.ExampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseQueryWriter {

    private final ExampleRepository exampleRepository;

    @Value("${query.generation.size}")
    private int n;

    @Transactional
    @EventListener(ContextRefreshedEvent.class)
    public void generateAndSave() {
        log.info("Generating query for size {}", n);
        log.info("Deleting previous data ...");
        exampleRepository.deleteAllInBatch();
        log.info("Previous data deleted successfully");
        log.info("Generating new data ...");
        var list = new ArrayList<ExampleEntity>();
        for (int i = 0; i < n; i++) {
            var e = ExampleEntity.builder()
                    .title(UUID.randomUUID().toString())
                    .build();
            list.add(e);
        }
        log.info("Data generated, saving ...");
        exampleRepository.saveAllAndFlush(list);
        log.info("{} entities saved successfully", list.size());
    }

    @Transactional(readOnly = true)
    public void generateQueryAndFetch(int n) {
        log.info("Generating fetch query for {}", n);
        var titles = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            titles.add(UUID.randomUUID().toString());
        }
        var found = exampleRepository.findAllByTitleIn(titles);
        log.info("Found {} entities after query execution", found.size());
    }
}
