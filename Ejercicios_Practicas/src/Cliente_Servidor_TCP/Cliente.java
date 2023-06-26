package Cliente_Servidor_TCP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends JFrame {

    private JTextField inputField;
    private JTextArea chatArea;
    private PrintWriter writer;

    public Cliente() {
        setTitle("Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                writer.println(message);
                inputField.setText("");
            }
        });

        inputPanel.add(inputField, BorderLayout.CENTER);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        getContentPane().add(new JScrollPane(chatArea), BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
    }

    public void start() {
        String serverAddress = JOptionPane.showInputDialog("Dirección IP del servidor:");
        try {
            Socket socket = new Socket(serverAddress, 1234);
            Scanner scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);

            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                chatArea.append("Servidor: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setVisible(true);
        cliente.start();
    }
}