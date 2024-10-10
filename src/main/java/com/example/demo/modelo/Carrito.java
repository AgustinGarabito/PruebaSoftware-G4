package com.example.demo.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class Carrito {
	private int idCarrito;
	private LocalDate fecha;
	private LocalTime hora;
	private Cliente cliente;
	private List<ItemCarrito> lstItem = new ArrayList<ItemCarrito>();
	
	public Carrito() {};

	public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, Cliente cliente) {
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.hora = hora;
		this.cliente = cliente;
		
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public List<ItemCarrito> getLstItem() {
		return lstItem;
	}

	public void setLstItem(List<ItemCarrito> lstItem) {
		this.lstItem = lstItem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Carrito [idCarrito=" + idCarrito + ", fecha=" + fecha + ", hora=" + hora + ", cliente=" + cliente
				+ ", \nlstItem=" + lstItem + "]";
	}

	public float calcularTotal() {
		float total = 0;
		for (int i = 0; i < lstItem.size(); i++) {
			total = total + lstItem.get(i).calcularSubTotal();
		}
		return total;
	}

	public boolean agregarItem(Producto producto, int cantidad) {
		if (traerItemCarrito(producto) != null) {
			traerItemCarrito(producto).setCantidad(traerItemCarrito(producto).getCantidad() + cantidad);
		} else {
			if(lstItem.size()==0) {
				lstItem.add(new ItemCarrito(1,producto,cantidad));					
				}else {
				lstItem.add(new ItemCarrito(lstItem.get(lstItem.size()-1).getIdItem()+1, producto, cantidad));
				}
			}		
		return true;
	}

	public boolean eliminarItem(Producto producto, int cantidad) throws Exception {
		ItemCarrito itemEncontrado = traerItemCarrito(producto);
		if (itemEncontrado == null)
			throw new Exception("No existe el item que contenga el producto");
		if (cantidad < itemEncontrado.getCantidad()) {
			itemEncontrado.setCantidad(itemEncontrado.getCantidad() - cantidad);
		}
		if (itemEncontrado.getCantidad() == cantidad) {
			lstItem.remove(itemEncontrado);
		}
		return true;
	}

	public ItemCarrito traerItemCarrito(Producto producto) {
		ItemCarrito ic = null;
		int i = 0;
		while (i < lstItem.size() && ic == null) {
			if (lstItem.get(i).getProducto().equals(producto)) {
				ic = lstItem.get(i);
			}
			i++;
		}
		return ic;
	}

	public ItemCarrito traerItemCarrito(int idItem) {
		ItemCarrito ic = null;
		int i = 0;
		while (i < lstItem.size() && ic == null) {
			if (lstItem.get(i).getIdItem() == idItem) {
				ic = lstItem.get(i);
			}
			i++;
		}
		return ic;
	}

	/*
	 * public boolean agregarItem(Producto producto,int cantidad)throws Exception {
	 * ItemCarrito i=null; if (traerItemCarrito(producto) != null) {
	 * traerItemCarrito(producto).setCantidad(traerItemCarrito(producto).getCantidad
	 * ()+cantidad); } else { lstItem.add(new ItemCarrito(idMayor() + 1, producto,
	 * cantidad)); } return true; }
	 */
}
