# Club Séneca — Persona 3 (Java Project / Eclipse)

Implementación de **Comercial, administración y acceso**, según UML `Proyecto1_ClubSeneca_dominio_correccion2.pdf` y RF/RN proporcionados.

## Abrir en Eclipse

1. `File > Import > General > Existing Projects into Workspace`.
2. Seleccionar la carpeta `ClubSeneca_JavaProject` (esta carpeta si se descarga ZIP; en GitHub es la raíz del repositorio).
3. Verificar que Eclipse use **Java 17 o superior**.
4. Ejecutar `src/clubseneca/pruebas/EjecutarTodo.java` (`Run As > Java Application`).

## Ejecutar desde terminal

```bash
mkdir -p bin
javac -encoding UTF-8 -d bin $(find src -name '*.java')
java -cp bin clubseneca.pruebas.EjecutarTodo
```

## Entregables

- `modelo`: Usuario, Administrador, Socio, Empleado, Entrenador, Fisioterapeuta; Producto, ArticuloTienda, Bebida, Snack; UbicacionInventario, ExistenciaInventario, SugerenciaProducto; Turno, SolicitudCambioTurno, Venta, VentaTienda, VentaCafeteria, LineaVenta, Mensualidad.
- `servicios`: Autenticacion, Inventario, PersonalTurnos, VentasService y Administracion.
- `pruebas`: Prueba7, Prueba8, Prueba9, Prueba10 y PruebaComercial adicional.

## Decisiones y límites de integración

- Al partir de un **repositorio vacío**, se incluyen las clases mínimas `Socio`, `Empleado`, `Entrenador` y `Fisioterapeuta` para compilar. Estas son de soporte, y deben fusionarse con las clases que implementen otros integrantes.
- `RegistroAdministrativo` es un **adaptador temporal de lectura**, no una clase del UML. Registra fecha, categoría, referencia y detalle de reservas, partidos, sesiones y rankings, hasta conectar con los objetos de dominio de los demás paquetes. Por tanto, la prueba 9 valida los filtros e informes, **no** los flujos de registro deportivos completos ni la disponibilidad real de canchas.
- La política de **mínimo 2 entrenadores y 1 fisioterapeuta** se verifica de manera continua por segmentos dentro de los días y del horario de atención que se suministra a `PersonalTurnos`. El caso de prueba configura lunes a domingo, 08:00–18:00; el horario real deberá reemplazarse cuando se confirme.
- Los importes de descuentos se expresan en proporciones (0.15 = 15%, 0.08 = 8%). Se evita combinar descuentos con puntos. Los cupones como texto se **registran**, pero todavía no se validan contra un catálogo ni se controla su cesión a un tercero: esa política necesita integración.
- IVA del 19% y consumo del 8%; puntos ganados = parte entera de 2% del valor total y equivalencia provisional **1 punto = 1 unidad monetaria** en redención, ya que no se suministró tasa de conversión.
- Inventario usa referencias de objetos `Producto` y ubicaciones por nombre, sin persistencia; los datos viven en memoria durante la ejecución.
- `Mensualidad` se registra y reporta como rubro independiente (RN-42).
- El proyecto es de lógica de negocio con programas `main` y **no incluye interfaz gráfica ni persistencia**. No se ha implementado el resto del dominio deportivo (RF 01–11, 17–28, 31–32) de los demás integrantes.
- Las restricciones de Administrador están implementadas en `Autenticacion` y las banderas de rol; las interfaces futuras deberán invocar esas validaciones en sus operaciones concretas.
