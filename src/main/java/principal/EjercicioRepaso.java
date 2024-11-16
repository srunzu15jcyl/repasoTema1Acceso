package principal;

import java.util.List;
import java.util.Scanner;

import Gestores.GestorFicherosBinarios;
import Gestores.GestorFicherosTexto;
import Gestores.GestorXML;
import Utils.Utils;
import pojos.Aerolinea;

public class EjercicioRepaso {

	public static void main(String[] args) {
		int opcion;
		Scanner escaner = new Scanner(System.in);

		do {

			mostrarMenu();

			System.out.println("Introduzca la opción deseada: ");
			opcion = escaner.nextInt();
			escaner.nextLine();

			switch (opcion) {
			case 0:
				System.out.println("Hasta otra");
				break;
			case 1:
				pedirCSVParaCrearFichero(escaner, "csv");
				break;
			case 2:
				imprimirAerolineas(GestorFicherosTexto.leerTodosLosFicherosDeTexto());
				break;
			case 3:
				pedirCSVParaCrearFichero(escaner, "dat");
				break;
			case 4:
				imprimirAerolineas(GestorFicherosBinarios.leerTodosLosFicherosBinarios());
				break;
			case 5:
				imprimirAerolineas(Utils.leerTodosLosFicheros());
				break;
			case 6: 
				if(GestorXML.crearFichero())
					System.out.println("XML creado correctamente");
				break;
			default:
				System.out.println("Opción desconocida");

			}

		} while (opcion != 0);
	}

	public static void mostrarMenu() {
		System.out.println();
		System.out.println("----MENU----");
		System.out.println("0) Salir");
		System.out.println("1) Crear csv de manera manual");
		System.out.println("2) Leer todos los csv de FicherosAerolineas");
		System.out.println("3) Crear dat de manera manual");
		System.out.println("4) Leer todos los .dat de FicherosAerolineas");
		System.out.println("5) Leer todos los ficheros de FicherosAerolineas");
		System.out.println("6) Crear XML con todos los ficheros");


		System.out.println();

	}

	public static void imprimirAerolineas(List<Aerolinea> aerolineas) {
		for (Aerolinea aerolinea : aerolineas) {
			System.out.println(aerolinea);
		}
	}

	public static void pedirCSVParaCrearFichero(Scanner escaner, String extension) {
		System.out.println("Introduzca el texto csv");
		String csvLeido = escaner.nextLine();

		String nombreFicheroGenerado;

		if (extension.equals("dat"))
			nombreFicheroGenerado = GestorFicherosBinarios.generarFicheroDat(csvLeido);
		else
			nombreFicheroGenerado = GestorFicherosTexto.generarFicheroDeTexto(csvLeido);

		if (nombreFicheroGenerado != null) {
			System.out.println("Fichero" + nombreFicheroGenerado + " generado correctamente");
		}

	}

}
