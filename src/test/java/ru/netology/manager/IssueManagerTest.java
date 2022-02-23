package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    Issue first = new Issue(1, 1200, "First", "John", new HashSet<>(List.of("status: new")),
            new HashSet<>(List.of("Paul")), new HashSet<>(List.of("v1.1")), true);
    Issue second = new Issue(2, 1400, "Second", "John", new HashSet<>(List.of("theme: build")),
            new HashSet<>(List.of("Nikolay")), new HashSet<>(List.of("v3.0")), true);
    Issue third = new Issue(3, 1000, "Third", "Alex", new HashSet<>(List.of("status: discussion")),
            new HashSet<>(List.of("Sergey")), new HashSet<>(List.of("fixed")), true);
    Issue fourth = new Issue(4, 1130, "Fourth", "Kyle", new HashSet<>(List.of("status: invalid")),
            new HashSet<>(List.of("Lisa")), new HashSet<>(List.of("added")), false);
    Issue fifth = new Issue(5, 900, "Fifth", "Alice", new HashSet<>(List.of("theme: build")),
            new HashSet<>(List.of("Paul")), new HashSet<>(List.of("v1.1")), false);
    Issue sixth = new Issue(6, 1800, "Sixth", "Robin", new HashSet<>(List.of("type: task")),
            new HashSet<>(List.of("Paul", "Lisa")), new HashSet<>(List.of("abc")), false);

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    @BeforeEach
    public void setUp() {
        manager.addAll(List.of(first, second, third, fourth, fifth, sixth));
    }

    @Test
    void ShouldAddToEmpty() {
        IssueRepository repository1 = new IssueRepository();
        IssueManager manager = new IssueManager(repository1);

        manager.add(second);

        assertArrayEquals(new Issue[]{second}, repository1.findALL().toArray());
    }
    @Test
    void ShouldRemoveByIdWhenExist() {
        manager.removeById(2);

        assertArrayEquals(new Issue[]{first, third, fourth, fifth, sixth}, repository.findALL().toArray());
    }

    @Test
    void ShouldRemoveByIdWhenNotExist() {
        manager.removeById(10);

        assertArrayEquals(new Issue[]{first, second, third, fourth, fifth, sixth}, repository.findALL().toArray());
    }

    @Test
    void shouldFindOpened() {
        Collection<Issue> expected = new ArrayList();
        Collections.addAll(expected, first, second, third);

        assertEquals(expected, manager.findOpened());
    }

    @Test
    void shouldFindClosed() {
        Collection<Issue> expected = new ArrayList();
        Collections.addAll(expected, fourth, fifth, sixth);

        assertEquals(expected, manager.findClosed());
    }

    @Test
    void shouldFilterByAuthorWhenExist() {
        Collection<Issue> expected = new ArrayList();
        Collections.addAll(expected, first, second);

        assertEquals(expected, manager.filterByAuthor("John"));
    }

    @Test
    void shouldFilterByAuthorWhenNotExist() {
        assertArrayEquals(new Issue[0], manager.filterByAuthor("Sebastian").toArray());
    }

    @Test
    void shouldFilterByLabelWhenExist() {
        Collection<Issue> expected = new ArrayList();
        Collections.addAll(expected, second, fifth);

        assertEquals(expected, manager.filterByLabel("theme: build"));
    }

    @Test
    void shouldFilterByLabelWhenNotExist() {
        assertArrayEquals(new Issue[0], manager.filterByLabel("closed").toArray());
    }

    @Test
    void shouldFilterByAssigneeWhenExist() {
        Collection<Issue> expected = new ArrayList();
        Collections.addAll(expected, fourth, sixth);

        assertEquals(expected, manager.filterByAssignee("Lisa"));
    }

    @Test
    void shouldFilterByAssigneeWhenNotExist() {
        assertArrayEquals(new Issue[0], manager.filterByAssignee("Leo").toArray());
    }
}