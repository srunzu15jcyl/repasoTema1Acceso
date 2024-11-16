package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Gestores.GestorFicherosBinarios;
import Gestores.GestorFicherosTexto;
import pojos.Aerolinea;
import pojos.Ruta;

public class Utils {

	public static Aerolinea leerAerolineaCSV(String textoCSV) {

		String[] partes = textoCSV.split(";");

		List<Ruta> rutas = new ArrayList<Ruta>();

		for (int i = 1; i < partes.length - 1; i++) {
			String[] partesRuta = partes[i].split(",");

			List<Integer> viajeros = new ArrayList<Integer>();

			for (int j = 2; j < partesRuta.length; j++) {
				viajeros.add(Integer.parseInt(partesRuta[j]));
			}

			rutas.add(new Ruta(partesRuta[0], partesRuta[1], viajeros));
		}

		Aerolinea aerolinea = new Aerolinea(partes[0], partes[partes.length - 1], rutas);

		return aerolinea;

	}

	public static File prepararFichero(String csvLeido, String extension) {
		File directorio = new File("FicherosAerolineas");

		if (!directorio.exists())
			directorio.mkdir();

		String[] elementos = csvLeido.split(";");

		String nombreFichero = elementos[0] + "." + extension;

		File ficheroEscribir = new File(directorio, nombreFichero);

		if (ficheroEscribir.exists()) {
			System.err.println("Ya existe un fichero para la aerolínea " + elementos[0]);
			return null;
		}

		return ficheroEscribir;
	}

	public static List<File> devolverSoloFicherosConExtension(File[] todosFicheros, String extensionDada) {
		List<File> ficherosCSV = new ArrayList<File>();

		for (File fichero : todosFicheros) {

			String[] partesFichero = fichero.getName().split("\\.");

			String extensionFichero = partesFichero[partesFichero.length - 1];
			if (extensionFichero.equals(extensionDada))
				ficherosCSV.add(fichero);
		}

		return ficherosCSV;

	}

	public static List<Aerolinea> leerTodosLosFicheros() {
		List<Aerolinea> aerolineas = new ArrayList<Aerolinea>();
		aerolineas.addAll(GestorFicherosTexto.leerTodosLosFicherosDeTexto());
		aerolineas.addAll(GestorFicherosBinarios.leerTodosLosFicherosBinarios());
		return aerolineas;

	}

}
