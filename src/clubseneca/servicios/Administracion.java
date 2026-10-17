package clubseneca.servicios;

import java.time.*;import java.util.*;import java.util.stream.*;import clubseneca.modelo.*;
public class Administracion {
 private final Administrador admin;private final Inventario inventario;private final List<SugerenciaProducto> sugerencias=new ArrayList<>();
 private final List<Mensualidad> mensualidades=new ArrayList<>();private final List<RegistroAdministrativo> reservas=new ArrayList<>(),partidos=new ArrayList<>(),entrenamientos=new ArrayList<>(),rankings=new ArrayList<>();
 private final List<Venta> ventas;
 public Administracion(Administrador admin,Inventario inv,List<Venta> ventas){this.admin=admin;inventario=inv;this.ventas=ventas;}
 public List<ExistenciaInventario> consultarInventario(){return inventario.consultar();}
 public void reabastecer(Producto p,UbicacionInventario u,int n){if(n<=0)throw new IllegalArgumentException();inventario.ajustar(p,u,n);}
 public void mover(Producto p,UbicacionInventario origen,UbicacionInventario destino,int n){inventario.mover(p,origen,destino,n);}
 public void registrarSugerencia(SugerenciaProducto s){sugerencias.add(s);}
 public List<SugerenciaProducto> consultarSugerencias(){return List.copyOf(sugerencias);}
 public void evaluarSugerencia(SugerenciaProducto s,boolean aprobar){if(!sugerencias.contains(s))throw new IllegalArgumentException();s.evaluar(aprobar);}
 public void registrarMensualidad(Mensualidad m){mensualidades.add(m);}
 public void registrarReserva(RegistroAdministrativo r){reservas.add(r);}public void registrarPartido(RegistroAdministrativo r){partidos.add(r);}
 public void registrarEntrenamiento(RegistroAdministrativo r){entrenamientos.add(r);}public void registrarRanking(RegistroAdministrativo r){rankings.add(r);}
 private List<RegistroAdministrativo> filtrar(List<RegistroAdministrativo> fuente,LocalDate desde,LocalDate hasta,String categoria){return fuente.stream().filter(r->!r.fecha().isBefore(desde)&&!r.fecha().isAfter(hasta)&&(categoria==null||categoria.equalsIgnoreCase(r.categoria()))).toList();}
 public List<RegistroAdministrativo> consultarReservas(LocalDate desde,LocalDate hasta){return filtrar(reservas,desde,hasta,null);}
 public List<RegistroAdministrativo> consultarPartidos(LocalDate desde,LocalDate hasta){return filtrar(partidos,desde,hasta,null);}
 public List<RegistroAdministrativo> consultarEntrenamientos(LocalDate desde,LocalDate hasta){return filtrar(entrenamientos,desde,hasta,null);}
 public List<RegistroAdministrativo> reporteAsistencia(LocalDate desde,LocalDate hasta){return consultarEntrenamientos(desde,hasta);}
 public List<RegistroAdministrativo> reporteResultados(LocalDate desde,LocalDate hasta){return consultarPartidos(desde,hasta);}
 public List<RegistroAdministrativo> reporteRankings(LocalDate desde,LocalDate hasta,String deporte){return filtrar(rankings,desde,hasta,deporte);}
 public List<RegistroAdministrativo> reporteRankingsJugador(LocalDate desde,LocalDate hasta,String deporte,String jugador){return reporteRankings(desde,hasta,deporte).stream().filter(r->r.referencia().equalsIgnoreCase(jugador)).toList();}
 public double reporteVentas(LocalDate desde,LocalDate hasta,String rubro){
  if(desde.isAfter(hasta))throw new IllegalArgumentException();
  String tipo=rubro.toUpperCase(Locale.ROOT); if(!List.of("TIENDA","CAFETERIA","MENSUALIDADES").contains(tipo))throw new IllegalArgumentException("Rubro desconocido");
  if(tipo.equals("MENSUALIDADES"))return mensualidades.stream().filter(m->!m.getFechaPago().isBefore(desde)&&!m.getFechaPago().isAfter(hasta)).mapToDouble(Mensualidad::getValor).sum();
  return ventas.stream().filter(v->!v.getFecha().toLocalDate().isBefore(desde)&&!v.getFecha().toLocalDate().isAfter(hasta))
   .filter(v->tipo.equals("TIENDA")?v instanceof VentaTienda:v instanceof VentaCafeteria).mapToDouble(Venta::getTotal).sum();
 }
 public Administrador getAdministrador(){return admin;}
}
