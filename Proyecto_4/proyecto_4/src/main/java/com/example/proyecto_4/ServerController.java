package com.example.proyecto_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements Runnable {
    @FXML
    private TextArea TAServer;
    private static ArrayList<connections> conexiones;
    private ArrayList<log> logs;
    private ServerSocket servidor;
    private boolean flag;
    private ExecutorService pool;
    private static ArrayList<String> usuarios;


    public ServerController() {
        flag = true;
        conexiones = new ArrayList<>();
        usuarios = new ArrayList<>();

    }

    @Override
    // run para iniciar al crear
    public void run() {

        try {
            pool = Executors.newCachedThreadPool();
            // crear socket del server
            servidor = new ServerSocket(1234);
            // aceptar conexiones
            while (flag) {

                Socket cliente = servidor.accept();
                connections conexion = new connections(cliente);
                System.out.println("Conexion aceptada");
                conexiones.add(conexion);
                pool.execute(conexion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarmssgatodos(String mensaje) {
        for (connections conexion : conexiones) {
            conexion.enviarMensaje(mensaje);
        }
    }

    public void apagarservidor() {
        try {
            flag = false;
            if (!servidor.isClosed()) {
                servidor.close();
            }
            for (connections conexion : conexiones) {
                conexion.cerrarConexion();
            }

        } catch (IOException e) {
            //todo: handle exception
        }

    }

    //corregir
    public static ArrayList<String> getUsuarios() {
        return usuarios;
        }

    public ArrayList<log> getLogsUsario(String nombre){
        ArrayList<log> logsUsuario = new ArrayList<>();
        for (log log : logs) {
            if(log.getOrigen().equals(nombre)||log.getDestino().equals(nombre)){
                logsUsuario.add(log);
            }
        }
        return logsUsuario;
    }

    public ArrayList<log> getConversacion(String Origen, String Destino){
        ArrayList<log> conversacion = new ArrayList<>();
        for (log log : logs) {
            if((log.getOrigen().equals(Origen)&&log.getDestino().equals(Destino))||(log.getOrigen().equals(Destino)&&log.getDestino().equals(Origen))){
                conversacion.add(log);
            }
        }
        return logs;
    }

    public void eliminarmensajes(String Origen){
        //listview puedes pedirle la lista
        // mensajes = listview.getItems();
        //iterar por el cui y borrar los que coincidan
    //"Nombre: mensaje" split por : y comparar el nombre
        enviarmssgatodos("EliminarM@"+Origen);

    }


    //------------------------------------------------------------------------------------------******************************----------------------------------------------


    public class connections implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nombre;
        private String destinatario;
        private String[] mensajesplit;

        public connections(Socket client) {
            this.client = client;
//            usuario = new User();
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true); // enviar mensajes
                in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream())); // recibe nombre// conectar con gui
                out.println("Ingrese su nombre: "); // enviar mensaje al cliente
                nombre = in.readLine();
                usuarios.add(nombre); //corregir para que no se agrege dos veces
                System.out.println(nombre + " se ha conectado");
                //revisar si si es usario
                enviarmssgatodos(nombre + " se ha conectado");
                String mensaje;

                while ((mensaje = in.readLine()) != null) {
                    destinatario = "Todos";
                    System.out.println("Mensaje recibido en servidor:\n"+mensaje);
                     mensajesplit = mensaje.split("//");
                    if(mensajesplit[0].equals("Todos")){
                        enviarmssgatodos(nombre+ ": " + mensajesplit[1]);
                        logs.add(new log(nombre, destinatario,mensajesplit[1], LocalTime.now().toString(),"Exitosa"));
                    }
                    //@usuario//mensaje
                    if(mensajesplit[0].contains("@")){
                        destinatario = mensajesplit[0].substring(1);
                        for (connections conexion : conexiones) {
                            if (conexion.getNombre().equals(destinatario)) {
                                conexion.enviarMensaje(nombre + ": " + mensajesplit[1]);
                                logs.add(new log(nombre, destinatario,mensajesplit[1], LocalTime.now().toString(),"Exitosa"));
                                break;
                            }
                            else {
                                enviarMensaje("El usuario no se encuentra disponible");
                                logs.add(new log(nombre, destinatario,mensajesplit[1], LocalTime.now().toString(),"Error al enviar"));
                            }
                        }
                    }
                    if (mensajesplit[0].equals("Salir")) {
                        conexiones.remove(this);
                        enviarmssgatodos(nombre + " se ha desconectado");
                        logs.add(new log(nombre, destinatario,"Desconectado", LocalTime.now().toString(),"Exitosa"));
                        break;
                    }
                    //eliminar
                    if (mensajesplit[0].equals("Eliminar")) {
                        conexiones.remove(this);
                        enviarmssgatodos(nombre + " se ha desconectado");
                        logs.add(new log(nombre, destinatario,"Desconectado", LocalTime.now().toString(),"Exitosa"));
                        break;

                    }
                    else {
                        enviarmssgatodos(mensaje);
                    }
                }
            } catch (IOException e) {
                //jpane con error
                logs.add(new log(nombre, destinatario,mensajesplit[1], LocalTime.now().toString(),"Error al enviar"));            }

        }




        public String getNombre() {
            return nombre;
        }

        public void enviarMensaje(String mensaje) {
            out.println(mensaje);
        }

        public void cerrarConexion() {
            try {
                if (!client.isClosed()) {
                    client.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                ;
            }
        }
    }
}




