package javaserverws;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Db_query {
    /*
        Classe que realiza a query desejada no banco de dados
        e retorna com a informação desejada
    */
    
    public static ArrayList<String[]> DbQuery(String query){
        ArrayList<String[]> result = new ArrayList<String[]>();

        try{
            // Conexao db
            Connection conn = Db.connect();
            Statement st = conn.createStatement();

            // ResultSet e um cursor que vai sempre avancando
            ResultSet rs = st.executeQuery(query);

            // Obtem o numero de colunas no DB
            int numColunas = rs.getMetaData().getColumnCount();
          
                // Monta um array de strings do tamanho da coluna
                // + 1 por causa do laco do for para nao romper o array
                String[] row = new String[numColunas + 1];            
            //int i = 0;   

            int i = 0;
            while (rs.next())
            {
                for(int j = 1; j <= rs.getMetaData().getColumnCount(); j++){
                   row[j] = rs.getString(j);
                }
                result.add(i, row.clone());
                i++;
            }
            st.close();            
            
            return result;
        }catch(Exception e){
            return result;
        }

    }
    
    // Operação de inserir no DB
    public static void Insert(String query){
        try{
            // Conexao db
                Connection conn = Db.connect();
                Statement st = conn.createStatement();
            // Query de inserção
                st.executeUpdate(query);
            // Fechando conexão
               conn.close();
            
        }catch(Exception e){
            System.out.println("Erro em insercao no DB");
            System.out.println(e);
        }
    }
    
    // Operação de atualizar no DB
    public static void Update(String query){
        try{
            // Conexao db
                Connection conn = Db.connect();
                Statement st = conn.createStatement();
            // Query de inserção
                st.executeUpdate(query);
            // Fechando conexão
               conn.close();
            
        }catch(Exception e){
            System.out.println("Erro em atualizacao no DB");
        }
    } 
    
    // Operação de remocao no DB
    public static void Delete(String query){
        try{
            // Conexao db
                Connection conn = Db.connect();
                Statement st = conn.createStatement();
            // Query de inserção
                st.executeUpdate(query);
            // Fechando conexão
               conn.close();
            
        }catch(Exception e){
            System.out.println("Erro em atualizacao no DB");
        }
    }
    
    // Operacao de contagem do ocorrencias de linhas encontradas
    public static int numTuplas(String query){
        try{
            // Conexao db
            Connection conn = Db.connect();
            Statement st = conn.createStatement();

            // ResultSet e um cursor que vai sempre avancando
            ResultSet rs = st.executeQuery(query);
            int cont = 0;
            while(rs.next()){
                cont = cont + 1;
            } 
            return cont;
        }catch(Exception e){
            System.out.println("Erro ao realizar a contagem de linhas");
            return -1;
        }
    }
}
