import java.io.*;
import java.net.Socket;

public class Network {

    private static final int PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private static Network instance;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    private Network() {
        try {
            socket = new Socket("localhost", PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Problem with server on port: 8189");
        }
    }

    public void writeMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    public String readMessage() throws IOException {
        return in.readUTF();
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    public void login(String login, String encPass) throws IOException{
        String message = "/log " + login + " " + encPass;
        System.out.println(message);
        out.writeUTF(message);
        out.flush();
    }

    public void register(String login, String encPass) throws IOException{
        String message = "/reg " + login + " " + encPass;
        System.out.println(message);
        out.writeUTF(message);
        out.flush();
    }
}