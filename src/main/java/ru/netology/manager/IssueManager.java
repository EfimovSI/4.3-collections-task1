package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueManager {
    private IssueRepository repository = new IssueRepository();

    public void add(Issue issue) {
        repository.add(issue);
    }

    public void addAll(Collection<Issue> issues) {
        repository.addAll(issues);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Collection<Issue> findOpened() {
        var openedIssues = repository.findALL().stream().filter(Issue::isOpened);
        return openedIssues.collect(Collectors.toCollection(ArrayList::new));
//        Collection<Issue> issues = repository.findALL();
//        Collection<Issue> openedIssues = new ArrayList<>();
//        for (Issue issue : issues) {
//            if (issue.isOpened()) {
//                openedIssues.add(issue);
//            }
//        }
//        return openedIssues;
    }

    public Collection<Issue> findClosed() {
        var openedIssues = repository.findALL().stream().filter(issue -> !issue.isOpened());
        return openedIssues.collect(Collectors.toCollection(ArrayList::new));
//        Collection<Issue> issues = repository.findALL();
//        Collection<Issue> closedIssues = new ArrayList<>();
//        for (Issue issue : issues) {
//            if (!issue.isOpened()) {
//                closedIssues.add(issue);
//            }
//        }
//        return closedIssues;
    }

    public Collection<Issue> filterByAuthor(String author) {
        var openedIssues = repository.findALL().stream().filter(issue -> issue.getAuthor().contains(author));
        return openedIssues.collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Issue> filterByLabel(String label) {
        var openedIssues = repository.findALL().stream().filter(issue -> issue.getLabel().contains(label));
        return openedIssues.collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        var openedIssues = repository.findALL().stream().filter(issue -> issue.getAssignee().contains(assignee));
        return openedIssues.collect(Collectors.toCollection(ArrayList::new));
    }
}
