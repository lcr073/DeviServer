package javaserverws;
import com.pusher.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ProcessaSolic {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";    
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
                try{
                    LoginValid login = new LoginValid(parts_msg[1], parts_msg[2]);

                    if(login.Validlogin(usuario))
                    { 
                        // Abrindo a "sessão" do usuario
                        usuario.setLogou(true);
                        usuario.setUsuario(parts_msg[1]);
                        conn.send("Logou");

                        // Registrando data de acesso (d) date
                        String[] data_acesso = new String[2];

                        data_acesso[0] = "d";
                        // id usuario
                        data_acesso[1] = usuario.getidUser();
                        DbBuffer.DbBuffer.add(data_acesso);

                        // Registrando ultimo endereco de acesso (a) address
                        String[] usuario_address = new String[3];
                        usuario_address[0] = "a";
                        usuario_address[1] = usuario.getidUser();
                        usuario_address[2] = usuario.getEnderecoUsuario();
                        System.out.println(usuario_address[0] + usuario_address[1] + usuario_address[2]);
                        DbBuffer.DbBuffer.add(usuario_address);  
                    }
                    else
                    {
                       conn.send("Invalido");
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(ANSI_RED+ "Usuario e / ou senha em branco" + ANSI_WHITE);
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
                       
                        // Atribuindo o valor do id ao objeto do usuario
                        usuario.setidCharSelect(parts_msg[1]);
                        
                       // Carregando todos os itens ligados ao personagem selecionado
                       //ObtemItensChar
                       ObtemItensChar pegaItens = new ObtemItensChar();
                       pegaItens.carregaArmazemoCharMEM(usuario);
                       pegaItens.carregaEquipsMEM(usuario);
                       pegaItens.carregaInventarioCharMEM(usuario);
                       pegaItens.carregaCaracCharMEM(usuario);
                       conn.send(usuario.infoCharUsu());
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
