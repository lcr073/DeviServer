package javaserverws;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginValid {
    private String Usuario;
    private String Senha;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    LoginValid(String usuario, String senha){
            this.setUsuario(usuario);
            this.setSenha(senha);
    }
    

    public void setUsuario(String user){  this.Usuario = user;   }
    public void setSenha(String pass){ this.Senha = pass;  }
    public String getUsuario(){  return Usuario; }    
    public String getSenha(){ return Senha; }  
    
    
    // Método utilizado para validar se a senha fornecida por um usuario realmente é sua senha (somente a senha)
    public static boolean ValidPassUsu(User user, String pass){
            // Validacao
            ArrayList<String[]> busca_senha = Db_query.DbQuery("SELECT senha FROM login WHERE idLogin = '" + user.getidUser() +"'");

            // Tenta retornar a senha vendo se é verdadeira
            try{
                String senha = busca_senha.get(0)[1];

                  if(senha.equals(pass)){
                        return true;
                  }else{
                      return false;
                  }  
            }
            catch(IndexOutOfBoundsException e){
                            return false;   
            }        
    }
    


    // Método utilizado para validar um certo personagem pertence a alguma conta fornecida
    public static boolean ValidCharAcc(User user, String idChar){
            // Validacao
            ArrayList<String[]> busca_char_acc = Db_query.DbQuery("SELECT idLogin FROM charc WHERE idChar = '" + idChar +"'");

            // Tenta retornar a senha vendo se é verdadeira
            try{
                String idLoginChar = busca_char_acc.get(0)[1];

                // Compara se o id do dono do personagem é o mesmo da conta que esta logada
                  if(idLoginChar.equals(user.getidUser())){
                        return true;
                  }else{
                      return false;
                  }  
            }
            catch(IndexOutOfBoundsException e){
                            return false;   
            }        
    }    

    

    public boolean Validlogin(User user){
        // Valida se usuario e senha estao com tamanho corretos
        if((this.Usuario.length() < 6) || (this.Usuario.length() > 64) || (this.Senha.length() < 6) || (this.Senha.length() > 30)){
            System.out.println(ANSI_RED + "Comprimento do usuario e / ou senha invalidos" + ANSI_WHITE);    
            return false;
        }
        else{
            // Validacao
            // ArrayList busca_senha = Db_query.DbQuery("SELECT senha FROM login WHERE usuario = '" + getUsuario() +"'");
            ArrayList<String[]> busca_senha = Db_query.DbQuery("SELECT idLogin, senha FROM login WHERE usuario = '" + getUsuario() +"'");

                   // System.out.println("result" + busca_senha.get(0)[2]);

            try{
                String senha = busca_senha.get(0)[2];

                  if(this.getSenha().equals(senha)){
                     user.setidUser(busca_senha.get(0)[1]);
                    System.out.println(ANSI_GREEN + "[Logou]" + user.getEnderecoUsuario()+ " : " + "Usuario: "+ this.getUsuario() +  ANSI_WHITE); 
                        return true;
                  }else{
                        System.out.println(ANSI_RED + "[Senha Incorreta] " + user.getEnderecoUsuario()+ " : " + "Usuario: "+ this.getUsuario() +  " Senha: " + this.getSenha() +  ANSI_WHITE);                  
                      return false;
                  }  
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(ANSI_RED + "[Usuario Incorreto] " + user.getEnderecoUsuario()+ " : " + " Usuario: " + this.getUsuario() +  ANSI_WHITE);
                            return false;   
            }
        }
    }
}

                