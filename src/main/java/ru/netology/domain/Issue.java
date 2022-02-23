package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private long time;
    private String name;
    private String author;
    private Set<String> label;
    private Set<String> assignee;
    private Set<String> tag;
    private boolean isOpened;
}
