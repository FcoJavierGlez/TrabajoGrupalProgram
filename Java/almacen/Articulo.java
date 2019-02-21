package almacen;

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
 * 
 * 
 * @author Francisco Javier González Sabariego
 *
 */
public class Articulo {
  
  private int codigo;
  
  private String descripcion;
  
  private double precioCompra;
  
  private double precioVenta;
  
  private int stock;
  
  /**
   * Constructor
   */
  public Articulo(int cod, String desc, double precComp, double precVen, int stock) {
    
    this.codigo = cod;
    
    this.descripcion = desc;
    
    this.precioCompra = precComp;
    
    this.precioVenta = precVen;
    
    this.stock = stock;
    
    
  }
  
  
  /**
   * Este método modifica el stock de un artículo determinado.
   * <ul>
   * <li>Añade artículos si el valor de entrada es positivo.</li>
   * <li>Reduce el stock almacenado si el valor de entrada es negativo y si el total a restar 
   * no supera al total almacenado.</li>
   * </ul>
   * 
   * En caso de querer reducir un total de artículos superior al almacenado muestra un mensaje 
   * de información al usuario y no hace ningún cambio.
   * 
   * @param numArticulos  La cantidad a modificar el stock
   */
  private void setStock(int numArticulos) {
    
    if (numArticulos<0) {
      
      if (Math.abs(numArticulos)>this.stock) {
        System.out.print("\n\nNo puede retirar más artículos de los que hay disponibles en stock.");
        System.out.print("\n\nUsted desea retirar " + Math.abs(numArticulos) + " artículos y hay en stock " + this.stock + ".");
        System.out.print("\n\nPor favor introduzca una cantidad válida.");
      } else {
        this.stock += numArticulos;
      }
      
    } else {
      
      this.stock += numArticulos;
      
    }
    
  }
  
  
  /**
   * 
   * @return
   */
  public int getStock() {
    return this.stock;
  }
  
  
  /**
   * 
   * @param numArticulos
   * @return
   */
  protected void entradaMercancia() {
    int numArticulos;
    Scanner s = new Scanner(System.in);
    
    System.out.println("\n\n¿Cuántos artículos desea registrar?");
    numArticulos = s.nextInt();
    
    while(numArticulos<0) {
      System.out.print("\n\nNo puede introducir una cantidad negativa de artículos, introduzca un valor positivo.");
      System.out.println("\n\n¿Cuántos artículos desea registrar?");
      numArticulos = s.nextInt();
    }
    
    this.setStock(numArticulos);
    
  }
  
  
  /**
   * 
   * @param numArticulos
   * @return
   */
  protected void salidaMercancia() {
    int numArticulos;
    Scanner s = new Scanner(System.in);
    
    System.out.print("\n\n¿Cuántos artículos desea retirar?");
    numArticulos = s.nextInt();
    
    while(numArticulos>0) {
      System.out.print("\n\nNo puede retirar una cantidad positiva de artículos, introduzca un valor negativo.");
      System.out.println("\n\n¿Cuántos artículos desea registrar?");
      numArticulos = s.nextInt();
    }
    
    this.setStock(numArticulos);
    
  }
  
  
  public String toString() {
    
    return "Código de art: " + this.codigo + "\nDescripción: " +
            this.descripcion + "\nPrecio de compra: " + this.precioCompra + 
            "\nPrecio de venta: " + this.precioVenta + "\nArtículos en almacén: " + this.stock;
    
  }
  

}
