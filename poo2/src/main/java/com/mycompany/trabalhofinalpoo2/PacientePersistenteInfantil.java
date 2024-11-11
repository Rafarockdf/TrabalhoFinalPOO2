/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável pela persistência de dados dos pacientes (regular, infantil, VIP) no banco de dados.
 * @author rafam
 */
public class PacientePersistenteInfantil extends PersistenciaDeDados {

    @Override
    protected Connection abrirConexao() {
        Connection conexao = null;
        try {
            // Estabelecendo a conexão com o banco de dados PostgreSQL
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrabalhoFinalPOO2", "postgres", "otavio2004");
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(PacientePersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }

    @Override
    protected void efetuarInsert(Paciente paciente) {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            // Inserção na tabela paciente
            String queryPaciente = "INSERT INTO paciente (cpf, nome, data_nascimento, endereco, contato) VALUES (?, ?, ?, ?, ?)";
            
            // Inserção na tabela PacienteInfantil
            String queryPacienteInfantil = "INSERT INTO PacienteInfantil (paciente_cpf, nome_responsavel, contato_responsavel, informacoes_pediatricas, escola, autorizacao_tratamentos, medico_pediatra_preferencial) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            try {
                // Preparar o insert para a tabela 'paciente'
                try (PreparedStatement stmt = conexao.prepareStatement(queryPaciente)) {
                    String data = paciente.getDataNascimento();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsedDate = formatter.parse(data); // Parse a data
                    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Converte para java.sql.Date

                    stmt.setString(1, paciente.getCpf());
                    stmt.setString(2, paciente.getNome());
                    stmt.setDate(3, sqlDate);
                    stmt.setString(4, paciente.getEndereco());
                    stmt.setString(5, paciente.getContato());

                    stmt.executeUpdate();
                    System.out.println("Paciente inserido com sucesso na tabela 'paciente'.");
                }

                // Inserir dados na tabela 'PacienteInfantil' se o paciente for do tipo PacienteInfantil
                if (paciente instanceof PacienteInfantil) {
                    PacienteInfantil pacienteInfantil = (PacienteInfantil) paciente;
                    
                    try (PreparedStatement stmt = conexao.prepareStatement(queryPacienteInfantil)) {
                        stmt.setString(1, pacienteInfantil.getCpf());
                        stmt.setString(2, pacienteInfantil.getNomeResponsavel());
                        stmt.setString(3, pacienteInfantil.getContatoResponsavel());
                        stmt.setString(4, pacienteInfantil.getInformacoesPediatricas());
                        stmt.setString(5, pacienteInfantil.getEscola());
                        stmt.setBoolean(6, pacienteInfantil.isAutorizacaoTratamentos());
                        stmt.setString(7, pacienteInfantil.getMedicoPediatraPreferencial());
                        
                        stmt.executeUpdate();
                        System.out.println("Paciente Infantil inserido com sucesso na tabela 'PacienteInfantil'.");
                    }
                }
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(PacientePersistente.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                fecharConexao(conexao);
            }
        }
    }

    @Override
    protected void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException ex) {
                Logger.getLogger(PacientePersistente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void consultarPacientes() {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryPaciente = "SELECT * FROM paciente";
            String queryPacienteInfantil = "SELECT * FROM PacienteInfantil WHERE paciente_cpf = ?";

            try {
                // Consultar todos os pacientes na tabela 'paciente'
                PreparedStatement stmt = conexao.prepareStatement(queryPaciente);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    Date dataNascimento = rs.getDate("data_nascimento");
                    String endereco = rs.getString("endereco");
                    String contato = rs.getString("contato");

                    // Consultar dados na tabela 'PacienteInfantil' usando o CPF
                    PreparedStatement stmtInfantil = conexao.prepareStatement(queryPacienteInfantil);
                    stmtInfantil.setString(1, cpf);
                    ResultSet rsInfantil = stmtInfantil.executeQuery();

                    if (rsInfantil.next()) {
                        String nomeResponsavel = rsInfantil.getString("nome_responsavel");
                        String contatoResponsavel = rsInfantil.getString("contato_responsavel");
                        String informacoesPediatricas = rsInfantil.getString("informacoes_pediatricas");
                        String escola = rsInfantil.getString("escola");
                        boolean autorizacaoTratamentos = rsInfantil.getBoolean("autorizacao_tratamentos");
                        String medicoPediatraPreferencial = rsInfantil.getString("medico_pediatra_preferencial");
                        ;

                        // Informações gerais do paciente
                        System.out.println("-------------------------------------");
                        System.out.println("Paciente: ");
                        System.out.println("CPF: " + cpf);
                        System.out.println("Nome: " + nome);
                        System.out.println("Data de Nascimento: " + dataNascimento);
                        System.out.println("Endereço: " + endereco);
                        System.out.println("Contato: " + contato);

                        // Informações específicas do paciente infantil
                        System.out.println("Nome Responsável: " + nomeResponsavel);
                        System.out.println("Contato Responsável: " + contatoResponsavel);
                        System.out.println("Informações Pediátricas: " + informacoesPediatricas);
                        System.out.println("Escola: " + escola);
                        System.out.println("Autorização para Tratamentos: " + autorizacaoTratamentos);
                        System.out.println("Médico Pediatra Preferencial: " + medicoPediatraPreferencial);
                        System.out.println("-------------------------------------");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacientePersistente.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                fecharConexao(conexao);
            }
        }
    }
}
