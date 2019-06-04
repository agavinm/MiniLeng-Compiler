package lib.semantico;

import javacc.Token;

public abstract class ErrorSemantico {
    private static boolean correcto = true;
    
    public static boolean getCorrecto() {
        return correcto;
    }

    public static void error_semantico(SimboloNoEncontradoException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <No existe ningún símbolo con nombre '" +
            t.image + "'>");
    }

    public static void error_semantico(SimboloNoAsignableException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <El símbolo con nombre '" +
            t.image + "' no es asignable>");
    }
    
    public static void error_semantico(SimboloYaExistenteException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <Ya existe un símbolo con nombre '" +
            t.image + "'>");
    }

    public static void error_semantico(SimboloNoConcuerdaException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.next.beginLine + ", " + 
            t.next.beginColumn + ">) : <Los tipos no concuerdan>");
    }

    public static void error_semantico(SimboloNoAccionException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <El símbolo con nombre '" +
            t.image + "' no es una acción>");
    }

    public static void error_semantico(ExcesoParametrosException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <Exceso de parámetros>");
    }

    public static void error_semantico(DivisionPorCeroException e, Token t) {
        correcto = false;
        
        System.out.println("ERROR SEMÁNTICO (<" + t.beginLine + ", " + 
            t.beginColumn + ">) : <División por cero>");
    }
}
