package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.List;

public class AdmCliente {
	private List<Cliente> lstCliente = new ArrayList<Cliente>();

	public AdmCliente() {
	}

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	@Override
	public String toString() {
		return "AdmCliente [lstCliente=" + lstCliente + "]";
	}
	
	public boolean agregarCliente(String cliente, long dni, String direccion) throws Exception {
		Cliente aux = this.traerCliente(cliente);
		if (aux != null) {
			throw new Exception("El cliente " + cliente + " ya existe");
		}
		if (lstCliente.size() == 0) {
			aux = new Cliente(1, cliente, dni, direccion);
		} else {
			aux = new Cliente(lstCliente.get(lstCliente.size() - 1).getIdCliente() + 1, cliente, dni, direccion);
		}

		return lstCliente.add(aux);
	}

	public Cliente traerCliente(int idCliente) {
		Cliente c = null;
		int i = 0;
		while (i < lstCliente.size() && c == null) {
			if (lstCliente.get(i).getIdCliente() == idCliente) {
				c = lstCliente.get(i);
			}
			i++;
		}
		return c;
	}

	public Cliente traerCliente(String cliente) {
		Cliente c = null;
		int i = 0;
		while (i < lstCliente.size() && c == null) {
			if (lstCliente.get(i).getCliente().equals(cliente)) {
				c = lstCliente.get(i);
			}
			i++;
		}
		return c;
	}
	
	public boolean modificarCliente(int idCliente, String cliente, long dni, String direccion) throws Exception {
		Cliente cliente1 = this.traerCliente(idCliente);
		//float precion = (float) precio;
		if (cliente1 == null) {
			throw new Exception("Error: El cliente no existe");
		}

		boolean resultado = true;
		cliente1.setCliente(cliente);
		cliente1.setDireccion(direccion);
		cliente1.setDni(dni);
		cliente1.setIdCliente(idCliente);
		return resultado;
	}
	
	public boolean eliminarCLiente(int idCliente) throws Exception {
		Cliente cliente = this.traerCliente(idCliente);
		if (cliente == null) {
			throw new Exception("Error: El cliente no existe");
		}
		return lstCliente.remove(cliente);
	}
}
