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
    private Long num_instruc;
    
    public GeneracionCodigo(String file) throws FicheroFormatoException {
        String[] splited = file.split("\\.");
        if (splited.length != 2 || !splited[1].equals("ml")) {
            throw new FicheroFormatoException();
        }
        
        fout_name = splited[0] + ".code";
        buffer = null;
        num_etiqueta = null;
        num_instruc = null;
    }
    
    public void inicializar() {
        num_etiqueta = -1; // Inicializar en uno menos al primero
        num_instruc = new Long(0); // Siguiente dirección
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
        num_instruc++;
    }
    
    public void operacion(TipoOperador op) {
        if (op != null) {
            switch (op) {
            case SUM :
                instruccion("PLUS");
                break;

            case RES :
                instruccion("SBT");
                break;

            case OR :
                instruccion("OR");
                break;

            case MOD :
                instruccion("MOD");
                break;

            case DIV :
                instruccion("DIV");
                break;

            case MUL :
                instruccion("TMS");
                break;

            case AND :
                instruccion("AND");
                break;

            case MAY :
                instruccion("GT");
                break;

            case MEN :
                instruccion("LT");
                break;

            case IGU :
                instruccion("EQ");
                break;

            case MAI :
                instruccion("GTE");
                break;

            case MEI :
                instruccion("LTE");
                break;

            case NI :
                instruccion("NEQ");
                break;

            default :
                break;
            }
        }
    }
    
    public Long siguiente_dir() {
        return num_instruc;
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
