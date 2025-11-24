package com.fp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase que representa el cliente TCP
public class ClienteEstudianteTCP {
    private String servidor;
    private int puerto;

    public ClienteEstudianteTCP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    // Método para conectarse al servidor y enviar un mensaje
    public void conectarYEnviar() {
        try (Socket socket = new Socket(servidor, puerto);

             // Crear flujo de salida para enviar el mensaje al servidor
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

             // Crear el flujo de entrada para recibir información del servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Conectado al servidor");

            String text = "";
            Scanner scanner = new Scanner(System.in);
            do {

                System.out.print("Introduzca una id de estudiante: ");
                System.out.flush(); // Asegura que la línea se imprima antes de recibir la entrada

                try {
                    text = scanner.nextLine(); // Entrada del usuario

                    // Verificar si la entrada es un número
                    Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    System.out.println("La entrada debe ser un número");
                    continue;
                }

                // Enviar mensaje al servidor
                out.println(text);
                // Leer la respuesta del servidor
                String respuestaServidor = in.readLine();
                if (respuestaServidor !=null) {
                    System.out.println("Cliente buscado: " + respuestaServidor);
                }
            } while (!text.equals("-1"));
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }


}