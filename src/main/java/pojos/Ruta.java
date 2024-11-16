package pojos;

import java.io.Serializable;
import java.util.List;

public class Ruta implements Serializable{


	private static final long serialVersionUID = 1L;
	private String origen;
	private String destino;
	private List<Integer> viajeros;

	public Ruta(String origen, String destino, List<Integer> viajeros) {
		this.origen = origen;
		this.destino = destino;
		this.viajeros = viajeros;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Integer> getViajeros() {
		return viajeros;
	}

	public void setViajeros(List<Integer> viajeros) {
		this.viajeros = viajeros;
	}
	
	

}
