package com.example.demo.modelo;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Supermercado {
	private AdmProducto admProducto = new AdmProducto() ;
	private AdmCliente admCliente = new AdmCliente();
	private AdmCarrito admCarrito = new AdmCarrito();
	
	public Supermercado() {}
	
	public AdmProducto getAdmProducto() {
		return admProducto;
	}

	public void setAdmProducto(AdmProducto admProducto) {
		this.admProducto = admProducto;
	}

	public AdmCliente getAdmCliente() {
		return admCliente;
	}

	public void setAdmCliente(AdmCliente admCliente) {
		this.admCliente = admCliente;
	}
	
	public AdmCarrito getAdmCarrito() {
		return admCarrito;
	}
	
	public void setAdmCarrito(AdmCarrito admCarrito) {
		this.admCarrito = admCarrito;
	}
	
	public boolean agregarProducto(String producto,float precio) throws Exception {
		return admProducto.agregarProducto(producto, precio);
	}
	
	public Producto traerProducto(int idProducto) {
		return admProducto.traerProducto(idProducto);
	}
	
	public boolean eliminarProducto(int idProducto) throws Exception {
		return admProducto.eliminarProducto(idProducto);
	}
	
	public boolean modificarProducto(int idProducto, String producto, double precio) throws Exception{
		return admProducto.modificarProducto(idProducto, producto, precio);
	}
	
	public boolean agregarCliente(String cliente, long dni, String direccion) throws Exception {
		return admCliente.agregarCliente(cliente, dni, direccion);
	}
	
	public Cliente traerCliente(int idCliente) {
		return admCliente.traerCliente(idCliente);
	}
	
	public Cliente traerCliente(String Cliente) {
		return admCliente.traerCliente(Cliente);
	}
	
	public boolean modificarCliente(int idCliente, String cliente, long dni, String direccion) throws Exception {
		return admCliente.modificarCliente(idCliente, cliente, dni, direccion);
	}
	
	public boolean eliminarCLiente(int idCliente) throws Exception {
		return admCliente.eliminarCLiente(idCliente);
	}
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		return admCarrito.agregarCarrito(fecha, hora, cliente);
	}
	
	public Carrito traerCarrito(Cliente cliente) {
		return admCarrito.traerCarrito(cliente);
	}
	
	public Carrito traerCarrito(int idCarrito) {
		return admCarrito.traerCarrito(idCarrito);
	}
	
	public boolean eliminarCarrito(int idCarrito) throws Exception {
		return admCarrito.eliminarCarrito(idCarrito);
	}
	
	/////////
	public float calcularTotal(Cliente cliente) throws Exception {
		return admCarrito.calcularTotal(cliente);
	}
	
	public float calcularTotal(int dniCliente) throws Exception {
		return admCarrito.calcularTotal(dniCliente);
	}
	
	public float calcularTotal(LocalDate fechaInicio, LocalDate fechaFin) {
		return admCarrito.calcularTotal(fechaInicio, fechaFin);
	}
	
	public float calcularTotal(LocalDate fecha) {
		return admCarrito.calcularTotal(fecha);
	}
	
	public float calcularTotal(int mes, int anio) throws Exception {
		return admCarrito.calcularTotal(mes, anio);
	}
	
	public float calcularTotal(LocalDate fechaInicio, LocalDate fechaFin, Cliente cliente) throws Exception {
		return admCarrito.calcularTotal(fechaInicio, fechaFin, cliente);
	}
	
	public float calcularTotal(LocalDate fecha, Cliente cliente) throws Exception {
		return admCarrito.calcularTotal(fecha, cliente);
	}
	
	public float calcularTotal(int mes, int anio, Cliente cliente) throws Exception {
		return admCarrito.calcularTotal(mes, anio, cliente);
	}
	
	public float calcularTotal(int mes, int anio, int dniCliente) throws Exception {
		return admCarrito.calcularTotal(mes, anio, dniCliente);
	}
}
