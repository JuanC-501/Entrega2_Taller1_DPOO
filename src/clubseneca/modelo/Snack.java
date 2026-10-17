package clubseneca.modelo;

import java.util.List;
public class Snack extends Producto {
 private final List<String> alergenos;
 public Snack(String nombre,double precio,List<String> alergenos){super(nombre,precio);this.alergenos=List.copyOf(alergenos);}
 public List<String> getAlergenos(){return alergenos;}
}
