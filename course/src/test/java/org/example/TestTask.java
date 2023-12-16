package org.example;

import org.example.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class TestTask {

    @Test
    void testCreatingTaskList() {
        Task t = new Task("make homework");
        assertTrue(t.getText().equals("make homework"));
    }

    @Test
    void testSizeOfTasks() {
        Task t = new Task("make homework");
        t.addSubTask("math");
        t.addSubTask("biology");
        t.addSubTask("geography");
        assertTrue(t.getSubTasks().size() == 3);
    }
}