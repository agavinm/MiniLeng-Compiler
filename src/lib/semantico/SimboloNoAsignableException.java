//*****************************************************************
// File:   SimboloNoEncontradoException.java
// Author: Andrés Gavín Murillo 716358
// Date:   22/5/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V3.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.semantico;

public class SimboloNoAsignableException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public SimboloNoAsignableException() {
        super();
    }

    public SimboloNoAsignableException(String message) {
        super(message);
    }
}