package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;import java.util.*;
/** RF-35,36; RN-38. */
public class Prueba7 {
 public static void main(String[] args){
 Inventario inv=new Inventario();UbicacionInventario bodega=new UbicacionInventario("Bodega"),tienda=new UbicacionInventario("Tienda");
 ArticuloTienda pelota=new ArticuloTienda("Pelota",30000,"Deporte");Snack barra=new Snack("Barra",5000,List.of("Nueces"));
 Administracion a=new Administracion(ApoyoPruebas.admin(),inv,new ArrayList<>());
 a.reabastecer(pelota,bodega,12);a.reabastecer(barra,tienda,4);a.mover(pelota,bodega,tienda,5);
 ApoyoPruebas.verificar(inv.cantidad(pelota,bodega)==7&&inv.cantidad(pelota,tienda)==5,"Movimiento o reabastecimiento incorrecto");
 SugerenciaProducto s1=new SugerenciaProducto(ApoyoPruebas.entrenador(10),"Pelota","Mover a tienda");
 SugerenciaProducto s2=new SugerenciaProducto(ApoyoPruebas.fisio(11),"Snack","No mover");
 a.registrarSugerencia(s1);a.registrarSugerencia(s2);a.evaluarSugerencia(s1,true);a.evaluarSugerencia(s2,false);
 ApoyoPruebas.verificar(s1.getEstado()==EstadoSolicitud.APROBADA&&s2.getEstado()==EstadoSolicitud.RECHAZADA,"Evaluacion incorrecta");
 ApoyoPruebas.verificar(a.consultarInventario().size()==3,"Inventario incompleto");
 ApoyoPruebas.falla(()->a.mover(pelota,bodega,tienda,500),"No debe permitir stock negativo");
 System.out.println("PRUEBA 7 OK: inventario, movimientos, sugerencias aprobadas/rechazadas");
 }
}
