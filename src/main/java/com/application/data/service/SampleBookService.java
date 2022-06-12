package com.application.data.service;

import com.application.data.entity.SampleBook;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SampleBookService {

    private final SampleBookRepository repository;

    @Autowired
    public SampleBookService(SampleBookRepository repository) {
        this.repository = repository;
    }

    public Optional<SampleBook> get(Integer id) {
        return repository.findById(id);
    }

    public SampleBook update(SampleBook entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<SampleBook> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
