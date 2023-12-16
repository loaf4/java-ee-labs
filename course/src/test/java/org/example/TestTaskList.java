package org.example;

import org.example.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class TestTaskList {

    @Test
    void testCreatingTaskList() {
        TaskList tl = new TaskList("homework");
        assertTrue(tl.getTitle().equals("homework"));
    }

    @Test
    void testSizeOfTasks() {
        TaskList tl = new TaskList("homework");
        tl.addTask("1");
        tl.addTask("2");
        tl.addTask("3");
        assertTrue(tl.getTasks().size() == 3);
    }
}