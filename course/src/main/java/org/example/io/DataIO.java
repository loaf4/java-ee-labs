package org.example.io;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.*;

public class DataIO {

    public static Map<String, User> readData() {
        try (BufferedReader bf = new BufferedReader(new FileReader("/home/loaf/Downloads/apache-tomcat-9.0.82/webapps/course/src/main/resources/data.txt"))) {
            String data = bf.readLine();
            Map<String, User> users = new Gson().fromJson(data, new TypeToken<HashMap<String, User>>() {}.getType());
            return users == null ? new HashMap<>() : users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeData(Map<String, User> users) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("/home/loaf/Downloads/apache-tomcat-9.0.82/webapps/course/src/main/resources/data.txt"))) {
            String data = new Gson().toJson(users, new TypeToken<HashMap<String, User>>() {}.getType());
            bf.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}