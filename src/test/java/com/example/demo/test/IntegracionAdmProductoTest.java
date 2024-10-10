package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.modelo.AdmProducto;
import com.example.demo.modelo.Producto;

public class IntegracionAdmProductoTest {
	private AdmProducto admProducto;

    @BeforeEach
    public void setUp() {
        admProducto = new AdmProducto();
    }

    @Test
    public void testAgregarProducto() throws Exception {
        admProducto.agregarProducto("Manzanas", 2.5f);
        Producto producto = admProducto.traerProducto(1); // Traer producto por idProducto

        assertNotNull(producto, "El producto debería haberse agregado correctamente.");
        assertEquals("Manzanas", producto.getProducto(), "El nombre del producto debería ser Manzanas.");
        assertEquals(2.5f, producto.getPrecio(), 0.01, "El precio del producto debería ser 2.5.");
    }

    @Test
    public void testModificarProducto() throws Exception {
        admProducto.agregarProducto("Leche", 1.5f);
        Producto producto = admProducto.traerProducto(1); // Traer producto por idProducto

        admProducto.modificarProducto(producto.getIdProducto(), "Leche Entera", 1.75f);

        Producto productoModificado = admProducto.traerProducto(producto.getIdProducto());
        assertEquals("Leche Entera", productoModificado.getProducto(), "El nombre del producto debería haberse modificado.");
        assertEquals(1.75f, productoModificado.getPrecio(), 0.01, "El precio debería haberse modificado.");
    }

    @Test
    public void testEliminarProducto() throws Exception {
        admProducto.agregarProducto("Jugo", 3.0f);
        Producto producto = admProducto.traerProducto(1); // Traer producto por idProducto

        boolean eliminado = admProducto.eliminarProducto(producto.getIdProducto());
        assertTrue(eliminado, "El producto debería haberse eliminado correctamente.");
    }
}
