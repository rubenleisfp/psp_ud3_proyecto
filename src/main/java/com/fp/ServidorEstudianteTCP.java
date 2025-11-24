package com.fp;

import com.fp.repository.Estudiante;
import com.fp.repository.EstudianteRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// Clase que representa el servidor TCP
public class ServidorEstudianteTCP {
    private int puerto;

    public ServidorEstudianteTCP(int puerto) {
        this.puerto = puerto;
    }

    // Método para iniciar el servidor
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Bucle infinito para aceptar múltiples conexiones de clientes
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Crear un nuevo hilo para manejar cada conexión de cliente
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket);
                new Thread(clienteHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClienteHandler implements Runnable {
        private EstudianteRepository estudianteRepository = new EstudianteRepository();
        private Socket clienteSocket;

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true)) {
                String mensajeCliente = "";
                do {
                    mensajeCliente = in.readLine();
                    try {
                        if (!mensajeCliente.equals("-1")) {
                            int id = Integer.parseInt(mensajeCliente);
                            Estudiante estudiante = estudianteRepository.findById(id);
                            if (estudiante != null) {
                                out.println(estudiante.toString());
                            } else {
                                out.println("Estudiante no encontrado");
                            }
                        }
                    } catch (NumberFormatException e) {
                        out.println("El mensaje debe ser un numero entero");
                    }
                } while (!mensajeCliente.equals("-1"));

            } catch (IOException e) {
                System.err.println("Error al recibir mensajes del cliente: " + e.getMessage());
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al conectar al servidor: " + e.getMessage());
                }
            }
        }
    }


}
