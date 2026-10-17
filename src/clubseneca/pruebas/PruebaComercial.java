package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;import java.util.*;
/** Casos extra RF-12 a RF-16 y RF-30: descuentos, IVA, impuesto y bebida caliente. */
public class PruebaComercial {
 public static void main(String[] args){
 Inventario inv=new Inventario();UbicacionInventario t=new UbicacionInventario("Tienda"),c=new UbicacionInventario("Cafeteria");
 ArticuloTienda raqueta=new ArticuloTienda("Raqueta",100000,"Deporte");Bebida cafe=new Bebida("Cafe",5000,true);Snack galleta=new Snack("Galleta",4000,List.of("Gluten"));
 inv.ajustar(raqueta,t,2);inv.ajustar(cafe,c,2);inv.ajustar(galleta,c,2);VentasService vs=new VentasService(inv,t,c);
 Socio socio=ApoyoPruebas.socio();VentaTienda v=vs.comprarTienda(socio,List.of(new LineaVenta(raqueta,1)),"Tarjeta",0.08,"EMPLEADO8",0);
 ApoyoPruebas.casi(92000,v.getSubtotal()-v.getDescuento());ApoyoPruebas.casi(17480,v.getIva());ApoyoPruebas.casi(109480,v.getTotal());
 ApoyoPruebas.falla(()->vs.comprarCafeteria(socio,List.of(new LineaVenta(cafe,1)),"Tarjeta",.1,true,0),"Bebida caliente prohibida");
 ApoyoPruebas.verificar(inv.cantidad(cafe,c)==2,"No debio vender bebida prohibida");
 ApoyoPruebas.verificar(vs.informarAlergenos(List.of(new LineaVenta(galleta,1))).contains("Gluten"),"Debe avisar alergenos");
 VentaCafeteria vc=vs.comprarCafeteria(socio,List.of(new LineaVenta(cafe,1)),"Efectivo",.10,false,0);
 ApoyoPruebas.casi(5900,vc.getTotal());ApoyoPruebas.verificar(socio.getPuntosFidelidad()>0,"Se deben ganar puntos");
 ApoyoPruebas.falla(()->vs.comprarTienda(socio,List.of(new LineaVenta(raqueta,1)),"Tarjeta",.15,"15",1),"No combinar promociones");
 System.out.println("PRUEBA COMERCIAL OK: IVA, descuento, alergenos, bebida caliente, propina");
 }
}
