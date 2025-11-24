package com.fp;

public class AppClienteEstudianteTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteEstudianteTCP cliente = new ClienteEstudianteTCP("localhost", 1234);
            cliente.conectarYEnviar();
        }

}
