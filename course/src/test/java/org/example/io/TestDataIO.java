package org.example.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class TestDataIO {

    @Test
    void testWriteData() {
        String ethalon = "{\"asdf@asdf.com\":{\"email\":\"asdf@asdf.com\",\"password\":\"1111\",\"lists\":{}}}";
        Map<String, User> users = new HashMap<>();
        users.put("asdf@asdf.com", new User("asdf@asdf.com", "1111", new HashMap<>()));
        String data = new Gson().toJson(users, new TypeToken<HashMap<String, User>>() {}.getType());
        assertTrue(ethalon.equals(data));
    }

    @Test
    void testReadData() {
        Map<String, User> ethalon = new HashMap<>();
        ethalon.put("asdf@asdf.com", new User("asdf@asdf.com", "1111", new HashMap<>()));
        String data = "{\"asdf@asdf.com\":{\"email\":\"asdf@asdf.com\",\"password\":\"1111\",\"lists\":{}}}";
        Map<String, User> users = new Gson().fromJson(data, new TypeToken<HashMap<String, User>>() {}.getType());
        System.out.println(users.toString());
        System.out.println(ethalon.toString());
        assertTrue(ethalon.toString().equals(users.toString()));
    }
}
