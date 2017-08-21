package javaserverws;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    /*
        Classe responsavel por conectar ao SGBD e retornar um objeto
        de tal conexao
    */
    private static final String USUARIO = "USUARIO";
    private static final String SENHA = "SENHA";
    private static final String URL = "jdbc:mysql://ENDERECO/BANCO";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Conectar ao banco
    public static Connection connect() throws Exception {
        // Registrar o driver
        Class.forName(DRIVER);

        // Capturar a conexão
        //Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        try{
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conn;
        }
        catch(Exception e){
            System.out.println(e);
            return (Connection)null;

        }

    }
}
