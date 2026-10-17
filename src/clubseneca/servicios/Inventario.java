package clubseneca.servicios;

import java.util.*;import clubseneca.modelo.*;
public class Inventario {
 private final List<ExistenciaInventario> existencias=new ArrayList<>();
 public synchronized List<ExistenciaInventario> consultar(){return List.copyOf(existencias);}
 public synchronized int cantidad(Producto p,UbicacionInventario u){return buscar(p,u).map(ExistenciaInventario::getCantidad).orElse(0);}
 private Optional<ExistenciaInventario> buscar(Producto p,UbicacionInventario u){return existencias.stream().filter(e->e.getProducto()==p&&e.getUbicacion().equals(u)).findFirst();}
 public synchronized void ajustar(Producto p,UbicacionInventario u,int delta){if(p==null||u==null)throw new IllegalArgumentException();var encontrado=buscar(p,u);if(encontrado.isPresent())encontrado.get().ajustar(delta);else {if(delta<0)throw new IllegalArgumentException("Sin existencias");existencias.add(new ExistenciaInventario(p,u,delta));}}
 public synchronized void mover(Producto p,UbicacionInventario origen,UbicacionInventario destino,int cantidad){if(cantidad<=0||origen.equals(destino))throw new IllegalArgumentException();if(cantidad(p,origen)<cantidad)throw new IllegalArgumentException("Existencias insuficientes");ajustar(p,origen,-cantidad);ajustar(p,destino,cantidad);}
 public synchronized void descontar(Map<Producto,Integer> productos,UbicacionInventario ubicacion){productos.forEach((p,n)->{if(n<=0||cantidad(p,ubicacion)<n)throw new IllegalArgumentException("Sin existencias: "+p.getNombre());});productos.forEach((p,n)->ajustar(p,ubicacion,-n));}
}
