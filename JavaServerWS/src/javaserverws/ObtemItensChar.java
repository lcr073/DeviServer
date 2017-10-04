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
    
    
        public void carregaEquipsMEM(User usuario){
            // chave de mapeamento de equipamentos
            String ItemCabeca = "1";
            String ItemFace = "2";
            String ItemCostas = "3";            
            String ItemMaoEsq = "4";            

            // Carregando os itens equipados
                   ArrayList<String[]> equips_char = Db_query.DbQuery("SELECT  idItemCabeca,idItemFace, idItemCostas, idItemMaoEsq FROM charEquip AS cE WHERE cE.idChar = '" + usuario.getidCharSelect() +"'");

                   for(int i = 0; i<equips_char.size();i++){
                       if(!(equips_char.get(i)[1].equals("0"))){
                           equipChar.put(ItemCabeca, equips_char.get(i)[1]);
                       }
                       if(!(equips_char.get(i)[2].equals("0"))){
                           equipChar.put(ItemFace, equips_char.get(i)[2]);
                       }                       
                       if(!(equips_char.get(i)[3].equals("0"))){
                           equipChar.put(ItemCostas, equips_char.get(i)[3]);
                       }                                              
                       if(!(equips_char.get(i)[4].equals("0"))){
                           equipChar.put(ItemMaoEsq, equips_char.get(i)[4]);
                       }                                                               
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
                  ArrayList<String[]> carac_char = Db_query.DbQuery("SELECT nomeChar,lvChar,x,y,sexo,est_cabelo,cor_cabelo,atrib_str,atrib_agi,atrib_dex,atrib_int,atrib_luck,hp,sp FROM charc WHERE idChar = '" + usuario.getidCharSelect() +"'");
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
                  }   
        }          
}
