/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;

public class PacienteFactory {
    public static Paciente criarPaciente(
            String tipo, String nome, String dataNascimento, String endereco, String contato,
            String historicoConsultas, String medicoPreferencia, String planoDeSaude,
            String periodicidadeConsultas, String observacoes,
            CriterioPrioridade criterioPrioridade, int nivelPrioridade,
            String servicosAdicionais, String acessoEspecialistas,
            String dataCadastroVIP, String nomeResponsavel, String contatoResponsavel,
            String informacoesPediatricas, String escola, boolean autorizacaoTratamentos,
            String medicoPediatraPreferencial) {

        // Utilizando equals para comparar strings
        if (tipo.equalsIgnoreCase("Regular")) {
            return new PacienteRegular(
                    nome, dataNascimento, endereco, contato,
                    historicoConsultas, medicoPreferencia, planoDeSaude,
                    periodicidadeConsultas, observacoes);
        } else if (tipo.equalsIgnoreCase("VIP")) {
            return new PacienteVIP(
                    nome, dataNascimento, endereco, contato,
                    criterioPrioridade, servicosAdicionais,
                    acessoEspecialistas, dataCadastroVIP, nivelPrioridade);
        } else if (tipo.equalsIgnoreCase("Infantil")) {
            return new PacienteInfantil(
                    nome, dataNascimento, endereco, contato,
                    nomeResponsavel, contatoResponsavel, informacoesPediatricas,
                    escola, autorizacaoTratamentos, medicoPediatraPreferencial);
        }

        // Caso o tipo seja inválido, lançar uma exceção
        throw new IllegalArgumentException("Tipo de paciente desconhecido: " + tipo);
    }
}

