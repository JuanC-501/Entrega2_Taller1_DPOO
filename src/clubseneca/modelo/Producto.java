package clubseneca.modelo;

public abstract class Producto {
 private final String nombre; private final double precio;
 protected Producto(String nombre,double precio){if(nombre==null||nombre.isBlank()||!Double.isFinite(precio)||precio<0)throw new IllegalArgumentException("Producto invalido");this.nombre=nombre;this.precio=precio;}
 public String getNombre(){return nombre;}public double getPrecio(){return precio;}
}
