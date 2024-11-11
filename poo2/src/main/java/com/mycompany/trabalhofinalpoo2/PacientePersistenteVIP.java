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
 * Classe responsável pela persistência de dados dos pacientes VIP no banco de dados.
 * @author rafam
 */
public class PacientePersistenteVIP extends PersistenciaDeDados {

    @Override
    protected Connection abrirConexao() {
        Connection conexao = null;
        try {
            // Estabelecendo a conexão com o banco de dados PostgreSQL
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrabalhoFinalPOO2", "postgres", "otavio2004");
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(PacientePersistenteVIP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }

    @Override
    protected void efetuarInsert(Paciente paciente) {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            // Inserção na tabela paciente
            String queryPaciente = "INSERT INTO paciente (cpf, nome, data_nascimento, endereco, contato) VALUES (?, ?, ?, ?, ?)";
            // Inserção na tabela pacienteVIP
            String queryPacienteVIP = "INSERT INTO PacienteVIP (paciente_cpf, nivel_prioridade, servicos_adicionais, acesso_especialistas) VALUES (?, ?, ?, ?)";

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

                // Preparar o insert para a tabela 'PacienteVIP'
                try (PreparedStatement stmt = conexao.prepareStatement(queryPacienteVIP)) {
                    // Considerando que o paciente é do tipo PacienteVIP
                    if (paciente instanceof PacienteVIP) {
                        PacienteVIP pacienteVIP = (PacienteVIP) paciente;
                        
                        stmt.setString(1, pacienteVIP.getCpf());
                        stmt.setInt(2, pacienteVIP.getNivelPrioridade());
                        stmt.setString(3, pacienteVIP.getServicosAdicionais());
                        stmt.setString(4, pacienteVIP.getAcessoEspecialistas());
                       // Converte para java.sql.Date
                        
                        stmt.executeUpdate();
                        System.out.println("Paciente VIP inserido com sucesso na tabela 'PacienteVIP'.");
                    } else {
                        System.out.println("O paciente não é do tipo PacienteVIP. Nenhuma inserção na tabela 'PacienteVIP' foi realizada.");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacientePersistenteVIP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PacientePersistenteVIP.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PacientePersistenteVIP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void consultarPacientes() {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryPaciente = "SELECT * FROM paciente";
            String queryPacienteVIP = "SELECT * FROM PacienteVIP WHERE paciente_cpf = ?";

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


                    // Consultar dados na tabela 'PacienteVIP' usando o CPF
                    PreparedStatement stmtVIP = conexao.prepareStatement(queryPacienteVIP);
                    stmtVIP.setString(1, cpf);
                    ResultSet rsVIP = stmtVIP.executeQuery();

                    if (rsVIP.next()) {
                        int nivelPrioridade = rsVIP.getInt("nivel_prioridade");
                        String servicosAdicionais = rsVIP.getString("servicos_adicionais");
                        String acessoEspecialistas = rsVIP.getString("acesso_especialistas");
                        Date dataCadastroVIP = rsVIP.getDate("data_cadastro_vip");
                        //Info Geral
                        System.out.println("-------------------------------------");
                        System.out.println("Paciente: ");
                        System.out.println("CPF: " + cpf);
                        System.out.println("Nome: " + nome);
                        System.out.println("Data de Nascimento: " + dataNascimento);
                        System.out.println("Endereço: " + endereco);
                        System.out.println("Contato: " + contato);
                        //Info vip
                        System.out.println("Nível de Prioridade: " + nivelPrioridade);
                        System.out.println("Serviços Adicionais: " + servicosAdicionais);
                        System.out.println("Acesso a Especialistas: " + acessoEspecialistas);
                        System.out.println("Data de Cadastro VIP: " + dataCadastroVIP);
                        System.out.println("-------------------------------------");
                    }

                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacientePersistenteVIP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                fecharConexao(conexao);
            }
        }
    }
}
