package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.demo.modelo.Carrito;
import com.example.demo.modelo.ItemCarrito;
import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Cliente;

public class CarritoTest {
	private Producto producto1;
	private Producto producto2;
	private ItemCarrito itemCarrito1;
	private ItemCarrito itemCarrito2;
	private ItemCarrito itemCarrito3;
    private Carrito carrito;
    private Cliente cliente;
    
    @BeforeEach
    public void setUp() {
    	producto1 = mock(Producto.class);
        producto2 = mock(Producto.class);
    	
        itemCarrito1 = mock(ItemCarrito.class);
        itemCarrito2 = mock(ItemCarrito.class);
        itemCarrito3 = mock(ItemCarrito.class);
        
        cliente = mock(Cliente.class);
    }

    @Test
    public void testCalcularTotal() {
    	when(itemCarrito1.calcularSubTotal()).thenReturn(60.0f);
    	when(itemCarrito2.calcularSubTotal()).thenReturn(100.0f);
    	when(itemCarrito3.calcularSubTotal()).thenReturn(10.0f);
        
        List<ItemCarrito> lstItems = new ArrayList<>();
        lstItems.add(itemCarrito1);
        lstItems.add(itemCarrito2);
        lstItems.add(itemCarrito3);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(lstItems);
        
        float total = carrito.calcularTotal();
        assertEquals(170.0f, total, 0.01f);
    }
    
    @Test
    public void testTraerItemCarritoPorProducto() {
        when(itemCarrito1.getProducto()).thenReturn(producto1);
        when(itemCarrito2.getProducto()).thenReturn(producto2);
        
        List<ItemCarrito> lstItems = new ArrayList<>();
        lstItems.add(itemCarrito1);
        lstItems.add(itemCarrito2);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(lstItems);
        
        ItemCarrito resultado = carrito.traerItemCarrito(producto1);
        assertEquals(itemCarrito1, resultado);
    }
    
    @Test
    public void testTraerItemCarritoPorProductoNoEncontrado() {
        when(itemCarrito1.getProducto()).thenReturn(producto1);
        when(itemCarrito2.getProducto()).thenReturn(producto2);
        
        List<ItemCarrito> lstItems = new ArrayList<>();
        lstItems.add(itemCarrito1);
        lstItems.add(itemCarrito2);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(lstItems);
        
        ItemCarrito resultadoNoEncontrado = carrito.traerItemCarrito(mock(Producto.class));
        assertEquals(null, resultadoNoEncontrado);
    }

    @Test
    public void testTraerItemCarritoPorIdItem() {

        when(itemCarrito1.getIdItem()).thenReturn(1);
        when(itemCarrito2.getIdItem()).thenReturn(2);
        
        List<ItemCarrito> lstItems = new ArrayList<>();
        lstItems.add(itemCarrito1);
        lstItems.add(itemCarrito2);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(lstItems);
        
        ItemCarrito resultado = carrito.traerItemCarrito(1);
        assertEquals(itemCarrito1, resultado);
    }
    
    @Test
    public void testTraerItemCarritoPorIdItemNoEncontrado() {
        when(itemCarrito1.getIdItem()).thenReturn(1);
        when(itemCarrito2.getIdItem()).thenReturn(2);
        
        List<ItemCarrito> lstItems = new ArrayList<>();
        lstItems.add(itemCarrito1);
        lstItems.add(itemCarrito2);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(lstItems);
        
        ItemCarrito resultadoNoEncontrado = carrito.traerItemCarrito(3);
        assertEquals(null, resultadoNoEncontrado);
    }
    
    @Test
    public void testAgregarItemNuevo() {
        when(producto1.getPrecio()).thenReturn(20.0f);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        boolean resultado = carrito.agregarItem(producto1, 3);
        
        assertTrue(resultado);
        assertEquals(1, carrito.getLstItem().size());
        assertEquals(3, carrito.getLstItem().get(0).getCantidad());
    }
    
    @Test
    public void testEliminarItemCantidadMenor() throws Exception {
        when(producto1.getPrecio()).thenReturn(20.0f);
        itemCarrito1 = new ItemCarrito(1, producto1, 5);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(new ArrayList<ItemCarrito>());
        carrito.getLstItem().add(itemCarrito1);
        
        boolean resultado = carrito.eliminarItem(producto1, 2);
        
        assertTrue(resultado);
        assertEquals(3, itemCarrito1.getCantidad());
    }
    
    @Test
    public void testEliminarItemCantidadMayor() throws Exception {
        when(producto1.getPrecio()).thenReturn(20.0f);
        itemCarrito1 = new ItemCarrito(1, producto1, 5);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(new ArrayList<ItemCarrito>());
        carrito.getLstItem().add(itemCarrito1);
        
        boolean resultado = carrito.eliminarItem(producto1, 6);
        
        assertTrue(resultado);
        assertEquals(0, itemCarrito1.getCantidad());
    }
    
    @Test
    public void testEliminarItemCantidadIgual() throws Exception {
        when(producto1.getPrecio()).thenReturn(20.0f);
        itemCarrito1 = new ItemCarrito(1, producto1, 3);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        carrito.setLstItem(new ArrayList<ItemCarrito>());
        carrito.getLstItem().add(itemCarrito1);
        
        boolean resultado = carrito.eliminarItem(producto1, 3);
        
        assertTrue(resultado);
        assertEquals(0, carrito.getLstItem().size());
    }
    
    @Test
    public void testEliminarItemNoExistente() {
        when(producto1.getPrecio()).thenReturn(20.0f);
        
        carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), cliente);
        
        Exception exception = assertThrows(Exception.class, () -> {
            carrito.eliminarItem(producto1, 1);
        });

        assertEquals("No existe el item que contenga el producto", exception.getMessage());
    }
    
    
}
