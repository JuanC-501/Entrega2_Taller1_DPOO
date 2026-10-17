package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;import java.time.*;import java.util.*;
/** RF-33,34,39,40,41; RN-41,42. Entradas deportivas como adaptadores hasta integrar modulos de los companeros. */
public class Prueba9 {
 public static void main(String[] args){
 Inventario inv=new Inventario();UbicacionInventario tienda=new UbicacionInventario("Tienda"),caf=new UbicacionInventario("Cafeteria");
 ArticuloTienda camiseta=new ArticuloTienda("Camiseta",10000,"Ropa");Bebida agua=new Bebida("Agua",4000,false);
 inv.ajustar(camiseta,tienda,5);inv.ajustar(agua,caf,5);
 VentasService ventas=new VentasService(inv,tienda,caf);Socio s=ApoyoPruebas.socio();
 VentaTienda vt=ventas.comprarTienda(s,List.of(new LineaVenta(camiseta,1)),"Tarjeta",0,null,0);
 VentaCafeteria vc=ventas.comprarCafeteria(s,List.of(new LineaVenta(agua,1)),"Efectivo",0.10,false,0);
 Administracion a=new Administracion(ApoyoPruebas.admin(),inv,ventas.consultarVentas());
 LocalDate hoy=LocalDate.now(),antes=hoy.minusMonths(2);
 a.registrarMensualidad(new Mensualidad(s,100000,hoy,"2026-10"));a.registrarMensualidad(new Mensualidad(s,90000,antes,"2026-08"));
 a.registrarReserva(new RegistroAdministrativo(hoy,"TENIS","R1","Cancha 1"));a.registrarReserva(new RegistroAdministrativo(antes,"PADEL","R2","Cancha 2"));
 a.registrarPartido(new RegistroAdministrativo(hoy,"FUTBOL","P1","2-1"));a.registrarPartido(new RegistroAdministrativo(antes,"FUTBOL","P2","1-1"));
 a.registrarEntrenamiento(new RegistroAdministrativo(hoy,"TENIS","E1","Asistieron 4"));a.registrarEntrenamiento(new RegistroAdministrativo(antes,"TENIS","E2","Asistieron 3"));
 a.registrarRanking(new RegistroAdministrativo(hoy,"TENIS","Sofia","ELO 1100"));a.registrarRanking(new RegistroAdministrativo(antes,"PADEL","Sofia","ELO 900"));
 ApoyoPruebas.casi(vt.getTotal(),a.reporteVentas(hoy,hoy,"TIENDA"));ApoyoPruebas.casi(vc.getTotal(),a.reporteVentas(hoy,hoy,"CAFETERIA"));
 ApoyoPruebas.casi(100000,a.reporteVentas(hoy,hoy,"MENSUALIDADES"));
 ApoyoPruebas.verificar(a.consultarReservas(hoy,hoy).size()==1&&a.consultarPartidos(hoy,hoy).size()==1&&a.consultarEntrenamientos(hoy,hoy).size()==1,"Filtro de periodo incorrecto");
 ApoyoPruebas.verificar(a.reporteAsistencia(hoy,hoy).size()==1&&a.reporteResultados(hoy,hoy).size()==1,"Reporte deportivo incorrecto");
 ApoyoPruebas.verificar(a.reporteRankings(hoy,hoy,"TENIS").size()==1&&a.reporteRankings(hoy,hoy,"PADEL").isEmpty(),"Filtro rankings incorrecto");
 System.out.println("PRUEBA 9 OK: ventas por rubro, mensualidades, reservas, deportes, rankings por periodo");
 }
}
