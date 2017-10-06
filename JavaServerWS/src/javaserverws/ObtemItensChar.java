package javaserverws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObtemItensChar {
/*
    Classe utilizada, para ao ser selecionado um personagem especifico, esse ter todos os seus itens, tanto
    equipados, no armazem e no inventario carregados na memoria, na forma de um mapa onde serao 
    vinculado as posicoes indexadas ao id do equipamento unico em si
 */
    
    String idChar;
    
    Map<String,String[]> armazemConta= new HashMap<String,String[]>();
    Map<String,String[]> inventarioChar = new HashMap<String,String[]>();    
    Map<String,String> equipChar = new HashMap<String,String>();    
    Map<String,String> relac_item_espec_global = new HashMap<String,String>();    
    
        public void carregaEquipsMEM(User usuario){
            // chave de mapeamento de equipamentos
            String ItemCabeca = "1";
            String ItemFace = "2";
            String ItemPeito = "3";
            String ItemCostas = "4";            
            String ItemMaoEsq = "5";
            String ItemMaoDir = "6";                      
            String ItemMaos = "7";
            String ItemPernas = "8";
            String ItemPe = "9";
            String ItemAcessorioEsq = "10";
            String ItemAcessorioDir = "11";


            // Carregando os itens equipados
                   ArrayList<String[]> equips_char = Db_query.DbQuery("SELECT  idItemCabeca,idItemFace, idItemPeito, idItemCostas, idItemMaoEsq, idItemMaoDir, idItemMaos, idItemPernas, idItemPe, idAcessorioEsq, idAcessorioDir FROM charEquip AS cE WHERE cE.idChar = '" + usuario.getidCharSelect() +"'");
                  // String utilizada para realizar a pesquisa dos id's globais
                  // dos equips que o jogador possuir 
                  
                   String queryGlobal = ""; 

                   for(int i = 0; i<equips_char.size();i++){
                       if(!(equips_char.get(i)[1].equals("0"))){
                           equipChar.put(ItemCabeca, equips_char.get(i)[1]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[1]);
                       }else{
                           equipChar.put(ItemCabeca, "0");                           
                       }
                       
                       if(!(equips_char.get(i)[2].equals("0"))){
                           equipChar.put(ItemFace, equips_char.get(i)[2]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[2]);
                       }else{
                           equipChar.put(ItemFace, "0");                           
                       }                       
                       
                       if(!(equips_char.get(i)[3].equals("0"))){
                            equipChar.put(ItemPeito, equips_char.get(i)[3]);
                            queryGlobal = queryGlobal.concat("," + equips_char.get(i)[3]);
                       }else{
                           equipChar.put(ItemPeito, "0");                           
                       }
                       
                       if(!(equips_char.get(i)[4].equals("0"))){
                           equipChar.put(ItemCostas, equips_char.get(i)[4]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[4]);
                       }else{
                           equipChar.put(ItemCostas, "0");                           
                       }
                       
                       if(!(equips_char.get(i)[5].equals("0"))){
                           equipChar.put(ItemMaoEsq, equips_char.get(i)[5]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[5]);
                       }else{
                           equipChar.put(ItemMaoEsq, "0");                           
                       }
                       
                       if(!(equips_char.get(i)[6].equals("0"))){
                           equipChar.put(ItemMaoDir, equips_char.get(i)[6]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[6]);
                       }else{
                           equipChar.put(ItemMaoDir, "0");                           
                       }
                       
                       if(!(equips_char.get(i)[7].equals("0"))){
                           equipChar.put(ItemMaos, equips_char.get(i)[7]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[7]);
                       }else{
                           equipChar.put(ItemMaos, "0");                           
                       }    
                       
                       if(!(equips_char.get(i)[8].equals("0"))){
                           equipChar.put(ItemPernas, equips_char.get(i)[8]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[8]);
                       }else{
                           equipChar.put(ItemPernas, "0");                           
                       }  

                       if(!(equips_char.get(i)[9].equals("0"))){
                           equipChar.put(ItemPe, equips_char.get(i)[9]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[9]);
                       }else{
                           equipChar.put(ItemPe, "0");                           
                       }  

                       if(!(equips_char.get(i)[10].equals("0"))){
                           equipChar.put(ItemAcessorioEsq, equips_char.get(i)[10]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[10]);
                       }else{
                           equipChar.put(ItemAcessorioEsq, "0");                           
                       }  

                       if(!(equips_char.get(i)[11].equals("0"))){
                           equipChar.put(ItemAcessorioDir, equips_char.get(i)[11]);
                           queryGlobal = queryGlobal.concat("," + equips_char.get(i)[11]);
                       }else{
                           equipChar.put(ItemAcessorioDir, "0");                           
                       }                         
                    }   
                   
                   // Removendo a virgula inicial da string
                   queryGlobal = queryGlobal.substring(1);

                   ArrayList<String[]> corresp_especif_global = Db_query.DbQuery("SELECT idInstanciaItem, idListaItem FROM instanciaItem WHERE idInstanciaItem IN (" + queryGlobal + ")");                   

                    for(int i = 0; i< corresp_especif_global.size();i++){
                        System.out.println(corresp_especif_global.get(i)[1]);
                        JavaServerWS.set_relac_item_espec_global(corresp_especif_global.get(i)[1],corresp_especif_global.get(i)[2]);
                    }  
                   usuario.setequipChar(equipChar);
        }

        public void carregaInventarioCharMEM(User usuario){
            // Carregando os itens inventario
                   ArrayList<String[]> invent_char = Db_query.DbQuery("SELECT idInstanciaItem, slotIndexChar FROM inventarioChar WHERE idChar = '" + usuario.getidCharSelect() +"'");

                   for(int i = 0; i<invent_char.size();i++){
                       inventarioChar.put(invent_char.get(i)[2], invent_char.get(i));
                    }   
                   usuario.setinventarioChar(inventarioChar);
        }
        
        public void carregaArmazemoCharMEM(User usuario){
            // Carregando os itens armazem da conta
                  ArrayList<String[]> armazem_conta = Db_query.DbQuery("SELECT idInstanciaItem, slotIndexUsu FROM inventarioUsu WHERE idLogin = '" + usuario.getidUser() +"'");
                  for(int i = 0; i< armazem_conta.size();i++){
                     armazemConta.put(armazem_conta.get(i)[2], armazem_conta.get(i));
                  }   

                 usuario.setarmazemConta(armazemConta);
        }        
          
        public void carregaCaracCharMEM(User usuario){
            // Carregando as caracteristicas do char selecionado para o objeto do usuario
                  ArrayList<String[]> carac_char = Db_query.DbQuery("SELECT nomeChar,lvChar,x,y,sexo,est_cabelo,cor_cabelo,atrib_str,atrib_agi,atrib_dex,atrib_int,atrib_luck,hp,sp,cor_olhos FROM charc WHERE idChar = '" + usuario.getidCharSelect() +"'");
                  for(int i = 0; i< carac_char.size();i++){
                      usuario.setnomeCharSelect(carac_char.get(i)[1]);                      
                      usuario.setlevel(carac_char.get(i)[2]);                      
                      usuario.setX(carac_char.get(i)[3]);
                      usuario.setY(carac_char.get(i)[4]);
                      usuario.setsexo(carac_char.get(i)[5]);
                      usuario.setestiloCabelo(carac_char.get(i)[6]);
                      usuario.setcorCabelo(carac_char.get(i)[7]);
                      usuario.setatribStr(carac_char.get(i)[8]);
                      usuario.setatribAgi(carac_char.get(i)[9]);
                      usuario.setatribDex(carac_char.get(i)[10]);
                      usuario.setatribInt(carac_char.get(i)[11]);
                      usuario.setatribLuck(carac_char.get(i)[12]);
                      usuario.sethp(carac_char.get(i)[13]);
                      usuario.setsp(carac_char.get(i)[14]);   
                      usuario.setcorOlhos(carac_char.get(i)[15]);                        
                  }   
        }          
}
