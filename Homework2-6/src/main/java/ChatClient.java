import java.io.*;
import java.net.Socket;

public class ChatClient {
    DataInputStream is;
    DataOutputStream os;
    private boolean running;
    Socket socket = new Socket("localhost", 8189);

    public ChatClient() throws IOException {
        running = true;
        try {
            handle(socket);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void handle(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        new Thread(()-> {
            while (running){
                String msg = null;
                try {
                    msg = is.readUTF();
                    System.out.println("From server: " + msg);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
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

    public static void main(String[] args) throws IOException {
        new ChatClient();
    }
}
