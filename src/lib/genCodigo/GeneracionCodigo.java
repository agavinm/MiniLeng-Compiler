//*****************************************************************
// File:   GeneracionCodigo.java
// Author: Andrés Gavín Murillo 716358
// Date:   5/6/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V4.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.genCodigo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import lib.semantico.RegistroExpr.TipoOperador;

public class GeneracionCodigo {
    private String fout_name;
    private String buffer;
    
    private Integer num_etiqueta;
    
    public GeneracionCodigo(String file) throws FicheroFormatoException {
        String[] splited = file.split("\\.");
        if (splited.length != 2 || !splited[1].equals("ml")) {
            throw new FicheroFormatoException();
        }
        
        fout_name = splited[0] + ".code";
        buffer = null;
        num_etiqueta = null;
    }
    
    public void inicializar() {
        num_etiqueta = -1; // Inicializar en uno menos al primero
        buffer = "";
    }
    
    public String nueva_etiqueta() {
        num_etiqueta++;
        return "L" + num_etiqueta;
    }
    
    public void escribir(String linea) {
        buffer += linea + "\n";
    }
    
    public void instruccion(String instr) {
        buffer += "    " + instr + "\n";
    }
    
    public void operacion(TipoOperador op) {
        if (op != null) {
            switch (op) {
            case SUM :
                buffer += "    PLUS\n";
                break;

            case RES :
                buffer += "    SBT\n";
                break;

            case OR :
                buffer += "    OR\n";
                break;

            case MOD :
                buffer += "    MOD\n";
                break;

            case DIV :
                buffer += "    DIV\n";
                break;

            case MUL :
                buffer += "    TMS\n";
                break;

            case AND :
                buffer += "    AND\n";
                break;

            case MAY :
                buffer += "    GT\n";
                break;

            case MEN :
                buffer += "    LT\n";
                break;

            case IGU :
                buffer += "    EQ\n";
                break;

            case MAI :
                buffer += "    GTE\n";
                break;

            case MEI :
                buffer += "    LTE\n";
                break;

            case NI :
                buffer += "    NEQ\n";
                break;

            default :
                break;
            }
        }
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
