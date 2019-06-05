//*****************************************************************
// File:   FicheroFormatoException.java
// Author: Andrés Gavín Murillo 716358
// Date:   5/6/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V4.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.genCodigo;

public class FicheroFormatoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public FicheroFormatoException() {
        super();
    }

    public FicheroFormatoException(String message) {
        super(message);
    }
}
