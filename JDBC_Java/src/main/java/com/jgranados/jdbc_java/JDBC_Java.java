/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgranados.jdbc_java;

import com.jgranados.jdbc_java.dbconnection.DBConnection;
import java.time.LocalDateTime;

/**
 *
 * @author jose
 */
public class JDBC_Java {
    
    //SELECT * FROM user WHERE name = 'xx' or 1 = 1 -- AND password = yyyy

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DBConnection connection = new DBConnection();
        connection.connect();
        //connection.crearTablas();
        //connection.crearColegiado();
        //connection.verColegiados();
        connection.crearOperacion("O',4545", LocalDateTime.now().minusDays(1),
                LocalDateTime.now(), "444444", "76655445");
        
        /*
        INSERT INTO operacion (codigo, fecha_hora_inicio, fecha_hora_fin, dpi_paciente) 
                values ('O',4545', '2025-07-29T16:52:27.790983870', '2025-07-30T16:52:27.791004799', '444444');
*/    
}
}
