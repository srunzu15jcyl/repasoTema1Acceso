package Gestores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import pojos.Aerolinea;

public class GestorFicherosTexto {

	public static String generarFicheroDeTexto(String csvLeido) {
		File ficheroEscribir = Utils.prepararFichero(csvLeido, "csv");

		try (FileWriter fw = new FileWriter(ficheroEscribir); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(csvLeido);

		} catch (IOException e) {
			System.err.println("Error escribiendo el fichero");
			e.printStackTrace();
			return null;
		}

		return ficheroEscribir.getName();

	}
	
	public static List<Aerolinea> leerTodosLosFicherosDeTexto() {
		File directorio = new File("FicherosAerolineas");
		List<File> csvs = Utils.devolverSoloFicherosConExtension(directorio.listFiles(), "csv");

		List<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		for (File csv : csvs) {
			aerolineas.add(GestorFicherosTexto.leerAerolineaDeFicheroDeTexto(csv));
		}

		return aerolineas;
	}

	public static Aerolinea leerAerolineaDeFicheroDeTexto(File aerolineaCSV) {
		Aerolinea aerolinea = null;

		try (FileReader fr = new FileReader(aerolineaCSV); BufferedReader br = new BufferedReader(fr)) {
			String textoLeido = br.readLine();
			aerolinea = Utils.leerAerolineaCSV(textoLeido);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido encontrar el archivo " + aerolineaCSV.getName());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo " + aerolineaCSV.getName());
			e.printStackTrace();
		}

		return aerolinea;

	}

}
