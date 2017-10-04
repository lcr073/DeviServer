package javaserverws;

import java.util.Map;

public class User {
    private String EnderecoUsuario; 
    private String Usuario;
    private String idUser;
    private boolean logou;
    private boolean charSelect;
    private String idCharSelect;
    private String nomeCharSelect;     
    private String X;
    private String Y;
    private Map<String,String[]> armazemConta;
    private Map<String,String[]> inventarioChar;    
    private Map<String,String> equipChar;  
    private String sexo;   
    private String estiloCabelo;
    private String corCabelo;
    private String level;
    private String hp;
    private String sp;
    private String atribStr;    
    private String atribAgi;    
    private String atribDex;    
    private String atribInt;    
    private String atribLuck;        
    
    public User(String address){
        EnderecoUsuario = address;   
        logou = false;
    }
    
    public void setarmazemConta(Map<String,String[]> item){
        armazemConta = item;
    }
    
    public void setsexo(String sexo){
        this.sexo = sexo;
    }
    
    public String getsexo(){
        return this.sexo;
    }       
    
    public void setnomeCharSelect(String nomeCharSelect){
        this.nomeCharSelect = nomeCharSelect;
    }
    
    public String getnomeCharSelect(){
        return this.nomeCharSelect;
    }       
    
    public void setestiloCabelo(String estiloCabelo){
        this.estiloCabelo = estiloCabelo;
    }
    
    public String getestiloCabelo(){
        return this.estiloCabelo;
    }    
    
    public void setcorCabelo(String corCabelo){
        this.corCabelo = corCabelo;
    }    
    
    public String getcorCabelo(){
        return this.corCabelo;
    }      
    
    public void setlevel(String level){
        this.level = level;
    }        
    
    public String getlevel(){
        return this.level;
    }  
    
    public void sethp(String hp){
        this.hp = hp;
    }        
    
    public String gethp(){
        return this.hp;
    }      

    public void setsp(String sp){
        this.sp = sp;
    }        
    
    public String getsp(){
        return this.sp;
    }      
    
    public void setatribStr(String atribStr){
        this.atribStr = atribStr;
    }        
    
    public String getatribStr(){
        return this.atribStr;
    }      

    public void setatribAgi(String atribAgi){
        this.atribAgi = atribAgi;
    }            

    public String getatribAgi(){
        return this.atribAgi;
    }      
    
    public void setatribDex(String atribDex){
        this.atribDex = atribDex;
    }            
    
    public String getatribDex(){
        return this.atribDex;
    }        
    
    public void setatribInt(String atribInt){
        this.atribInt = atribInt;
    }            
    
    public String getatribInt(){
        return this.atribInt;
    }       
 
    public void setatribLuck(String atribLuck){
        this.atribLuck = atribLuck;
    }           
    
    public String getatribLuck(){
        return this.atribLuck;
    }          
    
    public void setinventarioChar(Map<String,String[]> item){
        inventarioChar = item;
    }
    
    public void setequipChar(Map<String,String> item){
        equipChar = item;
    }
    
    public Map<String,String> getequipChar(){
        return this.equipChar;
    }
    
    public void setX(String X){
        this.X = X;
    }

    public String getX(){
        return this.X;
    }
    
    public void setY(String Y){
        this.Y = Y;
    }

    public String getY(){
        return this.Y;
    }    
    
    public void setidUser(String idUser){
        this.idUser = idUser;
    }

    public String getidUser(){
        return this.idUser;
    }
    
    public void setLogou(boolean status){
        this.logou = status;
    }
    
    public String getidCharSelect(){
        return this.idCharSelect;
    }
    
    public void setidCharSelect(String idCharSelect){
        this.idCharSelect = idCharSelect;
    }

    public boolean getcharSelect(){
        return this.charSelect;
    }
    
    public void setcharSelect(boolean charSelect){
        this.charSelect = charSelect;
    }
    
    public void setUsuario(String usuario){
        this.Usuario = usuario;
    }

    public String getUsuario(){
        return this.Usuario;
    }
    
    public boolean getLogou(){
        return this.logou;
    }
    public String getEnderecoUsuario(){
        return this.EnderecoUsuario;
    }
    
    // Agrupando dados do usuario e os enviando para selecao de char
    public String infoCharUsu(){
        
   
        
        String infoUsu = "infC : " + 
                this.getidCharSelect() + ":" +
                this.getnomeCharSelect() + ":" +
                this.getX() + ":" +
                this.getY() + ":" +
                this.getsexo() + ":" +
                this.getestiloCabelo() + ":" +
                this.getcorCabelo() + ":" +
                this.getatribStr() + ":" +
                this.getatribAgi() + ":" +
                this.getatribDex() + ":" +
                this.getatribInt() + ":" +
                this.getatribLuck() + ":" +
                this.gethp() + ":" +
                this.getsp()
        ;
        
        
        for (String key : getequipChar().keySet()) {
         // O metodo keySet volta uma lista com todas as chaves de um map            
            //Capturamos o valor a partir da chave
            String value = getequipChar().get(key);
            infoUsu = infoUsu.concat(":" + value);
        }

        return infoUsu;
    }
    
}
