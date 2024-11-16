package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aerolinea implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String sede;
	private List<Ruta> rutas = new ArrayList<Ruta>();

	public Aerolinea(String nombre, String sede, List<Ruta> rutas) {
		this.nombre = nombre;
		this.sede = sede;
		this.rutas = rutas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSede() {
		return sede;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	@Override
	public String toString() {
		String texto = "Aerolinea\n\nNombre: " + nombre + "\nSede: " + sede + "\nRutas:\n";

		for (Ruta ruta : rutas) {
			texto += "\t" + ruta.getOrigen() + " -> " + ruta.getDestino() + " : " + ruta.getViajeros() + "\n";
		}

		return texto;
	}

}
