package javaserverws;

import com.pusher.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.Collection;
import static javaserverws.DbBuffer.DbBuffer;

public class InfosClient implements Runnable{
        
        private ArrayList <User> ListaUsu;
        private Collection<WebSocket> con;
        
        public InfosClient(ArrayList<User> ListaUsu, Collection<WebSocket> con)
        {
            this.ListaUsu = ListaUsu;
            this.con = con;
        }
        
        // Método que recebe a string, recebe as conexoes e envia para todo mundo um dado
        public void sendToAll( String text , Collection<WebSocket> con ) {
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}
        
        // Thread de envio de informações aos usuarios do jogo
        public void run(){
            while(true){
                try{
                    String infosUsu = new String("");
                    
                   // Realiza os processamentos
                   for(User Usuario: ListaUsu){
                       // Se o usuario ainda nao selecionou o personagem, nao coloca na lista de informações para os outros usuarios
                       if(Usuario.getnomeCharSelect() == null){ }
                       else
                       {
                           // Colocando os 2 pontos de separacao mas nao na primeira info
                           if(infosUsu.equals("")){
                                infosUsu = infosUsu.concat(Usuario.infoCharUsu());                               
                           }
                           else
                           {
                                infosUsu = infosUsu.concat(":" + Usuario.infoCharUsu());                                                              
                           }

                       }
                    }
                    
                   // Faz a thread dormir por 0.5 segundos                
                    Thread.sleep(500);
                    System.out.println(infosUsu);
                    
                    
                    sendToAll(infosUsu,con);

                }catch(InterruptedException e){
                    System.out.println("Erro no thread de envio de informações ao cliente: " + e);
                }            
            }
       }
}
