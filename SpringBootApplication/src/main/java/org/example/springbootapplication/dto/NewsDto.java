package org.example.springbootapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.TreeMap;

@AllArgsConstructor
@Getter
@Setter
public class NewsDto {
    public Integer id;
    public String title;
    public String text;
    public Instant date;
}
