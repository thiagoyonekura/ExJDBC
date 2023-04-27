package jdbc;

import java.sql.*;


public class BancoDados implements InterfaceBancoDados {
	
	   Connection conexao;
    
    @Override
	public void conectar(String db_url, String db_user, String db_password) {
		try {
            conexao = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível conectar ao Banco de Dados: "+e);
        }
	}

	@Override
	public void desconectar() {
		try {
            conexao.close();
            System.out.println("Conexão encerrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível desconectar ao Banco de Dados: "+e);
        }
	}

	@Override
	public void consultar(String db_query) {
		try {
			Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(db_query);
            while (resultSet.next()) {
                System.out.println(" | Id: " + resultSet.getInt("id") + " | Nome: " + resultSet.getString("nome") + " | Email: " + resultSet.getString("email"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public int inserirAlterarExcluir(String db_query) {
		int linhasAfetadas = 0;

        try {
        	Statement statement = conexao.createStatement();
            linhasAfetadas = statement.executeUpdate(db_query);

            if (linhasAfetadas > 0) {
                System.out.println("Sucesso na operação! " + linhasAfetadas + " linhas afetadas.");
            } else {
                System.out.println("Nenhuma linha afetada pela operação!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return linhasAfetadas;
    }

	}

