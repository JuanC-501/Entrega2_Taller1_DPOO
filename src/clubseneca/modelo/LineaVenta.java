package clubseneca.modelo;

public class LineaVenta {
 private final Producto producto; private final int cantidad;private final double precioUnitario;
 public LineaVenta(Producto p,int cantidad){if(p==null||cantidad<=0)throw new IllegalArgumentException();this.producto=p;this.cantidad=cantidad;precioUnitario=p.getPrecio();}
 public Producto getProducto(){return producto;}public int getCantidad(){return cantidad;}public double getPrecioUnitario(){return precioUnitario;}
 public double getSubtotal(){return cantidad*precioUnitario;}
}
