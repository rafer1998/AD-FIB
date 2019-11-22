package org.me.imagenes;


public class Image {
    private int id;
    private String titulo;
    private String autor;
    private String descripcion;    
    private String creaDate;
    private String keywords;     
    private String fichero;
    
    public Image(){    
    }
    
    public Image(int id, String Titulo, String Autor, String Descripcion, String creaDate, String keywords, String fichero){
        this.id = id;
        this.titulo = Titulo;
        this.autor = Autor;
        this.descripcion = Descripcion;
        this.creaDate = creaDate;
        this.keywords = keywords;
        this.fichero = fichero;
    }
    
    //getters
    public int getID(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public String getCreaDate(){
        return creaDate;
    }
    
    public String getKeywords(){
        return keywords;
    }
    
    public String getFichero(){
        return fichero;
    }
    
    //setters
    public void setID(int id){
        this.id = id;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public void setCreaDate(String creaDate){
        this.creaDate = creaDate;
    }
    
    public void setKeywords(String keywords){
        this.keywords = keywords;
    }
    
    public void setFichero(String fichero){
        this.fichero = fichero;
    }
}
