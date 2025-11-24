package com.fp;

public class AppServidorEstudianteTCP {

    public static void main(String[] args) {

            // Crear una instancia del servidor en el puerto 1234 e iniciarlo
            ServidorEstudianteTCP servidor = new ServidorEstudianteTCP(1234);
            servidor.iniciar();
    }
}
