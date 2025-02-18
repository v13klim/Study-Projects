package org.example.springbootapplication.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.internal.ErrorMessages;
import org.example.springbootapplication.dto.NewsDto;
import org.example.springbootapplication.exception.ErrorNews;
import org.example.springbootapplication.exception.NewsNotFoundException;
import org.example.springbootapplication.services.NewsCRUDService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.Collection;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsCRUDService newsCRUDService;
    public NewsController(NewsCRUDService newsCRUDService) {
        this.newsCRUDService = newsCRUDService;
    }

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Integer id) {
        return newsCRUDService.getById(id);
    }
    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorNews> handleException(NewsNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorNews(exception.getMessage()));
    }

    @GetMapping
    public Collection<NewsDto> getAllNews() {
        return newsCRUDService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto createNews(@RequestBody NewsDto newsDto) {
        newsCRUDService.create(newsDto);
        return newsDto;
    }

    @PutMapping("/{id}")
    public void updateNews(@PathVariable Integer id, @RequestBody NewsDto newsDto) {
        newsCRUDService.update(id, newsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Integer id) {
        newsCRUDService.delete(id);
    }

}
