package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MedicoPersistente extends PersistenciaDeDados {

    @Override
    protected Connection abrirConexao() {
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

    @Override
    protected void efetuarInsert(Paciente paciente) {
        // Inserir na tabela 'medico' se o paciente tiver um médico associado
        // Lógica similar à de Paciente e PacienteRegular
    }

    protected void inserirMedico(Medico medico) throws ParseException {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryMedico = "INSERT INTO medico (crm, nome, especialidade, telefone, endereco, email,data_nascimento) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement stmt = conexao.prepareStatement(queryMedico)) {
                stmt.setString(1, medico.getCrm());
                stmt.setString(2, medico.getNome());
                stmt.setString(3, medico.getEspecialidade());
                stmt.setString(4, medico.getTelefone());
                stmt.setString(5, medico.getEndereco());
                stmt.setString(6, medico.getEmail());
                
                String data = medico.getDataNascimento();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = formatter.parse(data); // Parse a data
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                stmt.setDate(7, sqlDate);


                stmt.executeUpdate();
                System.out.println("Médico inserido com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                fecharConexao(conexao);
            }
        }
    }
    public void atualizarMedico(String crm, Medico medicoAtualizado) throws ParseException {
    Connection conexao = abrirConexao();
    if (conexao != null) {
        String queryUpdate = "UPDATE medico SET nome = ?, especialidade = ?, telefone = ?, endereco = ?, email = ?, data_nascimento = ? WHERE crm = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(queryUpdate)) {
            stmt.setString(1, medicoAtualizado.getNome());
            stmt.setString(2, medicoAtualizado.getEspecialidade());
            stmt.setString(3, medicoAtualizado.getTelefone());
            stmt.setString(4, medicoAtualizado.getEndereco());
            stmt.setString(5, medicoAtualizado.getEmail());
            
            String data = medicoAtualizado.getDataNascimento();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = formatter.parse(data);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            stmt.setDate(6, sqlDate);
            
            stmt.setString(7, crm); // Identificador do médico a ser atualizado

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Médico atualizado com sucesso.");
            } else {
                System.out.println("CRM não encontrado. Nenhuma atualização realizada.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            fecharConexao(conexao);
        }
    }
}
    public void consultarMedicos() {
    Connection conexao = abrirConexao();
    
    if (conexao != null) {
        String query = "SELECT crm, nome, especialidade, telefone, endereco, email, data_nascimento FROM medico";

        try (PreparedStatement stmt = conexao.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            // Verifica se existem médicos na tabela
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum médico encontrado.");
            } else {
                // Itera sobre todos os resultados e imprime os dados de cada médico
                System.out.println("Detalhes de Todos os Médicos:");
                while (rs.next()) {
                    // Recupera os dados do médico
                    String crm = rs.getString("crm");
                    String nome = rs.getString("nome");
                    String especialidade = rs.getString("especialidade");
                    String telefone = rs.getString("telefone");
                    String endereco = rs.getString("endereco");
                    String email = rs.getString("email");
                    java.sql.Date dataNascimento = rs.getDate("data_nascimento");

                    // Formata a data de nascimento para o formato "dd/MM/yyyy"
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dataNascimentoStr = formatter.format(dataNascimento);

                    // Imprime os detalhes do médico
                    System.out.println("CRM: " + crm);
                    System.out.println("Nome: " + nome);
                    System.out.println("Especialidade: " + especialidade);
                    System.out.println("Telefone: " + telefone);
                    System.out.println("Endereço: " + endereco);
                    System.out.println("Email: " + email);
                    System.out.println("Data de Nascimento: " + dataNascimentoStr);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void consultarPacientes() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
