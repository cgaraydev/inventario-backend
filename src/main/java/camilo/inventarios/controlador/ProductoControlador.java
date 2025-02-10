package camilo.inventarios.controlador;

import camilo.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import camilo.inventarios.modelo.Producto;
import camilo.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    //http://localhost:8080/inventario-app/productos
    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Obteniendo productos: ");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto) {
        logger.info("Agregando producto: " + producto.toString());
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{idProducto}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int idProducto) {
        Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
        if (producto != null) return ResponseEntity.ok(producto);
        else throw new RecursoNoEncontradoExcepcion("Producto no encontrado");
    }

    @PutMapping("/productos/{idProducto}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int idProducto,
            @RequestBody Producto productoRecibido) {
        Producto producto = this.productoServicio.buscarProductoPorId(idProducto);
        if (producto == null) throw new RecursoNoEncontradoExcepcion("No se encuentra el id" + idProducto);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{idProducto}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int idProducto) {
        Producto producto = productoServicio.buscarProductoPorId(idProducto);
        if (producto == null) throw new RecursoNoEncontradoExcepcion("No se encuentra el id" + idProducto);
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
