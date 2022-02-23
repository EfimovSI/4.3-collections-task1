package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueRepository {
    private Collection<Issue> issues = new ArrayList();

    public void add(Issue issue) {
        issues.add(issue);
    }

    public Collection<Issue> findALL() {
        return issues;
    }

    public void removeById(int id) {
        issues.removeIf(e -> e.getId() == id);
    }

    public void addAll(Collection<Issue> issues) {
        this.issues.addAll(issues);
    }
}
