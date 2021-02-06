import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean running;
     DataInputStream is;
     DataOutputStream os;

     public ChatServer(){
         running = true;
         try (ServerSocket server = new ServerSocket(8189)) {
             while (running) {
                 Socket socket = server.accept();
                 handle(socket);
             }
             } catch(IOException e){
                 e.printStackTrace();
             }
         }

     public void handle(Socket socket) throws IOException {
         is = new DataInputStream(socket.getInputStream());
         os = new DataOutputStream(socket.getOutputStream());
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         new Thread(()-> {
             while (running){
                 String msg;
                 try {
                     msg = is.readUTF();
                     if (msg.equals("exit")) {
                         os.writeUTF("Cya");
                         os.flush();
                         System.out.println("Client disconnected");
                         break;
                     }
                     System.out.println("From client: " + msg);
                 } catch (IOException ioException) {
                     System.out.println("Client disconnected");
                     setRunning(false);
                 }
             }
         }).start();
         new Thread(()-> {
             while (running){
                 try {
                     String outmsg = br.readLine();
                     os.writeUTF(outmsg);
                     os.flush();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
     }

    public static void main(String[] args){
         new ChatServer();
    }
}
