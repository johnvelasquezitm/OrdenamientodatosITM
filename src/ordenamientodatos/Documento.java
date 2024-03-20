package ordenamientodatos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Documento {

    private String apellido1;
    private String apellido2;
    private String nombre;
    private String documento;

    public Documento(String apellido1, String apellido2, String nombre, String documento) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return apellido1 + " " + apellido2 + " " + nombre;
    }

    //********** Atributos y Metodos estaticos ********** 
    //Almacena la lista de documentos
    public static List<Documento> documentos = new ArrayList();
    public static String[] encabezados;

    //Metodo que obtiene los datos desde el archivo CSV
    public static void obtenerDatosDesdeArchivo(String nombreArchivo) {
        documentos.clear();
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                String linea = br.readLine();
                encabezados = linea.split(";");
                linea = br.readLine();
                while (linea != null) {
                    String[] textos = linea.split(";");
                    if (textos.length >= 4) {
                        Documento d = new Documento(textos[0], textos[1], textos[2], textos[3]);
                        documentos.add(d);
                    }
                    linea = br.readLine();
                }
            } catch (Exception ex) {
            }
        }
    }

    //Metodo para mostrar los datos en una tabla
    public static void mostrarDatos(JTable tbl) {
        String[][] datos = null;
        if (documentos.size() > 0) {
            datos = new String[documentos.size()][encabezados.length];
            for (int i = 0; i < documentos.size(); i++) {
                datos[i][0] = documentos.get(i).apellido1;
                datos[i][1] = documentos.get(i).apellido2;
                datos[i][2] = documentos.get(i).nombre;
                datos[i][3] = documentos.get(i).documento;
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

}
