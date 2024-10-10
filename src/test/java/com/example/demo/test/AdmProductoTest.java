package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.modelo.AdmProducto;
import com.example.demo.modelo.Producto;

public class AdmProductoTest {
	private Producto producto1;
	private Producto producto2;
	private List<Producto> gondola;
	private AdmProducto admin;
    
    @BeforeEach
    public void setUp() {
    	admin = new AdmProducto();
        producto1 = mock(Producto.class);
        producto2 = mock(Producto.class);
        
        when(producto1.getIdProducto()).thenReturn(1);
        when(producto1.getProducto()).thenReturn("Producto 1");
        when(producto1.getPrecio()).thenReturn(20.0f);

        when(producto2.getIdProducto()).thenReturn(2);
        when(producto2.getProducto()).thenReturn("Producto 2");
        when(producto2.getPrecio()).thenReturn(30.0f);
        
    	gondola = new ArrayList<Producto>();
    }

    @Test
    public void testTraerProductoExistente() {
        gondola.add(producto1);
        gondola.add(producto2);

        admin.setGondola(gondola);

        Producto resultado = admin.traerProducto(1);

        assertEquals(producto1, resultado);
    }
    
    @Test
    public void testTraerProductoInexistente() {
        gondola.add(producto1);
        gondola.add(producto2);

        admin.setGondola(gondola);

        Producto resultado = admin.traerProducto(3);

        assertNull(resultado);
    }
    
    @Test
    public void testAgregarProductoConGondolaVacia() throws Exception {
        admin.setGondola(gondola);
        
        boolean resultado = admin.agregarProducto("Producto 1", 200);

        assertTrue(resultado);
        assertEquals(1, gondola.size());
        assertEquals("Producto 1", gondola.get(0).getProducto());
        assertEquals(200, gondola.get(0).getPrecio(), 0.01f);
    }
    
    @Test
    public void testAgregarProductoNuevoConGondolaNoVacia() throws Exception {
    	gondola.add(producto1);
        gondola.add(producto2);
    	admin.setGondola(gondola);
        
        boolean resultado = admin.agregarProducto("Producto 3", 100);

        assertTrue(resultado);
        assertEquals(3, gondola.size());
        assertEquals("Producto 3", gondola.get(2).getProducto());
        assertEquals(100, gondola.get(2).getPrecio(), 0.01f);
    }
    
    @Test
    public void testAgregarProductoExistente() throws Exception {
    	gondola.add(producto1);
        gondola.add(producto2);
    	admin.setGondola(gondola);
        
        
        Exception exception = assertThrows(Exception.class, () -> {
        	admin.agregarProducto("Producto 1", 20);
        });

        assertEquals("El producto Producto 1 ya esta registrado", exception.getMessage());
    }
    
    @Test
    public void testEliminarProductoExistente() throws Exception {
    	gondola.add(producto1);
        gondola.add(producto2);
    	admin.setGondola(gondola);
        
        boolean resultado = admin.eliminarProducto(1);
        assertTrue(resultado);
        assertEquals(1, gondola.size());
    }
    
    @Test
    public void testEliminarProductoInexistente() throws Exception {
    	gondola.add(producto1);
        gondola.add(producto2);
    	admin.setGondola(gondola);
    	Exception exception = assertThrows(Exception.class, () -> {
    		admin.eliminarProducto(3);
    	});
    	assertEquals("Error: El producto no existe", exception.getMessage());
    }
    
    @Test
    public void testModificarProductoInexistente() throws Exception {
    	gondola.add(producto1);
        gondola.add(producto2);
    	admin.setGondola(gondola);
    	Exception exception = assertThrows(Exception.class, () -> {
    		admin.modificarProducto(3, "Producto 3", 30);
    	});
    	assertEquals("Error: El producto no existe", exception.getMessage());
    }
}
