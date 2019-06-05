package lib.genCodigo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeneracionCodigo {
    private String fout_name;
    private String buffer;
    
    private Integer num_etiqueta_pri;
    private Integer num_etiqueta_fin;
    
    public GeneracionCodigo(String file) throws FicheroFormatoException {
        String[] splited = file.split("\\.");
        if (splited.length != 2 || !splited[1].equals("ml")) {
            throw new FicheroFormatoException();
        }
        
        fout_name = splited[0] + ".code";
        buffer = null;
        num_etiqueta_pri = null;
        num_etiqueta_pri = null;
    }
    
    public void inicializar() {
        num_etiqueta_pri = -1; // Inicializar en uno menos al primero
        num_etiqueta_fin = -1;
        buffer = "";
    }
    
    public String nueva_etiqueta() {
        num_etiqueta_pri++;
        return "L" + num_etiqueta_pri;
    }
    
    public String cerrar_etiqueta() {
        num_etiqueta_fin++;
        return "L" + num_etiqueta_fin;
    }
    
    public void escribir(String linea) {
        buffer += linea + "\n";
    }
    
    public void finalizar(boolean guardar) throws FicheroEscribirException {
        if (guardar) {
            try {
                PrintWriter fout = new PrintWriter(fout_name);
                fout.print(buffer);
                fout.close();
            }
            catch (FileNotFoundException e) {
                throw new FicheroEscribirException();
            }
        }
    }
}
