/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaropa;

/**
 *
 * @author arturv
 */
public class Ropa {
    
    
    private String idropa,descripcion; 
    private int cantidad,valoracion;
    private double precio;
    private String categoria;
    private static int contador;
    
    public String getdescripcion()
    {
        return descripcion;
    }
    
    public void setdescripcion(String decripcion)
    {
        this.descripcion = descripcion;
    }
    
    public int getcantidad()
    {
        return cantidad;
    }
    
    public void setcantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }
    
    public double getprecio()
    {
        return precio;
    }
    
    public void setprecio(double precio)
    {
        this.precio = precio;
    }
     
    public int getvaloracion()
    {
        return valoracion;
    }
    
    public void setvaloracion(int valoracion)
    {
        this.valoracion = valoracion;
    }
      
    public String getcategoria()
    {
        return categoria;
    }
    
    public void setcategoria(String categoria)
    {
        this.categoria = categoria;
    }
    
    public String getid()
    {
        return idropa;
    }
    
    public void setid(String idropa)
    {
        this.idropa = idropa;
    }

    public Ropa()
    {
        
        descripcion="Descripción por defecto";
        cantidad = 1;
        precio = 1;
        valoracion=5;
        categoria="Sin categoría";
        ponid();
        
    }
    
    private void ponid()
    {
        idropa = categoria + contador;
        contador++;
    }
    
    public Ropa(String descripcion,int cantidad, double precio, int valoracion, String categoria)
    {
        this.descripcion=descripcion;
        this.cantidad=cantidad;
        this.precio=precio;
        this.valoracion = valoracion;
        this.categoria = categoria;
        ponid();  
    }
}
