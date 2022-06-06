package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkiResortService {
    private final SkiResortRepository repository;

    @Autowired
    public SkiResortService(SkiResortRepository repository) {
        this.repository = repository;
    }

    public Optional<SkiResort> get(UUID id) {
        return repository.findById(id);
    }

    public Optional<SkiResort> get(String name) {
        return repository.findSkiResortByName(name);
    }

    public SkiResort update(SkiResort entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<SkiResort> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<SkiResort> getAllSkiResort() { return repository.findAll(); }

    public int count() {
        return (int) repository.count();
    }
}
