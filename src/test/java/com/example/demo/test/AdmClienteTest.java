package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.modelo.AdmCliente;
import com.example.demo.modelo.Cliente;


public class AdmClienteTest {

    private AdmCliente admCliente;

    @BeforeEach
    public void setUp() {
        admCliente = new AdmCliente();
    }

    @Test
    public void testAgregarCliente() throws Exception {
        boolean agregado = admCliente.agregarCliente("Juan Perez", 12345678L, "Calle Falsa 123");
        assertTrue(agregado);
        assertNotNull(admCliente.traerCliente("Juan Perez"));
    }

    @Test
    public void testAgregarClienteExistente() throws Exception {
        AdmCliente admCliente = new AdmCliente();  
        
        admCliente.agregarCliente("Juan Perez", 12345678L, "Calle Falsa 123");

        // Intentamos agregar de nuevo al cliente a ver si lanza excepcion 
        Exception exception = assertThrows(Exception.class, () -> {
            admCliente.agregarCliente("Juan Perez", 12345678L, "Calle Falsa 123");
        });
        
        // Verificamos que el mensaje de la excepción es el correcto
        assertEquals("El cliente Juan Perez ya existe", exception.getMessage());
    }

    @Test
    public void testModificarCliente() throws Exception {
        admCliente.agregarCliente("Juan Perez", 12345678L, "Calle Falsa 123");
        Cliente cliente = admCliente.traerCliente("Juan Perez");
        assertNotNull(cliente);

        boolean modificado = admCliente.modificarCliente(cliente.getIdCliente(), "Juan Gomez", 87654321L, "Otra Calle 456");
        assertTrue(modificado);

        Cliente clienteModificado = admCliente.traerCliente(cliente.getIdCliente());
        assertEquals("Juan Gomez", clienteModificado.getCliente());
        assertEquals(87654321L, clienteModificado.getDni());
        assertEquals("Otra Calle 456", clienteModificado.getDireccion());
    }

    @Test
    public void testEliminarCliente() throws Exception {
        admCliente.agregarCliente("Juan Perez", 12345678L, "Calle Falsa 123");
        Cliente cliente = admCliente.traerCliente("Juan Perez");
        assertNotNull(cliente);

        boolean eliminado = admCliente.eliminarCLiente(cliente.getIdCliente());
        assertTrue(eliminado);
        assertNull(admCliente.traerCliente(cliente.getIdCliente()));
    }

    @Test
    public void testEliminarClienteInexistente() throws Exception {
        AdmCliente admCliente = new AdmCliente();  
        
        Exception exception = assertThrows(Exception.class, () -> {
            admCliente.eliminarCLiente(100);  // Intentamos eliminar un cliente inexistente
        });
        
        assertEquals("Error: El cliente no existe", exception.getMessage());
    }
}
