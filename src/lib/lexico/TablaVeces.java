//*****************************************************************
// File:   TablaVeces.java
// Author: Andrés Gavín Murillo 716358
// Date:   15/4/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V1.3
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.lexico;

public class TablaVeces {
    private static final int N = 45;
    private static int tabla[] = new int[N];
    public enum tipo_token {
        tPROGRAMA, tPRINCIPIO, tFIN, tSI, tENT, 
        tSI_NO, tFSI, tMQ, tFMQ, tACCION,
        tPC, tC, tVAR, tENTERO, tBOOLEANO,
        tCARACTER, tVAL, tREF, tAND, tOR,
        tNOT, tMAYOR, tMENOR, tIGUAL, tMAI,
        tMEI, tNI, tTRUE, tFALSE, tESCRIBIR,
        tLEER, tENTACAR, tCARAENT, tMOD, tDIV,
        tOPAS, tSUMA, tRESTA, tMULTIPLICA, tAP,
        tCP, tCONSTENTERA, tCONSTCHAR, tCONSTCAD, tIDENTIFICADOR
    };
    private static final String[] nombres = 
        {
            "programa", "principio", "fin", "si", "ent",
            "si_no", "fsi", "mq", "fmq", "accion",
            ";", ",", "var", "entero", "booleano",
            "caracter", "val", "ref", "and", "or",
            "not", ">", "<", "=", ">=",
            "<=", "<>", "true", "false", "escribir",
            "leer", "entacar", "caraent", "mod", "div",
            ":=", "+", "-", "*", "(",
            ")", "Constantes enteras", "Caracteres", "Cadenas", "Identificadores"
        };
    
    // Constructor por defecto que iniciliza la tabla
    public TablaVeces() {
        for (int i=0; i<N; i++) {
            tabla[i] = 0;
        }
    }
    
    // Estadísticos
    public static void incrementarValor(tipo_token token) {
        tabla[token.ordinal()]++;
    }
    
    // Muestra los estadísticos
    public static void print() {
        String s = " Opción verbose: Número de apariciones de:\n";
        
        for (int i=0; i<N; i++) {
            if (i==0 || i==18 || i==29 || i==33 || i==41) {
                s += "  ---------------------------------------\n";
            }
            s += "  ";
            if (i<=tipo_token.tREF.ordinal()) {
                s += String.format("%-33s", "Palabras reservadas '" + nombres[i] + "': ");
            }
            else if (i>tipo_token.tREF.ordinal() && i<=tipo_token.tFALSE.ordinal()) {
                s += String.format("%-33s", "Operadores lógicos '" + nombres[i] + "': ");
            }
            else if (i>tipo_token.tFALSE.ordinal() && i<=tipo_token.tCARAENT.ordinal()) {
                s += String.format("%-33s", "Operaciones '" + nombres[i] + "': ");
            }
            else if (i>tipo_token.tCARAENT.ordinal() && i<=tipo_token.tCP.ordinal()) {
                s += String.format("%-33s", "Operadores aritméticos '" + nombres[i] + "': ");
            }
            else {
                s += String.format("%-33s", nombres[i] + ": ");
            }
            
            s += String.format("%3s", Integer.toString(tabla[i])) + "\n";
        }
        s += "  ---------------------------------------\n";
        
        System.out.println(s);
    }
}
