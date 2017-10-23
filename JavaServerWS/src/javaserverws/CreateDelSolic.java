package javaserverws;

import java.util.ArrayList;
import java.util.List;

public class CreateDelSolic {
/*
    Classe que possui metodos relacionados a criacao de elementos internos do 
    game onde podemos ter interacoes com DB como por exemplo a criacao de uma
    conta de usuario ou ate mesmo do personagem de tal.
    */    
    
    // Parametros de limites
    static Integer maxNome = 200;
    static Integer minNome = 6;
    
    static Integer maxUsuario = 63;
    static Integer minUsuario = 6;
    
    static Integer maxSenha = 30;
    static Integer minSenha = 6;
    
    static Integer maxEmail = 63;
    static Integer minEmail = 6;
    
    public static String CreateSolicAccount(String nome, String usuario,
            String senha, String email){
    /* 
        Metodo que realizara as avaliacoes das informacoes do usuario para
        criacao da conta propriamente dita
        */
    
                // Validacao se tudo chegou

                // Realizando validacoes mais internas dos campos
                // Validacao do nome
                if((nome.length() >= minNome) && (nome.length() <= maxNome)){
                    // Validacao do usuario
                    if((usuario.length() >= minUsuario) && (usuario.length() <= maxUsuario)){

                        // Validacao da senha
                        if((senha.length() >= minSenha) && (senha.length() <= maxSenha)){

                            // Validacao do email
                            if(email.matches("\\w{" + minEmail + "," + maxEmail +"}@\\w*\\.\\w*\\.?\\w*")){
  
                                        // Verificacoes concluidas com sucesso inicio da gravacao no DB
                                        String query = "INSERT INTO login(usuario,senha,nome_usuario,email_usuario) VALUES ('"+ usuario +"','"+ senha +"','"+ nome +"','"+ email + "')";
                                        Db_query.Insert(query);
                                        return "iCAS";
                            }else{
                                //System.out.println("Formato do email invalido");    
                                return "iCAF";
                            }                                

                        }else{
                            //System.out.println("Tamanho da senha invalida");    
                            return "iCAF";
                        }

                    }else{
                        //System.out.println("Tamanho do usuario invalido");    
                        return "iCAF";
                    }

                }else{
                    //System.out.println("Tamanho do nome invalido");    
                    return "iCAF";
                }
    }
    
    public static String CreateSolicChar(String idUsuario, String nomeChar,
            String estiloCabelo, String corCabelo, String sexo,
            String corOlhos, String str, String agi, String dex, String inte,
                    String luk){
        
        // Definicao do maximo de personagens que uma conta pode ter
        int ValMaxChars = 4;
        int MinNomeChar = 5;
        int MaxNomeChar = 33;
        int MaxAtribPoints = 5;
        
        
        List<Integer> estiloCabeloDisp = new ArrayList<Integer>();
            estiloCabeloDisp.add(0);
            estiloCabeloDisp.add(1);
            estiloCabeloDisp.add(2);
            estiloCabeloDisp.add(3);   
            
        List<Integer> corCabeloDisp = new ArrayList<Integer>();
            corCabeloDisp.add(0);
            corCabeloDisp.add(1);
            corCabeloDisp.add(2);
            corCabeloDisp.add(3);  
            
        List<Integer> corOlhosDisp = new ArrayList<Integer>();
            corOlhosDisp.add(0);
            corOlhosDisp.add(1);
            corOlhosDisp.add(2);
            corOlhosDisp.add(3);      
            
        List<Integer> sexoDisp = new ArrayList<Integer>();
            sexoDisp.add(0);
            sexoDisp.add(1);
            sexoDisp.add(2);
            sexoDisp.add(3);    
    
        try{
            /* 
                Metodo que realizara as avaliacoes das informacoes do usuario para
                criacao do char propriamente dito
                */
                // Valida se o usuario ja nao esta com o numero maximo de chars
               String query = "SELECT * FROM charc WHERE idLogin = '" + idUsuario + "'";
               if(Db_query.numTuplas(query) >= ValMaxChars){
                   System.out.println("Numero de chars maior que o permitido");
                   return "iCCF";
               }
               else{
               // Validacao se tudo chegou corretamente     
                   // Validacao de usuario sem espaços com tamanho correto
                   if(nomeChar.matches(".\\S{"+ MinNomeChar +","+ MaxNomeChar +"}")){

                       // Validacao de estilo de cabelo valido
                       if(estiloCabeloDisp.contains(Integer.parseInt(estiloCabelo))){

                           //  Validacao de cor de cabelo valido                            
                           if(corCabeloDisp.contains(Integer.parseInt(corCabelo))){

                               // Validacao de sexo selecionado
                               if(sexoDisp.contains(Integer.parseInt(sexo))){
                                   // Validacao de cor de olhos validos 
                                   if(corOlhosDisp.contains(Integer.parseInt(corOlhos))){

                                       // Validando se a soma das distribuicoes dos atribuots sao iguais aos pontos fornecidos
                                       if((Integer.parseInt(str) + Integer.parseInt(agi) + Integer.parseInt(dex) + Integer.parseInt(inte) + Integer.parseInt(luk)) == MaxAtribPoints){

                                         // Realiza a insercao no DB
                                            String queryInsert = "INSERT INTO charc(idLogin, nomeChar,sexo,est_cabelo,cor_cabelo,atrib_str,atrib_agi,atrib_dex,atrib_int,atrib_luck,cor_olhos) VALUES ('" + idUsuario + "','" + nomeChar +"','"+ sexo +"','"+  estiloCabelo +"','"+ corCabelo +"','" + str +"','"+ agi +"','"+ dex +"','"+ inte +"','"+ luk +"','"+   corOlhos + "')";
                                            Db_query.Insert(queryInsert);                                           

                                            // Povoa o DB de equips para reconhecer o novo char
                                            // Busca o id do Char recem criado
                                            String querySearchNewChar = "SELECT idChar FROM charc WHERE nomeChar = '" + nomeChar + "'";
                                            ArrayList<String[]> obtemIdChar = Db_query.DbQuery(querySearchNewChar);
                                            String idChar = obtemIdChar.get(0)[1];                                        

                                            // Povoando o db de equips
                                            String queryInsertEquips = "INSERT INTO charequip(idChar) VALUES ('" + idChar + "')";
                                            Db_query.Insert(queryInsertEquips); 
                                           return "iCCS";    
                                       }
                                       else{
                                           // Nao tem a soma dos atributos corretos
                                           return "iCCF";
                                       }
                                   }
                                   else{
                                       // Cor de olho selecionado nao disponivel
                                       return "iCCF";                                        
                                   }                                    
                               }else{
                                   // Sexo / raca selecionado nao disponivel
                                   return "iCCF";                                    
                               }
                           }
                           else{
                               // Cor de cabelo selecionado nao disponivel
                               return "iCCF";                                
                           }
                       }
                       else{
                           // Estilo de cabelo selecionado nao disponivel
                           return "iCCF";
                       }
                   }
                   else{
                       // Nome do char fora dos padroes necessitados
                       return "iCCF";
                   }
               }        

        }
        catch(NumberFormatException e){
            System.out.println("Dados invalidos inseridos !");
            return "iCCF";
        }
    }    
}
