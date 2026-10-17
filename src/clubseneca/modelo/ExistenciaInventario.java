package clubseneca.modelo;

public class ExistenciaInventario {
 private final Producto producto;private final UbicacionInventario ubicacion;private int cantidad;
 public ExistenciaInventario(Producto p,UbicacionInventario u,int cantidad){if(p==null||u==null||cantidad<0)throw new IllegalArgumentException();producto=p;ubicacion=u;this.cantidad=cantidad;}
 public Producto getProducto(){return producto;}public UbicacionInventario getUbicacion(){return ubicacion;}
 public int getCantidad(){return cantidad;}
 public void ajustar(int delta){if((long)cantidad+delta<0||(long)cantidad+delta>Integer.MAX_VALUE)throw new IllegalArgumentException("Existencias insuficientes o exceso");cantidad+=delta;}
}
