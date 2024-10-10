package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.example.demo.modelo.AdmCarrito;
import com.example.demo.modelo.Carrito;
import com.example.demo.modelo.Cliente;

public class AdmCarritoTest {
	 private AdmCarrito admCarrito;
	    private Cliente cliente;
	    
	    @BeforeEach
	    public void setUp() {
	        admCarrito = new AdmCarrito();
	        cliente = new Cliente(1, "John Doe", 12345678L, "123 Main St");
	    }

	    @Test
	    public void testAgregarCarrito() throws Exception {
	        LocalDate fecha = LocalDate.now();
	        LocalTime hora = LocalTime.now();
	        boolean result = admCarrito.agregarCarrito(fecha, hora, cliente);
	        
	        assertTrue(result, "El carrito debería agregarse exitosamente.");
	        assertEquals(1, admCarrito.getLstCarrito().size(), "Debería haber 1 carrito en la lista.");
	    }

	    @Test
	    public void testAgregarCarritoExistente() throws Exception {
	        LocalDate fecha = LocalDate.now();
	        LocalTime hora = LocalTime.now();
	        
	        admCarrito.agregarCarrito(fecha, hora, cliente);
	 
	        Exception exception = assertThrows(Exception.class, () -> {
	            admCarrito.agregarCarrito(fecha, hora, cliente);
	        });
	        
	        assertEquals("El carrito ya existe", exception.getMessage(), "Debería lanzar excepción de carrito existente.");
	    }

	    @Test
	    public void testTraerCarritoPorCliente() throws Exception {
	        LocalDate fecha = LocalDate.now();
	        LocalTime hora = LocalTime.now();
	        
	        admCarrito.agregarCarrito(fecha, hora, cliente);
	        
	        Carrito carrito = admCarrito.traerCarrito(cliente);
	        
	        assertNotNull(carrito, "Debería encontrar el carrito del cliente.");
	        assertEquals(cliente, carrito.getCliente(), "El cliente debería coincidir con el carrito.");
	    }

	    @Test
	    public void testTraerCarritoPorId() throws Exception {
	        LocalDate fecha = LocalDate.now();
	        LocalTime hora = LocalTime.now();
	        
	        admCarrito.agregarCarrito(fecha, hora, cliente);
	        
	        Carrito carrito = admCarrito.traerCarrito(1);
	        
	        assertNotNull(carrito, "Debería encontrar el carrito por ID.");
	        assertEquals(1, carrito.getIdCarrito(), "El ID del carrito debería ser 1.");
	    }

	    @Test
	    public void testEliminarCarritoExistente() throws Exception {
	        LocalDate fecha = LocalDate.now();
	        LocalTime hora = LocalTime.now();
	        
	        admCarrito.agregarCarrito(fecha, hora, cliente);
	        
	        boolean result = admCarrito.eliminarCarrito(1);
	        
	        assertTrue(result, "El carrito debería eliminarse correctamente.");
	        assertEquals(0, admCarrito.getLstCarrito().size(), "No debería haber carritos en la lista.");
	    }

	    @Test
	    public void testEliminarCarritoInexistente() throws Exception {
	        Exception exception = assertThrows(Exception.class, () -> {
	            admCarrito.eliminarCarrito(999);
	        });

	        assertEquals("Error: El cliente no existe", exception.getMessage());
	    }
}