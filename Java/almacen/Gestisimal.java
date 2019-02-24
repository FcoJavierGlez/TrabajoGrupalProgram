package almacen;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Crea el programa GESTISIMAL (GESTIón SIMplificada de Almacén) para llevar
 * el control de los artículos de un almacén. De cada artículo se debe saber
 * el código, la descripción, el precio de compra, el precio de venta y el stock
 * (número de unidades). El menú del programa debe tener, al menos, las
 * siguientes opciones:
 * 
 * 1. Listado
 * 2. Alta
 * 3. Baja
 * 4. Modificación
 * 5. Entrada de mercancía
 * 6. Salida de mercancía
 * 7. Salir
 * 
 * La entrada y salida de mercancía supone respectivamente el incremento y
 * decremento de stock de un determinado artículo. Hay que controlar que no se
 * pueda sacar más mercancía de la que hay en el almacén.
 * 
 * 
 * @author Francisco Javier González Sabariego
 *
 */
public class Gestisimal {
  public static void main(String[] args) {
    //Scanner:
    Scanner s = new Scanner (System.in);
    
    //Variables:
    int numeroMenu = 0;    
    int cantidadArticulos = 0;
    
    
    //Artículos:    
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
          break;
          
        case 2:
          darDeAlta(art, s);
          break;
          
        case 3:
          darDeBaja(art, s);
          break;
          
        case 4:
          //aquí la función
          break;
          
        case 5:
          entradaMercancia(art, s);
          break;
          
        case 6:
          salidaMercancia(art, s);
          break;
          
        default:
          System.out.println("\n\nFIN DE PROGRAMA.");
          
        esperaSegundos(2);
          
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
  
  
  /**
   * Permite ingresar un nuevo artículo a la lista. Se requiere almacenar:
   * 
   * <ul>
   * <li>La descripción del artículo</li>
   * <li>El precio de compra</li>
   * <li>El precio de venta</li>
   * <li>Stock</li>
   * <li>Código (único)</li>
   * </ul>
   * 
   * @param art
   * @param s
   */
  private static void darDeAlta(ArrayList<Articulo> art, Scanner s) {
    String respuesta = "";
    int codigo;
    String descripcion = "";
    double precioCompra;
    double precioVenta;
    int stock = 0;
    
    //Informamos que va a introducir un nuevo artículo:
    System.out.print("\nHa seleccionado dar de alta un artículo:");
    
    //Pedimos la descripción del artículo:
    System.out.println("\n\nInserte la descripción del artículo:");
    s.nextLine();
    descripcion = s.nextLine();
    
    //Pedimos el precio de compra:
    System.out.println("\n\nInserte el precio de compra del artículo:");
    precioCompra = s.nextDouble();
    
    //Pedimos el precio de venta:
    System.out.println("\n\nInserte el precio de venta del artículo:");
    precioVenta = s.nextDouble();
    
    //Preguntamos si han entrado unidades en stock para añadir cantidad:
    do {
      System.out.println("\n\n¿Han entrado unidades en stock? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
    } while(!(respuesta.equals("S") || respuesta.equals("SI") || respuesta.equals("N") || respuesta.equals("NO")));
    
    /*
     * Si la repsuesta es sí añadimos una cantidad:
     */
    if (respuesta.equals("S") || respuesta.equals("SI")) {
      respuesta = "";      
      do {  //Evitamos números negativos:
        System.out.println("\n\n¿Cuántas unidades han entrado en stock?");
        stock = s.nextInt();
      } while (stock<0);      
    } else {    //Si la respuesta es no, por defecto el valor es 0:
      respuesta = "";
    }
    
    //Pedimos el código de artículo (NO SE PUEDE REPETIR!!):
    System.out.println("\n\nInserte el código del artículo:");
    codigo = s.nextInt();
    
    //Creamos el objeto:
    art.add(new Articulo(codigo, descripcion, precioCompra, precioVenta, stock));
    
    //Y comprobamos que no se haya repetido el código con ningún otro artículo de la lista:
    while((art.get(art.size()-1)).repiteCodigo(art, codigo)) {    //Comprobamos que en la última posición no se haya repetido código           
      //Si se ha repetido lo tenemos que cambiar a la fuerza:
      System.out.print("\n\nEl código del artículo está repetido, no puede repetir el código.");
      System.out.println("\n\nPor favor, inserte un código de artículo válido:");
      codigo = s.nextInt();      
      (art.get(art.size()-1)).setCodigo(codigo);   //Reasignamos el código
    }
    
    //Mostramos el resultado del artículo a agregar:
    System.out.println("\n\nUsted desea agregar el siguiente artículo:");
    (art.get(art.size()-1)).toString();    
    
    //Preguntamos si acepta los cambios (el objeto está creado por defecto):
    do {
      System.out.println("\n\n¿Desea guardar los cambios? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
    } while(!(respuesta.equals("S") || respuesta.equals("SI") || respuesta.equals("N") || respuesta.equals("NO")));
    
    //Y si no acepta entonces borramos el objeto:
    if (respuesta.equals("N") || respuesta.equals("NO")) {
      art.remove(art.size()-1);
    }
    
    
  }
  
  /**
   * Elimina un artículo de la lista de artículos. Para ello comprueba:
   * <ul>
   * <li>Que el número de existencias de ese artículo no sea superior a 0.</li>
   * <li>Que el artículo esté o no descatalogado.</li>
   * </ul>
   * 
   * @param art   Lista de artículos
   * @param s     Scanner
   */
  private static void darDeBaja(ArrayList<Articulo> art, Scanner s) {    
    String respuesta = "";
    int numero;
    
    //Preguntamos si quiere ver la lista de artículos:
    do {
      System.out.println("\n\n¿Desea ver la lista de artículos? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
    } while(!(respuesta.equals("S") || respuesta.equals("SI") || respuesta.equals("N") || respuesta.equals("NO")));
           
    //Si la respuesta es afirmativa imprimimos la lista:
    if (respuesta.equals("S")) {
      imprimeLista(art);
    }
    
    //Comprobamos que el artículo selecciondo sea corecto:
    numero = seleccionArticulo(art, s);
    
    /*
     * -Si quedan existencias del artículo no puede ser borrado.
     * -Si no quedan existencias del artículo pero éste no está descatalogado entonces se le advierte al usuario.
     * -Si no quedan existencias y el artículo está descatalogado se borra sin más.
     */
    if ((art.get(numero-1)).getStock()!=0) {
      System.out.println("\n\nUsted no puede dar de baja un artículo cuyas existencias sigan almacenadas.");
    } else if ((art.get(numero-1)).getStock()==0 && !(art.get(numero-1)).getDescatalogado()) {
      System.out.println("\n\n¡CUIDADO! El artículo no está descatalogado, ¿está seguro que desea darlo de baja? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
      System.out.println("\n\nEl artículo ha sido borrado con éxito.");
      if (respuesta.equals("S") || respuesta.equals("SI")) {
        art.remove(numero-1);
      }
    } else {
      art.remove(numero-1);
    }
  }
  
  
  /**
   * Método que se encarga de gestionar la entrada de artículos del almacén, 
   * certifando que no puedan ingresar un número negativo de artículos.
   * 
   * @param art   Lista de artículos
   * @param s     Scanner
   */
  private static void entradaMercancia(ArrayList<Articulo> art, Scanner s) {
    String respuesta = "";
    int numero;
    int cantidadArticulos;
    
    //Preguntamos si quiere ver la lista de artículos:
    do {
      System.out.println("\n\n¿Desea ver la lista de artículos? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
    } while(!(respuesta.equals("S") || respuesta.equals("SI") || respuesta.equals("N") || respuesta.equals("NO")));
           
    //Si la respuesta es afirmativa imprimimos la lista:
    if (respuesta.equals("S")) {
      imprimeLista(art);
    }
    
    //Comprobamos que el artículo selecciondo sea corecto:
    numero=seleccionArticulo(art, s);
    
    //Pedimos el número de artículos a ingresar:
    System.out.println("\n\n¿Cuántos artículos desea ingresar?");
    cantidadArticulos = s.nextInt();
    
    while(cantidadArticulos<0) {
      System.out.print("\n\nNo puede introducir una cantidad negativa de artículos, introduzca un valor positivo.");
      System.out.println("\n\n¿Cuántos artículos desea registrar?");
      cantidadArticulos = s.nextInt();
    }
    
    //Si el artículo está descatalogado no se puede añadir al almacén.
    if((art.get(numero-1)).getDescatalogado()) {
      System.out.println("\n\nLo siento, usted no puede añadir artículos descatalogados.");
    } else {    //De lo contrario se añaden más existencias:
      (art.get(numero-1)).setStock(cantidadArticulos);
    }
  }
  
  
  /**
   * Método que se encarga de gestionar la salida de artículos del almacén, 
   * certifando que no puede extraerse un número positivo de artículos.
   * 
   * @param art   Lista de artículos
   * @param s     Scanner
   */
  private static void salidaMercancia(ArrayList<Articulo> art, Scanner s) {
    String respuesta = "";
    int numero;
    int cantidadArticulos;
    
    //Preguntamos si quiere ver la lista de artículos:
    do {
      System.out.println("\n\n¿Desea ver la lista de artículos? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
    } while(!(respuesta.equals("S") || respuesta.equals("SI") || respuesta.equals("N") || respuesta.equals("NO")));
           
    //Si la respuesta es afirmativa imprimimos la lista:
    if (respuesta.equals("S")) {
      imprimeLista(art);
    }
    
    //Comprobamos que el artículo selecciondo sea corecto:
    numero=seleccionArticulo(art, s);
    
    System.out.println("\n\n¿Cuántos artículos desea retirar?");
    cantidadArticulos = s.nextInt();
    
    while(cantidadArticulos>0) {
      System.out.print("\n\nNo puede retirar una cantidad positiva de artículos, introduzca un valor negativo.");
      System.out.println("\n\n¿Cuántos artículos desea registrar?");
      cantidadArticulos = s.nextInt();
    }
    (art.get(numero-1)).setStock(cantidadArticulos);  
  }
  
  
  /**
   * Método que selecciona el artículo elegido por el usuario tras 
   * preguntar al usuario si es el articulo correcto o si desea 
   * seleccionar otro.
   * 
   * @param art   Lista de artículos
   * @param s     Scanner
   * @return      El número de índice (int) del artículo seleccionado
   */
  private static int seleccionArticulo(ArrayList<Articulo> art, Scanner s) {
    int numero;
    String respuesta = "";
    
    do {
      //Pedimos el número del artículo a sacar:
      System.out.println("\n\nIndique el número de artículo según la lista.");
      numero = s.nextInt();
      
      //Comprobamos que el número introducido sea correcto:
      if (numero<1 || numero>art.size()) {
        System.out.println("\n\n¡¡Número de artículo incorrecto!!");
      } else {
        System.out.print("\n\nHa seleccionado el artículo nº" + numero + ": \n" + (art.get(numero-1).toString()));
        System.out.println("\n\n¿Es correcto el artículo seleccionado? (S/N)");
        s.nextLine();
        respuesta = s.nextLine().toUpperCase();
      }
    } while (!(respuesta.equals("S") || respuesta.equals("SI")));
    
    return numero;    
  }
  
  /**
   * Espera una cantidad n de segundos para que le de tiempo a leer al usuario.
   */
  private static void esperaSegundos(int n) {
    try {
      Thread.sleep(n*1000);
    } catch(Exception e) {}
  }
  
  
}
