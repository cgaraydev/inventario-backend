package camilo.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;
    String descripcion;
    Double precio;
    Integer existencia;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                '}';
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
}
