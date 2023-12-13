package org.example.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.*;

public class DataIO {

    public static void readData(Map<String, User> users) {
        try (BufferedReader bf = new BufferedReader(new FileReader("../../../resources/data.txt"))) {
            users = new GsonBuilder().create().fromJson(bf, new TypeToken<HashMap<String, User>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(Map<String, User> users) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../../../resources/data.txt"))) {
            bf.write(new GsonBuilder().create().toJson(bf, new TypeToken<HashMap<String, User>>() {}.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//user :: pass :: home ~~ homework $$ math ## history %% task || work ~~ project
//|   info    |  |list| |task  | |subtasks    |