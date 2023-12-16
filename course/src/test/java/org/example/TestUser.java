package org.example;

import org.example.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class TestUser {

    @Test
    void testCreatingUser() {
        User u = new User("user@example.com", "12345678");
        assertTrue(u.getEmail().equals("user@example.com") && u.getPassword().equals("12345678"));
    }

    @Test
    void testSizeOfTaskLists() {
        User u = new User("user@example.com", "12345678");
        u.addTaskList("list");
        u.addTaskList("homework");
        assertTrue(u.getLists().size() == 2);
    }
}