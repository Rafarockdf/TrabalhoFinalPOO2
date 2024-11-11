package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConsultaPersistente {

    private static ConsultaPersistente instancia;  // Instância única da classe
    
    // Construtor privado para evitar criação de instâncias fora da classe
    private ConsultaPersistente() {}

    // Método para obter a instância única (Singleton)
    public static synchronized ConsultaPersistente getInstancia() {
        if (instancia == null) {
            instancia = new ConsultaPersistente();
        }
        return instancia;
    }

    // Método para abrir conexão com o banco de dados
    private Connection abrirConexao() {
        Connection conexao = null;
        try {
            // Estabelecendo a conexão com o banco de dados PostgreSQL
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrabalhoFinalPOO2", "postgres", "otavio2004");
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexao;
    }

    // Método para registrar uma consulta
    public void registrarConsulta(Consulta consulta) throws ParseException {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryConsulta = "INSERT INTO consulta (paciente_cpf, medico_crm, data_consulta, motivo, observacoes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(queryConsulta)) {
                stmt.setString(1, consulta.getPacienteCpf());
                stmt.setString(2, consulta.getMedicoCrm());

                // Convertendo a data da consulta
                String data = consulta.getDataConsulta();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = formatter.parse(data);
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                stmt.setDate(3, sqlDate);

                stmt.setString(4, consulta.getMotivo());
                stmt.setString(5, consulta.getObservacoes());

                stmt.executeUpdate();
                System.out.println("Consulta registrada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                fecharConexao(conexao);
            }
        }
    }

    // Método para fechar a conexão com o banco de dados
    private void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
