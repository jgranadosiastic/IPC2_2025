/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.jdbc_java.enums;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose
 */
public class Pago {
    
    private Moneda moneda;
    private double monto;
    private LocalDate fecha;
    
    public void procesarPAgo() {
        if (!moneda.equals(Moneda.DOLAR)) {
            // hacemos algo
        }
        
        List<Moneda> monedas = new LinkedList<>();
        List<Moneda> monedas2 = new ArrayList<>();
    }
    
}
