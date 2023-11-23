import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {
    private List<Socket> clients = new ArrayList<>();
    private Map<String, Socket> users = new HashMap<>();
    private Map<Socket, String> invertUsers = new HashMap<>();
    private Map<String, ArrayList<String>> blackList = new HashMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid arguments");
            return;
        }
        int p = Integer.parseInt(args[0]);
        new TCPServer().runServer(p);
    }

    public void runServer(int p) {
        try (ServerSocket serverSocket = new ServerSocket(p)) {
            System.out.println("Server started on port " + p);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);

                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String clientName = in.readLine();
            users.put(clientName, clientSocket);
            invertUsers.put(clientSocket, clientName);
            blackList.put(clientName, new ArrayList<>());
            System.out.println("Client connected: " + clientName);
            sendAll("*SERVER*", clientName + " has been joined the chat");

            String mess;
            while ((mess = in.readLine()) != null) {
                if (mess.startsWith("@senduser")) {
                    String[] parts = mess.split(" ", 3);
                    String recipient = parts[1];
                    sendUser(clientName, recipient, parts[2]);
                } else if (mess.startsWith("@blacklist")) {
                    String[] parts = mess.split(" ");
                    String recipient = parts[1];
                    ArrayList<String> bUsers = blackList.get(clientName);
                    if (bUsers != null && !bUsers.contains(recipient)) {
                        bUsers.add(recipient);
                    }
                } else {
                    sendAll(clientName, mess);
                }
            }

            clients.remove(clientSocket);
            users.remove(clientName);
            invertUsers.remove(clientSocket);
            blackList.remove(clientName);
            System.out.println("Client disconnected: " + clientName);
            sendAll("*SERVER*", clientName + " has been leaved the chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAll(String sender, String mess) {
        Socket passClient = users.get(sender);
        for (Socket client : clients) {
            String recipient = invertUsers.get(client);
            ArrayList<String> bUsers = blackList.get(recipient);
            if (bUsers != null && bUsers.contains(sender)) {
                continue;
            }
 
            if (passClient == client) {
                continue;
            }
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(sender + ": " + mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendUser(String sender, String recipient, String mess) {
        Socket client = users.get(recipient);
        ArrayList<String> bUsers = blackList.get(recipient);
        if (bUsers != null && bUsers.contains(sender)) {
            return;
        }
        if (client != null) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(sender + "(private): " + mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
