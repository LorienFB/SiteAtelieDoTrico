
package br.com.siteateliedotrico.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

   private static final String URL = "jdbc:mysql://localhost:3306/atelie_do_trico";
    private static final String USUARIO = "root";
    private static final String SENHA = "Mysql_1234";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
