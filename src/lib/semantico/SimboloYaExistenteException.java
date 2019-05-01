package lib.semantico;

public class SimboloYaExistenteException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public SimboloYaExistenteException() {
        super();
    }

    public SimboloYaExistenteException(String message) {
        super(message);
    }
}
