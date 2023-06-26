package Cliente_Servidor_TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        Scanner scanner = new Scanner(clientSocket.getInputStream());
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

        while (true) {
            String message = scanner.nextLine();
            System.out.println("Cliente: " + message);
            writer.println("Recibido: " + message);
        }
    }
}