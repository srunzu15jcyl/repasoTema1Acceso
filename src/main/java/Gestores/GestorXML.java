package Gestores;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import Utils.Utils;
import pojos.Aerolinea;
import pojos.Ruta;

public class GestorXML {

	public static boolean crearFichero() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Error al crear el parser.");
			e.printStackTrace();
			return false;
		}

		DOMImplementation implementacion = builder.getDOMImplementation();

		Document documento = implementacion.createDocument(null, "aerolineas", null);
		documento.setXmlVersion("1.0");

		for (Aerolinea aerolinea : Utils.leerTodosLosFicheros()) {
			Element aerolineaElemento = anadirNodoSinTexto("aerolinea", documento.getDocumentElement(), documento);

			anadirNodoConTexto("nombre", aerolinea.getNombre(), aerolineaElemento, documento);
			anadirNodoConTexto("sede", aerolinea.getSede(), aerolineaElemento, documento);


			for (Ruta ruta : aerolinea.getRutas()) {
				Element rutaElemento = anadirNodoSinTexto("ruta", aerolineaElemento, documento);

				anadirNodoConTexto("origen", ruta.getOrigen(), rutaElemento, documento);
				anadirNodoConTexto("destino", ruta.getDestino(), rutaElemento, documento);

				Element pasajerosElemento = anadirNodoSinTexto("pasajeros", rutaElemento, documento);

				for (int pasajeros : ruta.getViajeros()) {
					anadirNodoConTexto("pasajeros", Integer.toString(pasajeros), pasajerosElemento, documento);
				}
			}
		}
		
		File fichero = new File("FicherosAerolineas/aerolineas.xml");
		
		Source origen = new DOMSource(documento);
		Result resultado = new StreamResult(fichero);

		Transformer transformador;
		try {
			transformador = TransformerFactory.newInstance().newTransformer();
			transformador.transform(origen, resultado);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.err.println("Error al escribir el fichero usando DOM");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	static void anadirNodoConTexto(String nombre, String valor, Element padre, Document documento) {
		Element elemento = documento.createElement(nombre);
		Text texto = documento.createTextNode(valor);
		elemento.appendChild(texto);

		padre.appendChild(elemento);
	}

	static Element anadirNodoSinTexto(String nombre, Element padre, Document documento) {
		Element elemento = documento.createElement(nombre);
		padre.appendChild(elemento);

		return elemento;
	}

}
