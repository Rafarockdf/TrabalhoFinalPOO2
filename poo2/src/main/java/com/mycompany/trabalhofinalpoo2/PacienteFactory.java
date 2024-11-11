/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;


public class PacienteFactory {
    public static Paciente criarPaciente(String tipo) {

        // Utilizando equals para comparar strings
        if (tipo.equalsIgnoreCase("Regular")) {
            return new PacienteRegular();
        } else if (tipo.equalsIgnoreCase("VIP")) {
            return new PacienteVIP();
        } else if (tipo.equalsIgnoreCase("Infantil")) {
            return new PacienteInfantil();
        }

        // Caso o tipo seja inválido, lançar uma exceção
        throw new IllegalArgumentException("Tipo de paciente desconhecido: " + tipo);
    }
}

