import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid arguments");
            return;
        }
 
        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            out.println(name);

            Thread serverThread = new Thread(() -> {
                String message;
                try {
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();

            String mess;
            while (true) {
                mess = scanner.nextLine();
                if (mess.startsWith("@quit")) {
                    System.out.println("*SERVER*: you has been leaved the chat");
                    System.exit(0);
                }
                out.println(mess);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
