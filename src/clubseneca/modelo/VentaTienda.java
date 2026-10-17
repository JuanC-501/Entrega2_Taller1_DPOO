package clubseneca.modelo;

import java.time.LocalDateTime;import java.util.List;
public class VentaTienda extends Venta {
 private final double iva,descuento;private final String codigoDescuentoUsado;
 public VentaTienda(LocalDateTime fecha,Usuario comprador,String metodo,List<LineaVenta> lineas,double subtotal,double descuento,double iva,double total,int puntos,String codigo){super(fecha,comprador,metodo,lineas,subtotal,total,puntos);this.iva=iva;this.descuento=descuento;codigoDescuentoUsado=codigo;}
 public double getIva(){return iva;}public double getDescuento(){return descuento;}public String getCodigoDescuentoUsado(){return codigoDescuentoUsado;}
}
