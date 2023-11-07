import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

class GestionXML {

    public static Document crearDocument() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static Document getDocumentDesdeRuta(String ruta) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                doc = dBuilder.parse(new File(ruta));
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void generarXML() {
        Document doc = GestionXML.crearDocument();

        doc.setXmlVersion("1.0");

        // Crear elementos y atributos
        Element datosPersonales = doc.createElement("DatosPersonales");
        Element nombreDocumento = doc.createElement("NombreDocumento");
        Element fecha = doc.createElement("fecha");
        fecha.appendChild(doc.createTextNode("09-08-2019"));
        Element usuarios = doc.createElement("usuarios");

        // Crear Elemento usuario con sublementos
        Element usuario = doc.createElement("usuario");
        Element nif = doc.createElement("nif");
        Element nombre = doc.createElement("nombre");
        Element apellidos = doc.createElement("apellidos");
        Element fechaNacimiento = doc.createElement("fechaNacimiento");
        Element asignatura = doc.createElement("asignatura");

        // Crear Elemento usuario2 con sublementos
        Element usuario2 = doc.createElement("usuario");
        Element nif2 = doc.createElement("nif");
        Element nombre2 = doc.createElement("nombre");
        Element apellidos2 = doc.createElement("apellidos");
        Element fechaNacimiento2 = doc.createElement("fechaNacimiento");
        Element asignatura2 = doc.createElement("asignatura");

        // Crear Elemento usuario3 con sublementos
        Element usuario3 = doc.createElement("usuario");
        Element nif3 = doc.createElement("nif");
        Element nombre3 = doc.createElement("nombre");
        Element apellidos3 = doc.createElement("apellidos");
        Element fechaNacimiento3 = doc.createElement("fechaNacimiento");
        Element asignatura3 = doc.createElement("asignatura");

        // establecer jerarquía

        doc.appendChild(datosPersonales);
        datosPersonales.appendChild(nombreDocumento);
        datosPersonales.appendChild(fecha);
        datosPersonales.appendChild(usuarios);

        // Agregar usuario con id=1 en usuarios
        usuarios.appendChild(usuario);
        usuario.setAttribute("id", "1");
        usuario.appendChild(nif);
        Text textNif = doc.createTextNode("0000000T");
        nif.appendChild(textNif);
        usuario.appendChild(nombre);
        Text textNombre = doc.createTextNode("Manuel");
        nombre.appendChild(textNombre);
        usuario.appendChild(apellidos);
        Text textApellidos = doc.createTextNode("Alconchel Fernández");
        apellidos.appendChild(textApellidos);
        usuario.appendChild(fechaNacimiento);
        Text textFechaNacimiento = doc.createTextNode("14-12-1988");
        fechaNacimiento.appendChild(textFechaNacimiento);
        usuario.appendChild(asignatura);
        Text textAsignatura = doc.createTextNode("Java");
        asignatura.appendChild(textAsignatura);

        // Agregar usuario con id=2 en usuarios
        usuarios.appendChild(usuario2);
        usuario2.setAttribute("id", "2");
        usuario2.appendChild(nif2);
        nif2.appendChild(doc.createTextNode("0000000O"));
        usuario2.appendChild(nombre2);
        nombre2.appendChild(doc.createTextNode("Óscar"));
        usuario2.appendChild(apellidos2);
        apellidos2.appendChild(doc.createTextNode("rodríguez Heredia"));
        usuario2.appendChild(fechaNacimiento2);
        fechaNacimiento2.appendChild(doc.createTextNode("07-12-1990"));
        usuario2.appendChild(asignatura2);
        asignatura2.appendChild(doc.createTextNode("PHP"));

        // Agregar usuario con id=3 en usuarios
        usuarios.appendChild(usuario3);
        usuario3.setAttribute("id", "3");
        usuario3.appendChild(nif3);
        nif3.appendChild(doc.createTextNode("0000000Z"));
        usuario3.appendChild(nombre3);
        nombre3.appendChild(doc.createTextNode("Genaro"));
        usuario3.appendChild(apellidos3);
        apellidos3.appendChild(doc.createTextNode("Jiménez Moreno"));
        usuario3.appendChild(fechaNacimiento3);
        fechaNacimiento3.appendChild(doc.createTextNode("05-11-1989"));
        usuario3.appendChild(asignatura3);
        asignatura3.appendChild(doc.createTextNode("Python"));

        Text textoNombreDocumento = doc.createTextNode("Datos Personales Clientes");
        nombreDocumento.appendChild(textoNombreDocumento);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File("datosPersonales.xml"));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
    }

    public static void leerDocumentoXML(String ruta) {
        try {
            File arxXml = new File(ruta);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arxXml);
            doc.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" +
            doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("usuario");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("--------------");
                    Element eElement = (Element) node;
                    System.out.println("Usuario con id: " + eElement.getAttribute("id"));
                    System.out.println("NIF: " + eElement.getElementsByTagName("nif").item(0).getTextContent());
                    System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Apellidos: " + eElement.getElementsByTagName("apellidos").item(0).getTextContent());
                    System.out.println("fechaNacimiento: " + eElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent());
                    System.out.println("Asignatura: " + eElement.getElementsByTagName("asignatura").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}