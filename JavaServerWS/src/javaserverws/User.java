package javaserverws;

public class User {
    private String EnderecoUsuario; 
    private String Usuario;
    private String idUser;
    private boolean logou;
    private boolean charSelect;
    private String idCharSelect;
    private String X;
    private String Y;
    
    public User(String address){
        EnderecoUsuario = address;   
        logou = false;
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
    
    public void setidCharSelect(String status){
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
}
