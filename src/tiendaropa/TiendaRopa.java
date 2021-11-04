/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaropa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author arturv
 */
public class TiendaRopa {
    private static ArrayList<Ropa> ropas;
    /**
     * @param args the command line arguments
     */
    
    //Comprueba si existe una categoría especificada en un arraylist de strings.
    //Devuelve true si la categoría no existe.
    private static boolean noexistecategoria(String categoria, ArrayList<String> categorias)
    {
        boolean encontrado= false, sal = false;
        int cont=0;
        while(!sal)
        {
            if(cont<categorias.size())
            {
                if(categorias.get(cont).equals(categoria))
                {
                    encontrado=true;
                    sal = true;
                }
                else
                {
                    cont++;
                }
            }
            else
            {
                sal=true;
            }
        }
        if(encontrado)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    //Solicita al usuario una categoría para listar los productos.
    private static void listarproductoscategorias()
    {
        ArrayList<String> categorias;
        //Se lee la lista de categorías y se muestran por pantalla.
        categorias = mostrarcategorias();
        System.out.println("Introduce el nombre de la categoría de la cual quieres consultar los productos.");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        try
        {
             String nombrecat = lector.readLine();
             if(!noexistecategoria(nombrecat,categorias))
             {
                 mostrarproductosdecategoria(nombrecat);
             }
             else
             {
                 System.out.println("La categoría no existe.");
             }
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
        
    }
    
    //Muestra las categorías y devuelve un arraylist con estas.
    private static ArrayList<String> mostrarcategorias()
    {
        ArrayList<String> categorias;
        String categoria;
        categorias = new ArrayList<String>();
        int cont=0;
        for(Ropa prenda:ropas)
        {
            categoria = prenda.getcategoria();
            if(noexistecategoria(categoria,categorias))
            {
                categorias.add(categoria);
            }
        }
        
        System.out.println("Existen las siguientes categorías.");
        
        for(String cat:categorias)
        {
            cont++;
            System.out.println(cat);
        }
        System.out.println("Hay " + cont + " categorías.");
        return categorias;
    }
    
    //Solicita al usuario una categoría y un descuento para aplicar.
    private static void rebajascategoria()
    {
        ArrayList<String> categorias;
        double descuento;
        String nombrecat;
        //Se muestran las categorías y se recoge la lista.
        categorias = mostrarcategorias();
        
        System.out.println("Introduce el nombre de la categoría de la cual quieres hacer la rebaja.");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Scanner entrada=new Scanner(System.in);
        
        try
        {
            //El usuario introduce el nombre de una categoría.
            nombrecat = lector.readLine();
            if(!noexistecategoria(nombrecat,categorias))
            {
                //Si la categoría existe el usuario introduce el descuento.
                System.out.println("Introduce el tanto por ciento de descuento a aplicar.");
                descuento = entrada.nextDouble();
                //Se aplica el descuento.
                aplicadescuentocategoria(nombrecat,descuento);
                System.out.println("Descuento aplicado.");
            }
            else
            {
                System.out.println("La categoría introducida no existe.");
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
        
    }
    
    //Muestra los productos de una categoría
    private static void mostrarproductosdecategoria(String nombrecat)
    {
         for(Ropa elemento : ropas)
         {
            if(elemento.getcategoria().equals(nombrecat))
            {
                //Se muestra el producto
                muestraelemento(elemento);
            }
         }
    }
    
    //Aplica un descuento a una categoría
    private static void aplicadescuentocategoria(String nombrecat, double descuento)
    {
        double precio;
        //Recorre toda la arraylist de prendas
        for(Ropa elemento : ropas)
        {
            if(elemento.getcategoria().equals(nombrecat))
            {
                //Cuando el elemento es de la categoría seleccionada
                //Se obtiene el precio, se calcula el descuento y se modifica el precio.
                precio = elemento.getprecio();
                precio = precio -(precio /100 * descuento);
                elemento.setprecio(precio);
            }
        }
    }
    
    //Aplica una rebaja a una posición del arraylist.
    private static void rebajaprecio(int posicion,double rebaja)
    {
        double precio = ropas.get(posicion).getprecio();
        precio = precio -(precio /100 * rebaja);
        ropas.get(posicion).setprecio(precio);
    }
    
    //Encuentra la posición en la arraylist de una prenda buscando por el identificador.
    private static int dameposicion(String identificador)
    {
        boolean sal=false, encontrado = false;
        int cont=0;
        while(!sal)
        {
            if(cont<ropas.size())
            {
                if(ropas.get(cont).getid().equals(identificador))
                {
                    encontrado = true;
                    sal = true;
                }
                else
                {
                    cont++;
                }
            }
            else
            {
                sal=true;
            }
        }
        if (encontrado)
        {
            return cont;
        }
        else
        {
            return -1;
        }
    }
    
    //Pide al usuario datos para aplicar un descuento a un producto concreto.
    private static void asignarpreciorebaja()
    {
        mostrartodo();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduce el identificador del producto a rebajar.");
        String identificador;
        double rebaja;
        Scanner entrada=new Scanner(System.in);
        int pos=0;
        try
        {
            identificador = lector.readLine();
            pos=dameposicion(identificador);
            if(pos==-1)
            {
                System.out.println("Identificador no encontrado.");
            }
            else
            {
                System.out.println("Introduce el tanto por ciento de rebaja.");
                rebaja = entrada.nextDouble();
                rebajaprecio(pos,rebaja);
                System.out.println("Rebaja realizada, el precio final es: " + ropas.get(pos).getprecio());
            }            
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
    }
    
    //Muestra un elemento del tipo ropa.
    private static void muestraelemento(Ropa elemento)
    {
         System.out.println("Identificador - " + elemento.getid());
         System.out.println("Descripción - " + elemento.getdescripcion());
         System.out.println("Cantidad - " + elemento.getcantidad());
         System.out.println("Precio - " + elemento.getprecio());
         System.out.println("Valoración - " + elemento.getvaloracion());
         System.out.println("Categoría - " + elemento.getcategoria());
    }
    
    //Muestra toda la arraylist de prendas.
    private static void mostrartodo()
    {
        for(Ropa elemento:ropas)
        {
           muestraelemento(elemento);
        }
    }
    
    //Pide al usuario datos para eliminar una prenda concreta.
    private static void eliminarproducto()
    {
        mostrartodo();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduce el identificador del producto a eliminar.");
        String identificador;
        try
        {
            identificador = lector.readLine();
           //Se utiliza predicata para evitar hacer un bucle.
            Predicate<Ropa> personPredicate = p -> p.getid().equals(identificador); 
            ropas.removeIf(personPredicate);
            
            System.out.println("Prenda eliminada.");
            
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
    }
    
    //Pide al usuario los datos para dar de alta un producto.
    private static void ingresaproducto()
    {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Scanner entrada=new Scanner(System.in);
        
        String descripcion, categoria;
        int cantidad,valoracion;
        double precio;
        
        try
        {
            System.out.println("Introduce la descripción de la nueva prenda.");
            descripcion = lector.readLine();
            System.out.println("Introduce la cantidad.");
            cantidad = entrada.nextInt();
            System.out.println("Introduce el precio.");
            precio = entrada.nextDouble();
            System.out.println("Introduce la valoración.");
            valoracion = entrada.nextInt();
            System.out.println("Introduce la categoría.");
            categoria = lector.readLine();
            Ropa producto = new Ropa(descripcion,cantidad,precio,valoracion,categoria);
            ropas.add(producto);
            System.out.println("Prenda añadida."); 
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
    }
    
    //Informa de cuantas prendas hay en el arraylist.
    private static void contabilizar()
    {
        System.out.println("Existen " + ropas.size() + " prendas dadas de alta.");
    }
    
    //Suma el dinero invertido en prendas y también muestra el dinero invertido en categorías.
    private static void dineroinvertido()
    {
        ArrayList<String> categorias;
        double dinero=0;
        for(Ropa elemento:ropas)
        {
            dinero+=elemento.getprecio() * elemento.getcantidad();
        }
        System.out.println("El total de dinero invertido es de " + dinero + ".");
        
        categorias = mostrarcategorias();
        for(String categoria:categorias)
        {
            //para cada categoría sumo el precio de las prendas que pertenecen a esta.
            dinero=0;
            for(Ropa elemento:ropas)
            {
                if(elemento.getcategoria().equals(categoria))
                {
                    dinero+=elemento.getprecio() * elemento.getcantidad();
                }
            }
            System.out.println("El dinero total invertido en la categoría " + categoria + " es de " + dinero + ".");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada=new Scanner(System.in);
        ropas = new ArrayList<>();
        int opcion=0;
        try
        {
            do
            {
                System.out.println("Elige una opción.");
                System.out.println("1-Ingresar producto.");
                System.out.println("2-Eliminar producto.");
                System.out.println("3-Asignar precio de rebaja a un producto.");
                System.out.println("4-Rebajas en categoría.");
                System.out.println("5-Listar productos por categorías.");
                System.out.println("6-Contabilizar productos en el sistema.");
                System.out.println("7-Contabilizar dinero invertido.");
                System.out.println("8-Mostrar todos los productos.");
                System.out.println("0-Salir.");
                opcion=entrada.nextInt();
                switch(opcion)
                {
                    case 1:
                        ingresaproducto();
                        break;
                    case 2:
                        eliminarproducto();
                        break;
                    case 3:
                        asignarpreciorebaja();
                        break;
                    case 4:
                        rebajascategoria();
                        break;
                    case 5:
                        listarproductoscategorias();
                        break;
                    case 6:
                        contabilizar();
                        break;
                    case 7:
                        dineroinvertido();
                        break;
                    case 8:
                        mostrartodo();
                        break;
                    case 0:
                        System.out.println("Adios.");
                        break;
                    default:
                        System.out.println("Opción incorrecta.");
                }
            }while(opcion!=0);
        }
        catch(Exception ex)
        {
            System.out.println("Error.");
        }
    }
    
}
