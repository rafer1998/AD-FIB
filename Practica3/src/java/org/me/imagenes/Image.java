package org.me.imagenes;

public class Image {
    public int id;
    public String titulo;
    public String autor;
    public String descripcion;    
    public String creaDate;
    public String keywords;     
    
    public Image(){    
    }
    
    public Image(int id, String Titulo, String Autor, String Descripcion, String creaDate, String keywords){
        this.id = id;
        this.titulo = Titulo;
        this.autor = Autor;
        this.descripcion = Descripcion;
        this.creaDate = creaDate;
        this.keywords = keywords;
    }
}
