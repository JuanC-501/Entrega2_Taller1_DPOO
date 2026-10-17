package clubseneca.modelo;

import java.time.LocalDateTime;import java.util.List;
public class VentaCafeteria extends Venta {
 private final double impuestoConsumo,propina;
 public VentaCafeteria(LocalDateTime fecha,Usuario comprador,String metodo,List<LineaVenta> lineas,double subtotal,double impuesto,double propina,double total,int puntos){super(fecha,comprador,metodo,lineas,subtotal,total,puntos);impuestoConsumo=impuesto;this.propina=propina;}
 public double getImpuestoConsumo(){return impuestoConsumo;}public double getPropina(){return propina;}
}
