/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.sqlinjection;

import static com.jgranados.jdbc_java.dbconnection.DBConnection.PASSWORD;
import static com.jgranados.jdbc_java.dbconnection.DBConnection.URL_FATAL;
import static com.jgranados.jdbc_java.dbconnection.DBConnection.USER_NAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author jose
 */
public class SQLInjection {

    private Connection connection;

    public void connect() {
        System.out.println("URL de conexion: " + URL_FATAL);
        try {
            connection = DriverManager.getConnection(URL_FATAL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            // manejamos la exception
            System.out.println("Error al conectarse");
            e.printStackTrace();
        }
    }

    public void loginInseguro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login Vulnerable ===");
        System.out.println("Usuario: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String password = scanner.nextLine();

        // Consulta vulnerable - concatenación directa de inputs
        String query = "SELECT * FROM usuario WHERE username = '" + username
                + "' AND password = '" + password + "'";

        System.out.println("sql: " + query);
        
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            if (rs.next()) {
                System.out.println("¡Autenticación exitosa!");
                System.out.println("Bienvenido: " + rs.getString("username"));
                System.out.println("Tu rol es: " + rs.getString("rol"));
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void loginSeguro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login Seguro ===");
        System.out.println("Usuario: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String password = scanner.nextLine();

        // Consulta segura para usar con prepared statements
        String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";

        System.out.println("sql: " + query);
        
        try (PreparedStatement stmt = connection.prepareStatement(query);
                ) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("¡Autenticación exitosa!");
                System.out.println("Bienvenido: " + rs.getString("username"));
                System.out.println("Tu rol es: " + rs.getString("rol"));
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
