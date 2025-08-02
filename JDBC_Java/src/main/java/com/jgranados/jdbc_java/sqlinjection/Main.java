/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.sqlinjection;

/**
 *
 * @author jose
 */
public class Main {
    public static void main(String[] args) {
        SQLInjection sqlInjection = new SQLInjection();
        
        sqlInjection.connect();
        //sqlInjection.loginInseguro();
        sqlInjection.loginSeguro();
    }
}
