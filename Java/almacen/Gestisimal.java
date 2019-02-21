package almacen;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestisimal {
  public static void main(String[] args) {
    //Scanner:
    Scanner s = new Scanner (System.in);
    
    //Variables:
    int numeroMenu = 0;    
    int cantidadArticulos = 0;
    
    
    //Artículos:
    //Articulo libro = new Articulo(103049, "AAA", 5.5, 19.90, 50);   //Esto es un ejemplo puede ser borrado
    
    ArrayList<Articulo> art = new ArrayList<Articulo>();
    
    art.add(new Articulo(103049, "Libro", 5.5, 19.90, 100));
    art.add(new Articulo(103050, "TV", 500, 799.90, 50));
    art.add(new Articulo(103051, "PS4", 180, 390.90, 20));
    
    
    //Inicio de programa:
    while(numeroMenu!=7) {
      
      numeroMenu = menuPrincipal(s);
      
      switch(numeroMenu) {
        
        case 1:
          imprimeLista(art);
          //aquí la función
          break;
          
        case 2:
          //aquí la función
          break;
          
        case 3:
          //aquí la función
          break;
          
        case 4:
          //aquí la función
          break;
          
        case 5:
          //aquí la función
          break;
          
        case 6:
          
          //libro.salidaMercancia();  //Para pruebas, puede borrarse
          
          //aquí la función
          break;
          
        default:
          System.out.println("\n\nFIN DE PROGRAMA.");
          
          try {
            Thread.sleep(2000);
          } catch(Exception e) {}
          
          System.exit(0);
      }      
    }
    
  }
  
  
  //#####################################     MÉTODOS     #####################################\\
  
  
  /**
   * Éste método imprime por pantalla el menú del programa y lo repite mientras
   * la entrada del número no se corresponda con ninguna de las opciones.
   * 
   * @param s Objeto clase Scanner.
   * @return  Devuelve (int) número menú.
   */
  private static int menuPrincipal(Scanner s) {
    
    int numeroMenu;
    
    do {
      
      System.out.print("\n\nPor favor elija una opción:");
      System.out.print("\n(1) Listado");
      System.out.print("\n(2) Alta");
      System.out.print("\n(3) Baja");
      System.out.print("\n(4) Modificación");
      System.out.print("\n(5) Entrada de mercancia");
      System.out.print("\n(6) Salida de mercancia");
      System.out.println("\n(7) Salir");
      numeroMenu=s.nextInt();
      
    } while (numeroMenu<1 || numeroMenu>7);
    
    return numeroMenu;
    
  }
  
  
  /**
   * Imprime el listado completo de artículos almacenados.
   * 
   * @param art   Lista de artículos.
   */
  private static void imprimeLista(ArrayList<Articulo> art) {
    
    System.out.println("\n\nHa elegido la opción de mostrar listado de artículos:");
    
    for (int i=0; i<art.size(); i++) {
      System.out.print("\n\nArtículo nº (" + (i+1) + "): \n" + (art.get(i)).toString());      
    }
    
  }
  
  
}
