import java.io.*;
import java.util.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.nio.file.Paths;

public class Client {
    private final DatagramSocket dSocket;
    private final InetAddress ip;
    private final int serverPort;
    private String clientName = "client";
    
    private String dirname;

    public Client(DatagramSocket ds, InetAddress ia, int sp) {
        dSocket = ds;
        ip = ia;
        serverPort = sp;
        dirname = Paths.get("").toAbsolutePath().toString();
    }

    public void sendMessage(String mess) {
        try {
            mess = clientName + ": " + mess;
            byte[] buf = mess.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, serverPort);
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
                String mess = new String(buf, 0, buf.length);

                String[] cmd = mess.split(" ");
                if(cmd[1].startsWith("@pwd")) {
                    sendMessage(get_pwd());
                } else if(cmd[1].startsWith("@ls")) {
                    sendMessage(get_ls());
                } else if(cmd[1].startsWith("@cd")) {
                    sendMessage(set_cd(cmd[2]));
                } else {
                    System.out.println(mess);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();
        }
    }

    public void changeName(String name) {
        clientName = name;
    }

    public String get_pwd() {
        return dirname;
    }

    public String get_ls() {
        StringBuilder res = new StringBuilder();
        File f = new File(dirname);
        String[] curFiles = f.list();
        for (int i = 0; i < curFiles.length; ++i) {
            res.append(curFiles[i] + " ");
        }
        return res.toString();
    }

    public String set_cd(String path) {
        path = path.trim();
        String[] parts = path.split("/");
        String dirCopy = dirname;
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].equals("") || parts[i].equals(".")) {
                continue;
            } else if (parts[i].equals("..")) {
                String[] tmpDir = dirCopy.split("/");
                dirCopy = String.join("/", Arrays.copyOfRange(tmpDir, 0, tmpDir.length - 1));
            } else {
                dirCopy += "/" + parts[i];
            }
        }
        dirCopy = dirCopy.trim();
        File f = new File(dirCopy);
        System.out.println(dirCopy);
        if (f.exists() && f.isDirectory()) {
            dirname = dirCopy;
            return "<OK>: directory changed";
        }
        return "<error>: unknown directory";
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments");
            System.exit(-1);
        }

        try {
            DatagramSocket ds = new DatagramSocket();
            Client client = new Client(ds, InetAddress.getByName(args[0]), Integer.parseInt(args[1]));

            Thread sending = new Thread(() -> {
                Scanner scan = new Scanner(System.in);
                while(true) {
                    String mess = scan.nextLine();
                    if (mess.startsWith("@quit")) {
                        System.out.println("Chat stopped...");
                        break;
                    } else if (mess.startsWith("@name")) {
                        String newName = mess.split(" ")[1];
                        if (newName.isBlank()) {
                            System.err.println("Name is not valid");
                            continue;
                        }
                        client.changeName(newName);
                        System.out.println("Name changed on " + newName);
                    } else {
                        client.sendMessage(mess);
                    }
                }
                if(!ds.isClosed()) {
                    ds.close();
                }
                scan.close();
                System.exit(0);
            });


            sending.start();
            client.receiveMessage();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
