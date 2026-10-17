package clubseneca.modelo;

import java.time.*;import java.util.*;
public abstract class Venta {
 private final LocalDateTime fecha;private final Usuario comprador;private final String metodoPago;
 private final List<LineaVenta> lineas;private final double subtotal,total;private final int puntosGanados,puntosRedimidos;
 protected Venta(LocalDateTime fecha,Usuario comprador,String metodo,List<LineaVenta> lineas,double subtotal,double total,int redimidos){
 if(fecha==null||comprador==null||lineas==null||lineas.isEmpty()||subtotal<0||total<0||redimidos<0)throw new IllegalArgumentException();
 this.fecha=fecha;this.comprador=comprador;metodoPago=metodo;this.lineas=List.copyOf(lineas);this.subtotal=subtotal;this.total=total;puntosRedimidos=redimidos;puntosGanados=(int)Math.floor(total*.02);
 }
 public LocalDateTime getFecha(){return fecha;}public Usuario getComprador(){return comprador;}public String getMetodoPago(){return metodoPago;}
 public List<LineaVenta> getLineas(){return lineas;}public double getSubtotal(){return subtotal;}public double getTotal(){return total;}
 public int getPuntosGanados(){return puntosGanados;}public int getPuntosRedimidos(){return puntosRedimidos;}
}
