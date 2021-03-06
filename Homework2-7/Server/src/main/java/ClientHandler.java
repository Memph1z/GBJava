import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ClientHandler implements Runnable {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement getNick;
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;
    public static Timestamp timestamp;
    private String nickToCheck;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
                    } else if (msg.startsWith("/w ")) {
                        try {
                            server.sendMessageTo(nickName, msg);
                        } catch (Exception e) {
                            System.err.println("User not found");
                        }
                    } else if (msg.startsWith("/log ")) {
                        System.out.println(msg);
                        nickToCheck = checkLogin(msg);
                        if (nickToCheck != null){
                            out.writeUTF("200 Success");
                        } else {
                            out.writeUTF("403 Forbidden");
                        }
//                    } else if (msg.startsWith("/cn ")) {
//                        nickToCheck = checkLogin(msg);
//                        if (nickToCheck != null){
//                            out.writeUTF("Nickname already taken");
//                        } else {
//                            changeNick(msg);
//                        }
                    } else if (msg.startsWith("/reg ")) {
                        System.out.println(msg);
                        nickToCheck = checkLogin(msg);
                        if (nickToCheck == null){
                            if (register(msg)){
                                out.writeUTF("200 Success");
                            } else {
                                out.writeUTF("409 Conflict");
                            }
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

    private String checkLogin(String msg) {
        connection = Server.getConnection();
        String [] message = msg.split(" ", 3);
        String nick = null;
        try {
            getNick = connection.prepareStatement("SELECT nick FROM Users WHERE login = ? AND pass = ?;");
            getNick.setString(1, message[1]);
            getNick.setString(2, message[2]);
            ResultSet rs = getNick.executeQuery();
            nick = rs.getString("nick");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nick;
    }

    private boolean register(String msg) throws IOException {
        connection = Server.getConnection();
        String [] message = msg.split(" ", 3);
        String nick = null;
        try {
            getNick = connection.prepareStatement("INSERT into Users (nick, pass, login) VALUES (?, ?, ?)");
            getNick.setString(1, message[1]);
            getNick.setString(2, message[2]);
            getNick.setString(3, message[1]);
            ResultSet rs = getNick.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
            return true;
    }

    private void changeNick(String msg){
        connection = Server.getConnection();
        String [] message = msg.split(" ", 3);
        try {
            getNick = connection.prepareStatement("UPDATE Users SET nick = ? WHERE nick = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}