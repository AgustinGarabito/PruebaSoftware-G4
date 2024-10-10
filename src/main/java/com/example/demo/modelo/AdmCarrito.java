package com.example.demo.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdmCarrito {
	private List<Carrito> lstCarrito = new ArrayList<Carrito>();

	public AdmCarrito() {
	}
	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	@Override
	public String toString() {
		return "AdmCarrito [lstCarrito=" + lstCarrito + "]";
	}
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		Carrito aux = this.traerCarrito(cliente);
		if (aux != null) {
			throw new Exception("El carrito ya existe");
		}
		if (lstCarrito.size() == 0) {
			aux = new Carrito(1, fecha, hora, cliente);
		} else {
			aux = new Carrito(lstCarrito.get(lstCarrito.size() - 1).getIdCarrito() + 1, fecha, hora, cliente);
		}
		return lstCarrito.add(aux);
	}
	
	public Carrito traerCarrito(Cliente cliente) {
		Carrito c = null;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().equals(cliente)) {
				c = lstCarrito.get(i);
			}
		}
		return c;
	}
	
	public Carrito traerCarrito(int idCarrito) {
		Carrito c = null;
		boolean encontrado = false;
		int i = 0;
		while (i < lstCarrito.size() && encontrado == false) {
			if (lstCarrito.get(i).getIdCarrito() == idCarrito) {
				c = lstCarrito.get(i);
			}
			i++;
		}
		return c;
	}
	
	public boolean eliminarCarrito(int idCarrito) throws Exception {
		Carrito carrito = this.traerCarrito(idCarrito);
		if (carrito == null) {
			throw new Exception("Error: El cliente no existe");
		}
		return lstCarrito.remove(carrito);
	}
	
	
	///////////////////no se si va aca supongo que si por el lstCarrito
	public float calcularTotal(Cliente cliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().equals(cliente)) {
				encontrado = true;
				for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
					total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
				}
			}
		}
		if (!encontrado) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}
	
	public float calcularTotal(int dniCliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().getDni() == dniCliente) {
				encontrado = true;
				for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
					total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
					
				}
			}
		}
		if (!encontrado) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}
	
	public float calcularTotal(LocalDate fechaInicio, LocalDate fechaFin) {
		float total = 0;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getFecha().isAfter(fechaInicio) && lstCarrito.get(i).getFecha().isBefore(fechaFin)) {
				for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
					total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
				}
			}
		}
		return total;
	}
	
	public float calcularTotal(LocalDate fecha) {
		float total = 0;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getFecha().equals(fecha)) {
				for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
					total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
				}
			}
		}
		return total;
	}
	
	public float calcularTotal(int mes, int anio) throws Exception {
		float total = 0;
		if (mes < 1 || mes > 12) {
			throw new Exception("El mes es incorrecto");
		}
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getFecha().getMonthValue() == mes && lstCarrito.get(i).getFecha().getYear() == anio) {
				for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
					total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
				}
			}
		}
		return total;
	}

	public float calcularTotal(LocalDate fechaInicio, LocalDate fechaFin, Cliente cliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().equals(cliente)) {
				encontrado = true;
				if (lstCarrito.get(i).getFecha().isBefore(fechaFin)
						&& lstCarrito.get(i).getFecha().isAfter(fechaInicio)) {
					for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
						total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
					}
				}
			}
		}
		if (encontrado == false) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}

	public float calcularTotal(LocalDate fecha, Cliente cliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().equals(cliente)) {
				encontrado = true;
				if (lstCarrito.get(i).getFecha().equals(fecha)) {
					for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
						total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
					}
				}
			}
		}
		if (encontrado == false) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}

	public float calcularTotal(int mes, int anio, Cliente cliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		if (mes < 1 || mes > 12) {
			throw new Exception("El mes es incorrecto");
		}
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().equals(cliente)) {
				encontrado = true;
				if (lstCarrito.get(i).getFecha().getMonthValue() == mes
						&& lstCarrito.get(i).getFecha().getYear() == anio) {
					for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
						total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
					}
				}
			}
		}
		if (encontrado == false) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}

	public float calcularTotal(int mes, int anio, int dniCliente) throws Exception {
		float total = 0;
		boolean encontrado = false;
		if (mes < 1 || mes > 12) {
			throw new Exception("El mes es incorrecto");
		}
		for (int i = 0; i < lstCarrito.size(); i++) {
			if (lstCarrito.get(i).getCliente().getDni() == dniCliente) {
				encontrado = true;
				if (lstCarrito.get(i).getFecha().getMonthValue() == mes
						&& lstCarrito.get(i).getFecha().getYear() == anio) {
					for (int j = 0; j < lstCarrito.get(i).getLstItem().size(); j++) {
						total = total + lstCarrito.get(i).getLstItem().get(j).calcularSubTotal();
					}
				}
			}
		}
		if (encontrado == false) {
			throw new Exception("El cliente no existe");
		}
		return total;
	}
}
