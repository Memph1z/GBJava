import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;
    public static Timestamp timestamp;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    public String getNickName() {
        return nickName;
    }

    private String nickName;
    private static int cnt = 0;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
        cnt++;
        nickName = "user" + cnt;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("[DEBUG] client start processing");
            server.broadCastMessage(nickName + " entered the chat");
            timestamp = new Timestamp(System.currentTimeMillis());
            while (running) {
                String msg = in.readUTF();
                if (msg.matches("\\S+")) {
                    if (msg.equals("/quit")) {
                        out.writeUTF(msg);
                    } else if (msg.contains("/w ")) {
                        try {
                            server.sendMessageTo(nickName, msg);
                        } catch (Exception e) {
                            System.err.println("User not found");
                        }
                    } else {
                        server.broadCastMessage(sdf.format(timestamp) + " " + nickName + ": " + msg);
                    }
                    System.out.println("[DEBUG] message from client: " + msg);
                }
            }
        } catch (Exception e) {
            System.err.println("Handled connection was broken");
            server.removeClient(this);
            try {
                server.broadCastMessage(nickName + " left the chat");
            } catch (IOException ioException) {
                System.err.println("No users to send message to");
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}