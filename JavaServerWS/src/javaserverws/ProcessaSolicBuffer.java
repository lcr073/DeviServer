package javaserverws;

import java.util.ArrayList;
import java.util.Calendar;

public class ProcessaSolicBuffer {
    /*
      Classe responsavel por pegar a tag vinculada a solicitação do buffer
      e executar as mudanças necessarias no banco de dados
    */
    ProcessaSolicBuffer(ArrayList<String[]> DbBuffer_Interno){
        
        // Varrendo ArrayList para ir tomando as acoes de backup
        for(int i =0; i< DbBuffer_Interno.size(); i++){
            try{
            //Analisa primeiro espaço do array de stirng para tomar a decisão
                // d corresponde a ultima data e hora que o jogador realizou o login
                if(DbBuffer_Interno.get(i)[0].equals("d")){
                    Calendar cal = Calendar.getInstance();
                    String query = "UPDATE login SET data_hora_ultimo_login = '" + cal.get(Calendar.YEAR)+ "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH)+ " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "' WHERE idLogin =" + DbBuffer_Interno.get(i)[1] ;
                    Db_query.Update(query);
                }  

                if(DbBuffer_Interno.get(i)[0].equals("a")){
                    String query = "UPDATE login SET ultimo_endereco = '" + DbBuffer_Interno.get(i)[2] + "' WHERE idLogin = "+ DbBuffer_Interno.get(i)[1] ;
                    Db_query.Update(query);
                }              
            }catch(Exception e){
                System.out.println("Buffer de backup vazio");
            }
        }

    }
}
