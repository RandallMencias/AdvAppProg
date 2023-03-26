//package com.example.proyecto_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class connections implements Runnable{
//    private Socket client;
//    private BufferedReader in;
//    private PrintWriter out;
//    private User usuario;
//
//
//    public connections(Socket client) {
//        this.client = client;
//    }
//
//    @Override
//    public void run() {
//        try {
//            out = new PrintWriter(client.getOutputStream(), true);
//            in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
//            usuario = new User(in.readLine());
//            String mensaje;
//            while((mensaje = in.readLine()) != null) {
//                usuario.setMensaje(mensaje);
//                System.out.println(usuario.getMensaje());
////                enviarmensajetodos
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public void enviarMensaje(String mensaje) {
//        out.println(mensaje);
//    }
//
//    public void cerrarConexion() {
//        try {
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            //
//        }
//    }
//
//
//}
//
