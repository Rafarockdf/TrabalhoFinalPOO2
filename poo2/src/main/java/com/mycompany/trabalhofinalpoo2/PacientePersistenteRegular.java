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
 * Classe responsável pela persistência de dados dos pacientes no banco de dados.
 * @author rafam
 */
public class PacientePersistenteRegular extends PersistenciaDeDados {
    
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
            // Inserção na tabela pacienteRegular
            String queryPacienteRegular = "INSERT INTO pacienteRegular (paciente_cpf, historico_consultas, medico_preferencia, plano_de_saude, observacoes) VALUES ( ?, ?, ?, ?, ?)";

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

                // Preparar o insert para a tabela 'pacienteRegular'
                try (PreparedStatement stmt = conexao.prepareStatement(queryPacienteRegular)) {
                    // Considerando que o paciente é do tipo PacienteRegular
                    if (paciente instanceof PacienteRegular) {
                        PacienteRegular pacienteRegular = (PacienteRegular) paciente;
                        
                        stmt.setString(1, pacienteRegular.getCpf());
                        stmt.setString(2, pacienteRegular.getHistoricoConsultas());
                        stmt.setString(3, pacienteRegular.getMedicoPreferencia());
                        stmt.setString(4, pacienteRegular.getPlanoDeSaude());
                        stmt.setString(5, pacienteRegular.getObservacoes());

                        stmt.executeUpdate();
                        System.out.println("Paciente inserido com sucesso na tabela 'pacienteRegular'.");
                    } else {
                        System.out.println("O paciente não é do tipo PacienteRegular. Nenhuma inserção na tabela 'pacienteRegular' foi realizada.");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacientePersistente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
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
            String queryPacienteRegular = "SELECT * FROM pacienteRegular WHERE paciente_cpf = ?";
            
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
                    
                    // Consultar dados na tabela 'pacienteRegular' usando o CPF
                    PreparedStatement stmtRegular = conexao.prepareStatement(queryPacienteRegular);
                    stmtRegular.setString(1, cpf);
                    ResultSet rsRegular = stmtRegular.executeQuery();
                    
                    if (rsRegular.next()) {
                        String historicoConsultas = rsRegular.getString("historico_consultas");
                        String medicoPreferencia = rsRegular.getString("medico_preferencia");
                        String planoDeSaude = rsRegular.getString("plano_de_saude");
                        String observacoes = rsRegular.getString("observacoes");
                        //Infos gerais
                        System.out.println("-------------------------------------");
                        System.out.println("Paciente: ");
                        System.out.println("CPF: " + cpf);
                        System.out.println("Nome: " + nome);
                        System.out.println("Data de Nascimento: " + dataNascimento);
                        System.out.println("Endereço: " + endereco);
                        System.out.println("Contato: " + contato);
                        //Infos regular
                        System.out.println("Histórico de Consultas: " + historicoConsultas);
                        System.out.println("Médico Preferido: " + medicoPreferencia);
                        System.out.println("Plano de Saúde: " + planoDeSaude);
                        System.out.println("Observações: " + observacoes);
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
