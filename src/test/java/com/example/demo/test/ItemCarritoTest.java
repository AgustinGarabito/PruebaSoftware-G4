package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.modelo.ItemCarrito;
import com.example.demo.modelo.Producto;

public class ItemCarritoTest {
    
    private Producto productoMock;
    private ItemCarrito itemCarrito;
    
    @BeforeEach
    public void setUp() {
        productoMock = mock(Producto.class);
    }

    @Test
    public void testCalcularSubTotal() {
        when(productoMock.getPrecio()).thenReturn(20.0f);
        itemCarrito = new ItemCarrito(1, productoMock, 3);
        float subTotal = itemCarrito.calcularSubTotal();
        assertEquals(60.0f, subTotal, 0.01f);
    }
    
    @Test
    public void testCalcularSubTotalCantidadNegativa() {
        when(productoMock.getPrecio()).thenReturn(20.0f);
        itemCarrito = new ItemCarrito(1, productoMock, -3);
        float subTotal = itemCarrito.calcularSubTotal();
        assertEquals(-60.0f, subTotal, 0.01f);
    }
    
    @Test
    public void testCalcularSubTotalPrecioNegativo() {
        when(productoMock.getPrecio()).thenReturn(-20.0f);
        itemCarrito = new ItemCarrito(1, productoMock, 3);
        float subTotal = itemCarrito.calcularSubTotal();
        assertEquals(-60.0f, subTotal, 0.01f);
    }
    
    @Test
    public void testCalcularSubTotalCantidadCero() {
        when(productoMock.getPrecio()).thenReturn(20.0f);
        itemCarrito = new ItemCarrito(1, productoMock, 0);
        float subTotal = itemCarrito.calcularSubTotal();
        assertEquals(0, subTotal, 0.01f);
    }
}
