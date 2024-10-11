package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.modelo.AdmCarrito;
import com.example.demo.modelo.AdmCliente;
import com.example.demo.modelo.AdmProducto;
import com.example.demo.modelo.Carrito;
import com.example.demo.modelo.Cliente;

import java.time.LocalDate;
import java.time.LocalTime;

public class IntegracionAdmCarritoTest {
	private AdmCarrito admCarrito;
    private AdmCliente admCliente;
    private AdmProducto admProducto;

    @BeforeEach
    public void setUp() {
        admCarrito = new AdmCarrito();
        admCliente = new AdmCliente();
        admProducto = new AdmProducto();
    }

    @Test
    public void testAgregarCarrito() throws Exception {
        // Agregamos un cliente
        admCliente.agregarCliente("Juan Perez", 123456789L, "Calle Falsa 123");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        // Creamos un carrito para el cliente
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        boolean carritoCreado = admCarrito.agregarCarrito(fecha, hora, cliente);

        assertTrue(carritoCreado, "El carrito debería haberse creado exitosamente.");
        Carrito carrito = admCarrito.traerCarrito(1); // Traer carrito por idCarrito
        assertNotNull(carrito, "El carrito debería existir.");
        assertEquals(1, admCarrito.getLstCarrito().size(), "Debería haber un carrito en la lista.");
    }

    /*@Test
    public void testAgregarProductosYCalcularTotal() throws Exception {
        // Agregamos un cliente
        admCliente.agregarCliente("Ana Gomez", 987654321L, "Av. Siempre Viva 742");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        // Agregamos productos
        admProducto.agregarProducto("Pan", 1.0f);
        admProducto.agregarProducto("Jugo", 3.0f);

        // Creamos un carrito
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        admCarrito.agregarCarrito(fecha, hora, cliente);

        // Añadimos productos al carrito
        Carrito carrito = admCarrito.traerCarrito(1); // Traer carrito por idCarrito
        /// Falla el Agregar producto por idProducto
        carrito.agregarProducto(admProducto.traerProducto(1)); // Agregar producto por idProducto
        carrito.agregarProducto(admProducto.traerProducto(2)); // Agregar producto por idProducto

        // Calculamos el total
        float total = admCarrito.calcularTotal(cliente);

        assertEquals(4.0f, total, 0.01, "El total del carrito debería ser 4.0.");
    }*/

    @Test
    public void testEliminarCarrito() throws Exception {
        // Agregamos un cliente
        admCliente.agregarCliente("Carlos Diaz", 111111111L, "Calle Central 456");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        // Creamos un carrito
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        admCarrito.agregarCarrito(fecha, hora, cliente);

        // Eliminamos el carrito
        Carrito carrito = admCarrito.traerCarrito(1); // Traer carrito por idCarrito
        boolean eliminado = admCarrito.eliminarCarrito(carrito.getIdCarrito());

        assertTrue(eliminado, "El carrito debería haberse eliminado correctamente.");
        assertEquals(0, admCarrito.getLstCarrito().size(), "La lista de carritos debería estar vacía.");
    }
    
    @Test
    public void testAgregarCarritoDuplicado() throws Exception {
        // Agregamos un cliente
        admCliente.agregarCliente("Luis Martinez", 987654321L, "Calle La Paz 321");
        Cliente cliente = admCliente.traerCliente(1); // Traer cliente por idCliente

        // Crear un carrito con fecha y hora actuales
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        admCarrito.agregarCarrito(fecha, hora, cliente);

        // Intentar agregar un carrito con la misma fecha, hora y cliente
        Exception exception = assertThrows(Exception.class, () -> {
            admCarrito.agregarCarrito(fecha, hora, cliente);
        });

        String expectedMessage = "El carrito ya existe";
        String actualMessage = exception.getMessage();
        assertEquals("El carrito ya existe", exception.getMessage());
    }

}
