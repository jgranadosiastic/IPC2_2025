/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.enums;

/**
 *
 * @author jose
 */
public class Main {

    private Moneda moneda;

    public static void main(String[] args) {
        Main main = new Main();
        main.hacerAlgo();
    }

    public void hacerAlgo() {
        moneda = Moneda.EURO;

        System.out.println(moneda);
        System.out.println(moneda.getCodigo());
    }
}
