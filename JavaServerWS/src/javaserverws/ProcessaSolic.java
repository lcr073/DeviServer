package javaserverws;
import com.pusher.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.Map;

public class ProcessaSolic {
    /*
        ProcessaSolic (Processa Solicitacoes)
        Classe que pega a mensagem recebida pelo WebSocket separa a tag 
        de servico e executa a acao correspondente necessaria a tag    
    */
    
    ProcessaSolic(WebSocket conn, String message, ArrayList ListaUsu,Map<String,String[]> itens){
        String [] parts_msg = message.split(":");
            SearchUser FindUsu = new SearchUser();
            User usuario = FindUsu.BuscaUsu(conn, ListaUsu);

            // Tag de servico relacionada a login (l)
            if(parts_msg[0].equals("l")){
                LoginValid login = new LoginValid(parts_msg[1], parts_msg[2]);
                if(login.Validlogin(usuario))
                { 
                    // Abrindo a "sessão" do usuario
                    usuario.setLogou(true);
                    usuario.setUsuario(parts_msg[1]);
                    conn.send("Logou");
                }
                else
                {
                   conn.send("Invalido");
                }
            }        
            
            // Tag de solicitacao de personagem (char)
            if(parts_msg[0].equals("c")){
                // Analisa se usuario realmente ja esta com a 'sessao aberta'
                if(usuario.getLogou()){
                    // Continua procedimento de login
                    ObtemChar.Obtem_char(conn,usuario.getidUser(),itens);
                }else{
                    // Usuario forcando selecao char sem ter logado
                    conn.send("Login Necessario");
                }
            }
            
            // Tag de solicitacao de personagem selecionado(iCS - id Char Selecionado)
            if(parts_msg[0].equals("iCS")){
                // Analisa se usuario realmente ja esta com a 'sessao aberta'
                if(usuario.getLogou()){
                       // Vincula ao objeto usuario as informações sobre esse personagem
                        usuario.setcharSelect(true);
                        usuario.setidCharSelect(parts_msg[1]);
                        
                }else{
                    // Usuario forcando selecao char sem ter logado
                    conn.send("Login Necessario");
                }
            }            
            
            // Comando de debug para resposta de valores randomicos
            if(parts_msg[0].equals("i")){
                conn.send("32:32");
            }
    }
}
