package javaserverws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Classe listaItem armazenara todos os itens presentes no Db em um objeto para
acesso mais rapido a eles
*/

public class listaItem {
    //Método de mapeamento de todos os itens
     public Map<String,String[]> listaItem(){
       Map<String,String[]> listaItem = new HashMap<String,String[]>();
       ArrayList<String[]> obtem_itens = Db_query.DbQuery("SELECT idListaItem, nomeItem, valCompra, valVenda FROM listaItem");
       for(int i = 0; i < obtem_itens.size() ; i++){
            listaItem.put(obtem_itens.get(i)[1],obtem_itens.get(i));  
          
       }
       return listaItem;
    }
    

}
