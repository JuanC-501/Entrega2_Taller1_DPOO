package clubseneca.servicios;

import java.time.*;import java.util.*;import clubseneca.modelo.*;
public class VentasService {
 private final Inventario inventario;private final UbicacionInventario tienda,cafeteria;private final List<Venta> ventas=new ArrayList<>();
 public VentasService(Inventario inv,UbicacionInventario tienda,UbicacionInventario cafeteria){this.inventario=inv;this.tienda=tienda;this.cafeteria=cafeteria;}
 public List<Venta> consultarVentas(){return List.copyOf(ventas);}
 private static double redondear(double n){return Math.round(n*100.0)/100.0;}
 private static Map<Producto,Integer> agrupados(List<LineaVenta> lineas){Map<Producto,Integer> p=new LinkedHashMap<>();for(LineaVenta l:lineas)p.merge(l.getProducto(),l.getCantidad(),Integer::sum);return p;}
 private static double subtotal(List<LineaVenta> ls){return ls.stream().mapToDouble(LineaVenta::getSubtotal).sum();}
 public VentaTienda comprarTienda(Usuario comprador,List<LineaVenta> lineas,String metodo,double porcentajeDescuento,String codigo,int puntos){
  if(comprador==null||lineas==null||lineas.isEmpty()||porcentajeDescuento<0||porcentajeDescuento>1||puntos<0)throw new IllegalArgumentException();
  if(lineas.stream().anyMatch(l->!(l.getProducto() instanceof ArticuloTienda)))throw new IllegalArgumentException("Solo articulos de tienda");
  if(porcentajeDescuento>0&&puntos>0)throw new IllegalArgumentException("No combinar promociones");
  if(puntos>0&&(!(comprador instanceof Socio s)||s.getPuntosFidelidad()<puntos))throw new IllegalArgumentException("Puntos insuficientes");
  Map<Producto,Integer> items=agrupados(lineas);double base=redondear(subtotal(lineas));double descuento=redondear(base*porcentajeDescuento+puntos);if(descuento>base)throw new IllegalArgumentException("Descuento excesivo");
  double iva=redondear((base-descuento)*.19);double total=redondear(base-descuento+iva);
  inventario.descontar(items,tienda);
  if(puntos>0)((Socio)comprador).redimirPuntos(puntos);
  VentaTienda v=new VentaTienda(LocalDateTime.now(),comprador,metodo,lineas,base,descuento,iva,total,puntos,codigo);ventas.add(v);
  if(comprador instanceof Socio s)s.sumarPuntos(v.getPuntosGanados());return v;
 }
 public VentaCafeteria comprarCafeteria(Usuario comprador,List<LineaVenta> lineas,String metodo,double porcentajePropina,boolean enPadelOMesaTechada,int puntos){
  if(comprador==null||lineas==null||lineas.isEmpty()||porcentajePropina<0||porcentajePropina>1||puntos<0)throw new IllegalArgumentException();
  if(lineas.stream().anyMatch(l->!(l.getProducto() instanceof Bebida)&&!(l.getProducto() instanceof Snack)))throw new IllegalArgumentException("Producto no es de cafeteria");
  if(enPadelOMesaTechada&&lineas.stream().anyMatch(l->l.getProducto() instanceof Bebida b&&b.isCaliente()))throw new IllegalArgumentException("No se permiten bebidas calientes en esta instalacion techada");
  if(puntos>0&&(!(comprador instanceof Socio s)||s.getPuntosFidelidad()<puntos))throw new IllegalArgumentException("Puntos insuficientes");
  Map<Producto,Integer> items=agrupados(lineas);double base=redondear(subtotal(lineas));if(puntos>base)throw new IllegalArgumentException("Descuento excesivo");
  double impuesto=redondear((base-puntos)*.08);double propina=redondear((base-puntos)*porcentajePropina);double total=redondear(base-puntos+impuesto+propina);
  inventario.descontar(items,cafeteria);if(puntos>0)((Socio)comprador).redimirPuntos(puntos);
  VentaCafeteria v=new VentaCafeteria(LocalDateTime.now(),comprador,metodo,lineas,base,impuesto,propina,total,puntos);ventas.add(v);
  if(comprador instanceof Socio s)s.sumarPuntos(v.getPuntosGanados());return v;
 }
 public List<String> informarAlergenos(List<LineaVenta> lineas){return lineas.stream().filter(l->l.getProducto() instanceof Snack).flatMap(l->((Snack)l.getProducto()).getAlergenos().stream()).distinct().toList();}
}
