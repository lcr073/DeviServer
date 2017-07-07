package javaserverws;

import com.pusher.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.Map;

public class ObtemChar {
    
    public static void Obtem_char(WebSocket conn, String idLogin, Map<String,String[]> itens){
    // Obtem os usuarios relacionados aquele idLogin
    ArrayList<String[]> busca_char = Db_query.DbQuery("SELECT idChar,nomeChar FROM charc WHERE idLogin = '" + idLogin +"'"); 
    
    // Obtem os equipamentos dos usuarios
    ArrayList<String[]> equips_char = Db_query.DbQuery("SELECT idChar, idItemCabeca,idItemFace, idItemCostas, idItemMaoEsq FROM charEquip WHERE " + stringQueryBuscaChar(busca_char) +";");
    
            // Respondendo ao cliente os chars
             for(int i=0; i< busca_char.size(); i++){
             conn.send("iC:" + busca_char.get(i)[1] + ":" + busca_char.get(i)[2]); 
             }
           
             for(int j =0; j< equips_char.size(); j++){
                 // Listando o idChar, idItemCabeca, idItemFace, idItemCostas, idItemMaoEsq
                 System.out.println("busca: " + equips_char.get(j)[1] + ":" + equips_char.get(j)[2] + ":" + equips_char.get(j)[3] + ":" + equips_char.get(j)[4]);  
                 
                 // Obtendo da lista de itens os detalhes dos itens
             }
    }
    
    private static String stringQueryBuscaChar(ArrayList<String[]> busca_char){
        /*
        Metodo que retorna a string que deve ser inserida na query de busca de
        equipamentos. Tal metodo retorna uma string contendo todos id's de chars
        encontrados na primeira query, de modo que nao seja necessario a realizacao
        de diversas querys
        */
       String queryString = new String("");
        for(int i=0; i< busca_char.size(); i++){
            queryString = queryString.concat(" idChar = '"+busca_char.get(i)[1] + "' OR ");
        }
        
        // Tirando o OR final colocado em branco
        queryString = queryString.substring(0, queryString.length() - 4);
        
        return queryString;
    }
} 
