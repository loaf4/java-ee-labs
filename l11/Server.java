import java.io.*;
import java.util.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

public class Server {
    private final DatagramSocket dSocket;
    private InetAddress ip;
    private int clientPort;
    private String name = "server";

    public Server(DatagramSocket ds) {
        dSocket = ds;
    }

    public void sendMessage(String mess) {
        try {
            mess = name + ": " + mess;
            byte[] buf = mess.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, clientPort);
            dSocket.send(dp);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        try {
            while(true) {
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                dSocket.receive(dp);
                ip = dp.getAddress();
                clientPort = dp.getPort();

                String mess = new String(buf, 0, buf.length);
                System.out.println(mess);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid arguments");
            System.exit(-1);
        }

        try {
            DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));
            Server server = new Server(ds);

            Thread sending = new Thread(() -> {
                Scanner scan = new Scanner(System.in);
                while(true) {
                    String mess = scan.nextLine();
                    if (mess.startsWith("@quit")) {
                        System.out.println("Chat stopped...");
                        break;
                    } else if (mess.startsWith("@cd")) {
                        if (mess.split(" ").length != 2) {
                            System.out.println("usage: @cd <dirname>");
                        } else {
                            server.sendMessage(mess);
                        }
                    } else {
                        server.sendMessage(mess);
                    }
                }
                if(!ds.isClosed()) {
                    ds.close();
                }
                scan.close();
                System.exit(0);
            });


            sending.start();
            server.receiveMessage();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
