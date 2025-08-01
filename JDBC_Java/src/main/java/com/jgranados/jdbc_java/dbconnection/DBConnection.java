/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author jose
 */
public class DBConnection {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "hospidb";
    private static final String USER_NAME = "admindba";
    private static final String PASSWORD = "12345";

    private static final String INSERT_COLEGIADO = "INSERT INTO colegiado "
            + "(numero_colegiado, fecha_colegiado, activo) "
            + "values (%d, '%s', %s)";
    
    private static final String INSERT_OPERACION = "INSERT INTO operacion "
            + "(codigo, fecha_hora_inicio, fecha_hora_fin, dpi_paciente) "
            + "values ('%s', '%s', '%s', '%s')";
    
    private static final String INSERT_CITA = "INSERT INTO cita "
            + "(codigo, fecha_hora, dpi, celular) "
            + "values ('%s', '%s', '%s', '%s')";

    private static final String URL = "jdbc:mysql://"
            + IP + ":" + PUERTO + "/" + SCHEMA;

    private Connection connection;

    public void connect() {
        System.out.println("URL de conexion: " + URL);
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
            System.out.println("Catalogo: " + connection.getCatalog());

        } catch (SQLException e) {
            // manejamos la exception
            System.out.println("Error al conectarse");
            e.printStackTrace();
        }

    }

    public void crearTablas() {
        try {
            Statement statementTablaColegiado = connection.createStatement();
            int rowsAffected
                    = statementTablaColegiado.executeUpdate("CREATE TABLE colegiado ("
                            + "numero_colegiado INTEGER NOT NULL,"
                            + "fecha_colegiado DATE NOT NULL,"
                            + "activo BOOLEAN NOT NULL,"
                            + "CONSTRAINT pk_colegiado PRIMARY KEY (numero_colegiado)"
                            + ")");

            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error creando tabla.");
            e.printStackTrace();
        }

    }

    public void crearColegiado() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Indique los datos del colegiado");
        System.out.println("Numero Colegiado:");
        int numeroColegiado = Integer.parseInt(scanner.nextLine());
        System.out.println("Fecha de colegiado:");
        String fecha = scanner.nextLine();
        System.out.println("Está activo (1/0):");
        boolean activo = "1".equals(scanner.nextLine()) ? true : false;

        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(INSERT_COLEGIADO, numeroColegiado,
                    fecha,
                    activo);

            insertStatement.executeUpdate(sql);
            System.out.println("sql ejecutado: " + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void crearOperacion(String codigo, LocalDateTime fechaInicio,
            LocalDateTime fechaFin, String dpi, String celular) {

        
        try {
            connection.setAutoCommit(false);
            Statement insertStatement = connection.createStatement();
            String sql = String.format(INSERT_OPERACION, codigo,
                    fechaInicio,
                    fechaFin,
                    dpi);

            insertStatement.executeUpdate(sql);
            System.out.println("sql ejecutado: " + sql);
            
            /*if (true) {
                throw new RuntimeException("Fallo por algo");
            }*/
            Statement insertCitaStmt = connection.createStatement();
            String sql2 = String.format(INSERT_CITA, "C" + codigo,
                    fechaInicio,
                    dpi,
                    celular);
            insertCitaStmt.executeUpdate(sql2);
            System.out.println("sql ejecutado: " + sql2);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Rollback error");
            }
            
            e.printStackTrace();
        }
    }
    
    public void verColegiados() {
        String sql = "SELECT * FROM colegiado";
        try {
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("Numero de Colegiado:");
                System.out.println(resultSet.getInt("numero_colegiado"));
                System.out.println("Fecha de Colegiado:");
                System.out.println(resultSet.getDate("fecha_colegiado").toLocalDate());
                System.out.println("Est[a activo:");
                System.out.println(resultSet.getBoolean("activo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
