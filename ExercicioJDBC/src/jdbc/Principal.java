package jdbc;

import java.sql.*;
import java.util.Scanner;


public class Principal {

public static void main(String[] args) {
		
	  BancoDados bd = new BancoDados();
      bd.conectar("jdbc:mysql://localhost:3306/jdbc", "root", "");

      Scanner leitor = new Scanner(System.in);

      System.out.println("Selecione uma opção:");
      System.out.println("1 - Inserir pessoa");
      System.out.println("2 - Consultar pessoas");
      System.out.println("3 - Inserir mais de uma pessoa");
      System.out.println("4 - Inserir várias pessoas");
      int opcao = leitor.nextInt();

      switch (opcao) {
          case 1:
              System.out.println("Digite o nome da pessoa:");
              String nome = leitor.next();
              System.out.println("Digite o email da pessoa:");
              String email = leitor.next();
              String queryInsercao = "INSERT INTO pessoa (nome, email) VALUES ('" + nome + "','" + email + "')";
              bd.inserirAlterarExcluir(queryInsercao);
              break;
          case 2:
              bd.consultar("SELECT * FROM pessoa");
              break;
          case 3:
              System.out.println("Quantas pessoas você quer inserir?");
              int quantidade = leitor.nextInt();
              for (int i = 0; i < quantidade; i++) {
                  System.out.println("Digite o nome da pessoa " + (i+1) + ":");
                  String nomePessoa = leitor.next();
                  System.out.println("Digite o email da pessoa " + (i+1) + ":");
                  String emailPessoa = leitor.next();
                  String queryInsercaoPessoa = "INSERT INTO pessoa (nome, email) VALUES ('" + nomePessoa + "','" + emailPessoa + "')";
                  bd.inserirAlterarExcluir(queryInsercaoPessoa);
              }
              break;
          case 4:
              String queryInsercaoVariasPessoas = "INSERT INTO pessoa (nome, email) VALUES ('Nathalia', 'nathalia@mail.com'), ('Carla', 'carla@mail.com'), ('Pedro', 'pedro@mail.com')";
              bd.inserirAlterarExcluir(queryInsercaoVariasPessoas);
              break;
          default:
              System.out.println("Opção inválida!");
              break;
      }

      bd.desconectar();
  }

}
