package clubseneca.modelo;

public class UbicacionInventario {
 private final String nombre;
 public UbicacionInventario(String nombre){if(nombre==null||nombre.isBlank())throw new IllegalArgumentException();this.nombre=nombre;}
 public String getNombre(){return nombre;}
 @Override public boolean equals(Object o){return o instanceof UbicacionInventario u && nombre.equalsIgnoreCase(u.nombre);}
 @Override public int hashCode(){return nombre.toLowerCase(java.util.Locale.ROOT).hashCode();}
}
