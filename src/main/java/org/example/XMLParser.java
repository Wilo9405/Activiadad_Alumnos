package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document; // Import correcto
import org.w3c.dom.Element; // Import correcto

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException; // Cambiar aquí
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public static List<Alumno> parseaAlumnosXML(String filePath) {
        List<Alumno> alumnos = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize(); // Normaliza el documento XML

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellido = element.getElementsByTagName("apellido").item(0).getTextContent();
                    String dni = element.getElementsByTagName("dni").item(0).getTextContent();
                    String curso = element.getElementsByTagName("curso").item(0).getTextContent();

                    Alumno alumno = new Alumno(nombre, apellido, dni, curso);
                    alumnos.add(alumno); // Agrega el alumno a la lista
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.err); // Imprime el stack trace de cualquier excepción
        }

        return alumnos; // Retorna la lista de alumnos
    }
}
