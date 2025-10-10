package com.example.apalabrados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLread {
    private static final String URL = "jdbc:mysql://localhost:3306/apalabradossql";
    private static final String USER = "root";
    private static final String PASSWORD = "ChangeMe";

    public static List<String> obtenerPalabras() {
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<String> datos = new ArrayList<>();

        try {
            System.out.println("Conectando");


            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");

            stmt = conexion.createStatement();

            String sql = "SELECT palabra FROM palabras";
            System.out.println("Ejecutando consulta: " + sql);

            rs = stmt.executeQuery(sql);
            System.out.println("Consulta ejecutada, procesando resultados...");

            int count = 0;
            while (rs.next()) {
                String palabra = rs.getString("palabra");
                datos.add(palabra);
                count++;
                System.out.println("Palabra " + count + ": " + palabra);
            }

            System.out.println("Total palabras obtenidas: " + count);
            System.out.println("CORRECTO: " + datos);

        } catch (SQLException e) {
            System.err.println("ERROR SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return datos;
    }

    public static void main(String[] args) {
        List<String> palabras = obtenerPalabras();
        System.out.println("Probando: " + palabras);
    }
}