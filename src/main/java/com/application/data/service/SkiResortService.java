package com.application.data.service;

import com.application.data.entity.SkiResort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkiResortService {
    private final SkiResortRepository repository;

    @Autowired
    public SkiResortService(SkiResortRepository repository) {
        this.repository = repository;
    }

    public Optional<SkiResort> get(Integer id) {
        return repository.findById(id);
    }

    public Optional<SkiResort> get(String name) {
        return repository.findSkiResortByName(name);
    }

    public SkiResort update(SkiResort entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<SkiResort> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<SkiResort> getAllSkiResort() {
        return repository.findAll();
    }

    public List<SkiResort> findAllSkiResort(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filterText);
        }
    }

    public int count() {
        return (int) repository.count();
    }
}
