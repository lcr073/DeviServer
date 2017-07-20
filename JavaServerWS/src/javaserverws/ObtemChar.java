package javaserverws;

import com.pusher.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.Map;

public class ObtemChar {
            /*
        Os atributos dbComecaItem e dbTerminaItem se referem ao indice da coluna do banco de dados resultante da query
        abaixo (equips_char) para indicar os valores de onde se inicia as colunas referentes
        aos itens e onde encerram, atualmente por exemplo temos o indice 3 referente a idItemCbeca e indice 6 referente
        ao idItemMaoEsq        
        */
        private static final int dbComecaItem = 3;
        private static final int dbTerminaItem = 6;
    
    
    public static void Obtem_char(WebSocket conn, String idLogin, Map<String,String[]> itens){

        
        // Obtem os id e nome dos personagens e seus respectivos equipamentos equipados
    ArrayList<String[]> equips_char = Db_query.DbQuery("SELECT cE.idChar, nomeChar , idItemCabeca,idItemFace, idItemCostas, idItemMaoEsq FROM charEquip AS cE INNER JOIN charc AS c ON(cE.idChar = c.idChar) WHERE idLogin = '" + idLogin +"'");

    // Obtem os id originais (globais) dos equipamentos dos usuarios
    ArrayList<String[]> id_glob_equips_char = Db_query.DbQuery("SELECT idInstanciaItem, idListaItem FROM instanciaItem WHERE " + stringQueryBuscaIDEquip(equips_char) +";");
           
             // Varredura entre todos os personagens
             for(int i =0; i< equips_char.size(); i++){
                 
                 //String de envio ao cliente 
                 // OBS: equips_char.get(i)[1] e [2] correspondem ao id e nome do char
                 String resp = new String("iC:" + equips_char.get(i)[1] + ":" + equips_char.get(i)[2]);
                 
                 // varredura entre todos os slots de equipamentos do personagem
                 for(int l = dbComecaItem; l < (dbTerminaItem + 1); l++){
                     //Varre o vetor de equips instanciados para achar uma correspondencia
                     // ao valor escolhido
                     for(int k = 0; k < id_glob_equips_char.size(); k++){
                         if((equips_char.get(i)[l]).equals(id_glob_equips_char.get(k)[1])){
                             // Ao ser igual, substituimos o valor do array para o valor do item geral
                             equips_char.get(i)[l] = id_glob_equips_char.get(k)[2];
                         }
                     }
                     
                     // Concatenando os elementos da resposta
                     resp = resp.concat(":" + equips_char.get(i)[l]);
                 }
                 
                  // Respondendo ao cliente
                   conn.send(resp);
              }
    }
      
        private static String stringQueryBuscaIDEquip(ArrayList<String[]> equips_char){
        /*
        Metodo que retorna a string que deve ser inserida na query de busca de
        equipamentos. Tal metodo retorna uma string contendo todos id's dos equipamentos
        equipados em cada char, tal string tera o id de todos os itens "unicos" para serem
        filtrados da tabela de itens instanciados em geral
        */
       String queryString = new String("");
       queryString = queryString.concat("idInstanciaItem IN(");
        for(int i=0; i< equips_char.size(); i++){
            // É adicionada uma virgula para cada nova ocorrencia nao provocar erros na query
        //   if(i > 0){queryString = queryString.concat(",");}
         //   queryString = queryString.concat(equips_char.get(i)[3] + "," + equips_char.get(i)[4] + "," + equips_char.get(i)[5] + "," + equips_char.get(i)[6]);
           
            for(int v = dbComecaItem; v < (dbTerminaItem + 1); v++){
                // Checamos se o personagem realmente esta equipando algo antes de incluir na verificacao
                // (0) significa que o personagem nao esta equipando nada naquele slot naquel momento
                  if(!equips_char.get(i)[v].equals("0")){
                    //  Para nao colocarmos uma virgula de separacao no primeiro elemento  
                    if(!queryString.equals("idInstanciaItem IN(")){queryString = queryString.concat(",");}
                    // Inserindo elementos na string de busca
                    queryString = queryString.concat(equips_char.get(i)[v]);                                            
                }
             }
          System.out.println(queryString);
        }
        queryString = queryString.concat(")");
       
        return queryString;
    }
} 
