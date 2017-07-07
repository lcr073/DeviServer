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
}