import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.sql.*;


public class Server {
    private static Connection connection;
    private static Statement statement;


    private static final int DEFAULT_PORT = 8189;

    private ConcurrentLinkedDeque<ClientHandler> clients;


    public Server(int port) throws ClassNotFoundException {
        if (!dbConnect()) {
            throw new RuntimeException("Не удалось подключиться к БД!");
        }
        clients = new ConcurrentLinkedDeque<>();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("[DEBUG] server started on port: " + port);
            while (true) {
                Socket socket = server.accept(); // get connection
                System.out.println("[DEBUG] client accepted");
                ClientHandler handler = new ClientHandler(socket, this);
                addClient(handler);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            System.err.println("Server was broken");
        } finally {
            dbDisconnect();
            System.out.println("DB connection broken");
        }
    }

    public void addClient(ClientHandler clientHandler){
        clients.add(clientHandler);
        System.out.println("[DEBUG] client added to broadcast queue");
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
        System.out.println("[DEBUG] client removed from broadcast queue");
    }

    public void broadCastMessage(String msg) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    public void sendMessageTo(String nickname, String msg) throws IOException {
        String nick = cutNick(msg);
        for (ClientHandler recipient : clients){
            if (recipient.getNickName().equals(nick)){
                recipient.sendMessage(ClientHandler.sdf.format(ClientHandler.timestamp) + " " + "from " + nickname + ": " + cutMsg(msg));
                for (ClientHandler sender : clients){
                    if (sender.getNickName().equals(nickname)){
                        sender.sendMessage(ClientHandler.sdf.format(ClientHandler.timestamp) + " to " + nick + ": " + cutMsg(msg));
                    }
                }
            }
        }
    }

    private String cutMsg(String msg) {
        String [] message = msg.split(" ", 3);
        return message[2];
    }

    private String cutNick(String msg) {
        String nick = msg.substring(3);
        String[] separated = nick.split("\\s");
        return separated[0];
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int port = -1;
        if (args !=  null && args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        if (port == -1) {
            port = DEFAULT_PORT;
        }
        new Server(port);

    }

    public static boolean dbConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server/chatDB.db");
            statement = connection.createStatement();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void dbDisconnect(){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}