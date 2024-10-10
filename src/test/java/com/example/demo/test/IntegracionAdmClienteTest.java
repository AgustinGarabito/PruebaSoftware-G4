package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.modelo.AdmCliente;
import com.example.demo.modelo.Cliente;


public class IntegracionAdmClienteTest {
	private AdmCliente admCliente;

    @BeforeEach
    public void setUp() {
        admCliente = new AdmCliente();
    }

    @Test
    public void testAgregarCliente() throws Exception {
        admCliente.agregarCliente("Juan Perez", 123456789L, "Calle Falsa 123");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        assertNotNull(cliente, "El cliente debería haberse agregado correctamente.");
        assertEquals("Juan Perez", cliente.getCliente(), "El nombre del cliente debería ser Juan Perez.");
        assertEquals(123456789L, cliente.getDni(), "El DNI debería ser 123456789.");
        assertEquals("Calle Falsa 123", cliente.getDireccion(), "La dirección debería ser Calle Falsa 123.");
    }

    @Test
    public void testModificarCliente() throws Exception {
        admCliente.agregarCliente("Ana Gomez", 987654321L, "Av. Siempre Viva 742");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        admCliente.modificarCliente(cliente.getIdCliente(), "Ana Maria Gomez", 987654321L, "Av. Libertad 100");

        Cliente clienteModificado = admCliente.traerCliente(cliente.getIdCliente());
        assertEquals("Ana Maria Gomez", clienteModificado.getCliente(), "El nombre debería haberse modificado correctamente.");
        assertEquals("Av. Libertad 100", clienteModificado.getDireccion(), "La dirección debería haberse modificado correctamente.");
    }

    @Test
    public void testEliminarCliente() throws Exception {
        admCliente.agregarCliente("Carlos Diaz", 111111111L, "Calle Central 456");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente 

        boolean eliminado = admCliente.eliminarCLiente(cliente.getIdCliente());
        assertTrue(eliminado, "El cliente debería haberse eliminado correctamente.");
    }
    
    @Test
    public void testAgregarClienteDuplicado() throws Exception {
        // Agregar el primer cliente con un DNI específico
        admCliente.agregarCliente("Pedro Lopez", 555555555L, "Calle Independencia 123");

        // Intentar agregar un segundo cliente con el mismo DNI
        Exception exception = assertThrows(Exception.class, () -> {
            admCliente.agregarCliente("Maria Fernandez", 555555555L, "Calle Libertad 456");
        });

        String expectedMessage = "El cliente con este DNI ya existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "No debería permitir agregar un cliente con DNI duplicado.");
    }

}
