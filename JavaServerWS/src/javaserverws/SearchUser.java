package javaserverws;
import java.util.ArrayList;
import com.pusher.java_websocket.WebSocket;

public class SearchUser {
    
    SearchUser(){
        
    }
    
    public User BuscaUsu(WebSocket conn, ArrayList ListaUsu){

        for(int i=0; i < ListaUsu.size(); i++){
            if(conn.getRemoteSocketAddress().toString().equals(((User)ListaUsu.get(i)).getEnderecoUsuario().toString())){
                return (User)ListaUsu.get(i);
            }
        }
        return new User("188.188.50.1");
        //return (User)null;
    }
}
