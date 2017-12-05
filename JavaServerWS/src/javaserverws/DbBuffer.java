package javaserverws;

import java.util.ArrayList;

public class DbBuffer implements Runnable {
    
    // Lista de armazenamento do Buffer de Backupeamento com o DB
    public static ArrayList<String[]> DbBuffer = new ArrayList<String[]>(); 
    
    // Lista de armazenamento do Buffer de Backupeamento com o DB
    /*
     O DbBuffer_Interno é um buffer o qual armazena um certo periodo do DB 
     e o utiliza para alterações no banco, enquanto que o DbBuffer é para uso
     externo, onde tal é enviado pelas outras classes, na hora da execução 
     propriamente dita, a referencia para o buffer é passada para o interno 
     e o externo é limpo.
    */
    
    private static ArrayList<String[]> DbBuffer_Interno;
    
    public void run(){
        while(true){
                //Fazendo as trocas de buffers
                DbBuffer_Interno = DbBuffer;

            try{
                // Zerando a referencia do DbBuffer
                DbBuffer = new ArrayList<String[]>();
                
                // Realiza as operações no DB com a lista de mudancas
                ProcessaSolicBuffer SyncDB = new ProcessaSolicBuffer(DbBuffer_Interno);
                
                // Zerando a referencia do DbBuffer_Interno;
                DbBuffer_Interno = null;
                
                // Faz a thread dormir por 3 segundos                
                Thread.sleep(3*1000);            
            }catch(InterruptedException e){
                System.out.println("Erro no thread do db: " + e);
            }            
        }


    }
}
