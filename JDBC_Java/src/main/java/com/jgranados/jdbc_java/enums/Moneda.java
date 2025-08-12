/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.enums;

/**
 *
 * @author jose
 */
public enum Moneda {
    DOLAR("USD", 1),
    QUETZAL("GTQ", 7.78),
    EURO("EU", 0.98);
    
    private String codigo;
    private double tasaCambio;
    
    Moneda(String codigo, double tasaCAmbio) {
        this.codigo = codigo;
        this.tasaCambio = tasaCAmbio;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public double getTasaCambio() {
        return tasaCambio;
    }
}
