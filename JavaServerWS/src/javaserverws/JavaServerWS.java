package javaserverws;
import java.net.InetSocketAddress;

import com.pusher.java_websocket.WebSocket;
import com.pusher.java_websocket.WebSocketImpl;
import com.pusher.java_websocket.handshake.ClientHandshake;
import com.pusher.java_websocket.server.WebSocketServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JavaServerWS extends WebSocketServer{
        // Lista onde armazenamos todos os usuario logados e suas caracteristicas
        ArrayList <User> ListaUsu = new ArrayList<>();
        

        
        // Criamos uma instancia de listaItem
        // Classe que possui um método que obtem todos os itens do db os retorna
        // na forma de um mapa onde teremos como a primeira chave o id de tal objeto
        // e na segunra um array contendo todas as informações do item em si
        listaItem listaItem = new listaItem();
        Map<String,String[]> itens = listaItem.listaItem();
        
        // Mapa onde sao adicionados a medida que necessitamos o relacionamento
        // entre um item com seu id especifico e seu item global, principalemnte
        // para efeito de comercio ou visualizacao no cliente
        
       private static Map<String,String> relac_item_espec_global = new HashMap<String,String>();
       
        public static Map<String,String> get_relac_item_espec_global(){
            return relac_item_espec_global;
        }
        
        public static void set_relac_item_espec_global(String key, String value){
           relac_item_espec_global.put(key, value);
         }
        
        public JavaServerWS(InetSocketAddress address) {
		super(address);
                
                 // Criando uma atributo para passar para a thread de informações
                // Thread de envio de informações ao cliente
                Runnable enviaInfoCli = new InfosClient(ListaUsu, connections());
                new Thread(enviaInfoCli).start();
               
                // Acrescentando a referenca que um objeto vazio e 0
                this.set_relac_item_espec_global("0","0");
	}
        
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
                // Cria um novo usuario ja com seu endereco vinculado 
                ListaUsu.add(new User(conn.getRemoteSocketAddress().toString()));
               System.out.println("Usuario cadastrado: " + conn.getRemoteSocketAddress().toString()); 
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
                //ListaUsu.remove(User)
	}
        
	@Override
	public void onMessage(WebSocket conn, String message) {
                System.out.println("usuario mandou:" + message);
               
                ProcessaSolic proc = new ProcessaSolic(conn, message, ListaUsu,itens);
                              
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	}
	

    public static void main(String[] args) {
        	String host = "localhost";
                //String host = "192.168.42.28";
		int port = 8887;

		WebSocketServer server = new JavaServerWS(new InetSocketAddress(host, port));
                System.out.println("Servidor Iniciado");     
                
		server.start();
               
                // Thread de backupeamento com o DB
                DbBuffer backup = new DbBuffer();
                new Thread(backup).start();
                

                
               // InfosClient enviaInfos = new InfosClient();
                //new Thread(enviaInfos).start();
                
                System.out.println();


	/*	JavaServerWS s = new JavaServerWS(new InetSocketAddress(host, port));
		s.start();
                System.out.println("Servidor Iniciado");  


		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );                   
                String in;
	*/	
       /*         while ( true ) {
                        try{
                            in = sysin.readLine();                            
                        }catch(Exception e){
                           in = "";
                         System.out.println(e);
                        }


			s.sendToAll( in );
			if( in.equals( "exit" ) ) {
                                try{
                                    s.stop();                                    
                                }catch(Exception e){
                                    System.out.println(e);
                                }

				break;
			}
		}
*/

    }
    
    
    
            public void sendToAll( String text ) {
		Collection<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}
    
}
