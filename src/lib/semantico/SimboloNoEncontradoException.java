package lib.semantico;

public class SimboloNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public SimboloNoEncontradoException() {
        super();
    }

    public SimboloNoEncontradoException(String message) {
        super(message);
    }
}
