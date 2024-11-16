package Gestores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import pojos.Aerolinea;

public class GestorFicherosBinarios {
	public static String generarFicheroDat(String csvLeido) {

		File ficheroEscribir = Utils.prepararFichero(csvLeido, "dat");
		Aerolinea aerolinea = Utils.leerAerolineaCSV(csvLeido);

		try (FileOutputStream fos = new FileOutputStream(ficheroEscribir);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(aerolinea);

		} catch (IOException e) {
			System.err.println("Error escribiendo el fichero");
			e.printStackTrace();
			return null;
		}

		return ficheroEscribir.getName();

	}
	
	public static List<Aerolinea> leerTodosLosFicherosBinarios() {
		File directorio = new File("FicherosAerolineas");
		List<File> csvs = Utils.devolverSoloFicherosConExtension(directorio.listFiles(), "dat");

		List<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		for (File csv : csvs) {
			aerolineas.add(leerAerolineaDeFicheroBinario(csv));
		}

		return aerolineas;
	}
	
	public static Aerolinea leerAerolineaDeFicheroBinario(File aerolineaCSV) {
		Aerolinea aerolinea = null;

		try (FileInputStream fis = new FileInputStream(aerolineaCSV);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			aerolinea = (Aerolinea) ois.readObject();

		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido encontrar el archivo " + aerolineaCSV.getName());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo " + aerolineaCSV.getName());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Error convirtiendo el contenido a un objeto del tipo aerolínea.");
			e.printStackTrace();
		}

		return aerolinea;

	}
}
