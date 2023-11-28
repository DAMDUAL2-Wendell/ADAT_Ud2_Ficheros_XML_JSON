import java.io.File;

public class Main {
    public static void main(String[] args) {

        String rutaRelativa = "ADAT\\ADAT_Ud2_Ficheros_XML_JSON\\";
        String nombreFichero = "datosPersonales.xml";
        String ruta = rutaRelativa + nombreFichero;

        // Generar XML
        GestionXML.generarXML(ruta);

        File f = new File(ruta);
        if (f.exists() && f.isFile()) {

            // Leer XML
            GestionXML.leerDocumentoXML(ruta);

        }

    }
}
