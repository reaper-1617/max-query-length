package com.gerasimchuk.maxqueryexample.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
    List<ExampleEntity> findAllByTitleIn(List<String> titles);
}
