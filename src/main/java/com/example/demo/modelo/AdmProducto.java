package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.List;

public class AdmProducto {
	//private List<Producto> lstProducto = new ArrayList<Producto>();
	private List<Producto> gondola = new ArrayList<Producto>();
	
	public AdmProducto() {
	}

	public List<Producto> getGondola() {
		return gondola;
	}

	public void setGondola(List<Producto> gondola) {
		this.gondola = gondola;
	}
	
	@Override
	public String toString() {
		return "AdmProducto [gondola=" + gondola + "]";
	}

	public boolean agregarProducto(String producto, float precio) throws Exception {
		Producto aux = null;
		for (int i = 0; i < gondola.size(); i++) {
			if (gondola.get(i).getProducto() == producto) {
				throw new Exception("El producto " + producto + " ya esta registrado");
			}
		}
		/*
		 * if(this.traerPelicula(pelicula)!=null){ throw new
		 * Exception("pelicula existente"); }
		 */
		if (gondola.size() == 0) {
			aux = new Producto(1, producto, precio);
		} else {
			aux = new Producto(gondola.get(gondola.size() - 1).getIdProducto() + 1, producto, precio);
		}

		return gondola.add(aux);
	}

	public Producto traerProducto(int idProducto) {
		Producto aux = null;
		int i = 0;
		while (aux == null && i < gondola.size()) {
			if (gondola.get(i).getIdProducto() == idProducto) {
				aux = gondola.get(i);
			}
			i++;
		}
		return aux;
	}

	public boolean modificarProducto(int idProducto, String producto, double precio) throws Exception {
		Producto producto1 = traerProducto(idProducto);
		float precion = (float) precio;
		if (producto1 == null) {
			throw new Exception("Error: El producto no existe");
		}

		boolean resultado = true;
		producto1.setProducto(producto);
		producto1.setPrecio(precion);

		return resultado;
	}

	public boolean eliminarProducto(int idProducto) throws Exception {
		Producto producto1 = traerProducto(idProducto);
		if (producto1 == null) {
			throw new Exception("Error: El producto no existe");
		}
		return gondola.remove(producto1);
	}
}
