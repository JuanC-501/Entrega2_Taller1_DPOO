package clubseneca.modelo;

import java.time.LocalDateTime;
public class SugerenciaProducto {
 private final String nombre,descripcion;private final Empleado empleado; private EstadoSolicitud estado=EstadoSolicitud.PENDIENTE;
 private final LocalDateTime fechaSolicitud=LocalDateTime.now();
 public SugerenciaProducto(Empleado empleado,String nombre,String descripcion){if(empleado==null||nombre==null||nombre.isBlank())throw new IllegalArgumentException();this.empleado=empleado;this.nombre=nombre;this.descripcion=descripcion;}
 public String getNombre(){return nombre;}public String getDescripcion(){return descripcion;}public Empleado getEmpleado(){return empleado;}
 public EstadoSolicitud getEstado(){return estado;}public LocalDateTime getFechaSolicitud(){return fechaSolicitud;}
 public void evaluar(boolean aprobar){if(estado!=EstadoSolicitud.PENDIENTE)throw new IllegalStateException("Sugerencia ya evaluada");estado=aprobar?EstadoSolicitud.APROBADA:EstadoSolicitud.RECHAZADA;}
}
