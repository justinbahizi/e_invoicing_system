package justin.bahizi.e_invoicing.exception;

public class InvalidUserDataException extends RuntimeException{

    public InvalidUserDataException(String message) {
        super(message);
    }
}
