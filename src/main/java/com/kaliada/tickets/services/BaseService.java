package com.kaliada.tickets.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public abstract class BaseService<T, R extends JpaRepository<T, Long>> {

    protected String defaultSortingField = "id";
    protected String defaultSortingDirection = "asc";

    @Autowired
    private R repository;

    public T getEntityById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
