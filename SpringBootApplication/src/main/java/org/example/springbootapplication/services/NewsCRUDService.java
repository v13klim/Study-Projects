package org.example.springbootapplication.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.springbootapplication.dto.NewsDto;
import org.example.springbootapplication.exception.NewsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Collection;
import java.util.TreeMap;
@Service
public class NewsCRUDService implements CRUDservice<NewsDto>{
    private final TreeMap<Integer, NewsDto> storage = new TreeMap<>();
    private static final Logger log = LogManager.getLogger(NewsCRUDService.class);

    @Override
    public NewsDto getById(Integer id) {
        if (!storage.containsKey(id)) {
            throw new NewsNotFoundException("Новость с ID " + id + " не найдена");
        }
        return storage.get(id);
    }

    @Override
    public Collection<NewsDto> getAll() {
        return storage.values();
    }

    @Override
    public void create(NewsDto item) {
        int nextId = (storage.isEmpty() ? 0 : storage.lastKey()) + 1;
        Instant.now();
        item.setDate(Instant.now());
        item.setId(nextId);
        storage.put(nextId, item);
    }

    @Override
    public void update(Integer id, NewsDto item) {
        if (!storage.containsKey(id)) return;
        item.setId(id);
        item.setDate(Instant.now());
        storage.replace(id, item);
    }

    @Override
    public void delete(Integer id) {
        if (!storage.containsKey(id)) {
            throw new NewsNotFoundException("Новость с ID " + id + " не найдена");
        }
        storage.remove(id);
    }
}
