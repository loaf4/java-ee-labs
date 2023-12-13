import java.io.*;
import java.util.*;
import java.nio.file.*;

public class PhoneBookModel {

    private Map<String, ArrayList<String>> users = new HashMap<>();

    public synchronized void add(String name, String phone) {
        if (users.containsKey(name)) {
            ArrayList<String> phones = users.get(name);
            if (phones.contains(phone)) { return; }
            phones.add(phone);
            return;
        }
        ArrayList<String> p = new ArrayList<>();
        p.add(phone);
        users.put(name, p);
    }
    public synchronized void deleteUser(String name) {
        if (users.containsKey(name)) {
            users.remove(name);
        }
    }
    public synchronized void deletePhone(String name, String phone) {
        if (users.containsKey(name) && users.get(name).contains(phone)) {
            users.get(name).remove(phone);
        }
    }

    public synchronized void readFile() throws IOException {
        String userDirectory = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .getParent().toString();
        try (BufferedReader bf = new BufferedReader(new FileReader(userDirectory + "/webapps/l13/list.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] info = line.split(" ");
                ArrayList<String> p = new ArrayList<>();
                for (int i = 1; i < info.length; i++) {
                    p.add(info[i]);
                }
                users.put(info[0], p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void saveFile() throws IOException {
        String userDirectory = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .getParent().toString();
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(userDirectory + "/webapps/l13/list.txt"))) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ArrayList<String>> u : users.entrySet()) {
                sb.append(u.getKey().replaceAll(" ", ""));
                for (String phone : u.getValue()) {
                    sb.append(" " + phone);
                }
                sb.append('\n');
                
            }
            bf.write(sb.toString()); 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, ArrayList<String>> getList() {
        return users;
    }
}

