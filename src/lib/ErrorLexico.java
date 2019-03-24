//*****************************************************************
// File:   ErrorLexico.java
// Author: Andrés Gavín Murillo 716358
// Date:   24/3/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V1.1
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib;

import javacc.Token;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ErrorLexico {
    public static void generar(Token lastToken, char curChar, FileInputStream file) {
        try {
            // Primera línea y primera columna representadas por 1
            int nLine = lastToken.endLine, nColumn = lastToken.endColumn;
            String line = null, part = null;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            
            for (int l = 0; l < nLine; l++) {
                line = br.readLine();
            }
            
            if (line != null) {
                part = line.substring(nColumn);
                
                if (part != null && part.contains(Character.toString(curChar))) {
                    // El caracter está en la misma línea
                    nColumn = line.indexOf(Character.toString(curChar), nColumn-1) + 1;
                }
                else {
                    // El caracter está en alguna línea más abajo
                    nLine++;
                    line = br.readLine();
                    while (!line.contains(Character.toString(curChar))) {
                        nLine++;
                        line = br.readLine();
                    }
                    
                    nColumn = line.indexOf(Character.toString(curChar), 0) + 1;
                }
                
                System.out.println("ERROR LÉXICO (<" + nLine + ", " + 
                        nColumn + ">) : símbolo no reconocido: <" + curChar + ">");
            }
        }
        catch (Exception e) {
            System.err.println("Error con el fichero de entrada");
        }
    }
}
