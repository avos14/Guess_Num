
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() throws Exception {

        ServerSocket server_socket = new ServerSocket(2022); //opening a new port
        System.out.println("Port 2022 is open.");

        Socket socket = server_socket.accept();
        System.out.println("Client " + socket.getInetAddress() + " has connected.");

        // I/O buffers:
        BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream()));
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter (socket.getOutputStream()), true);
        String message;
        int secret_number = (int)(Math.random()*10+1);

        do {
            out_socket.println("Guess a number [1-10]: your number is? ");
            message = in_socket.readLine();
        } while (!(Integer.parseInt(message)==secret_number));

        out_socket.println("Wow, you are a mentalist!!!");
        System.out.println("Secret number is out. Exiting the app.");

        socket.close();
        System.out.println("Socket is closed.");
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

