package exception.customException;

public class BindingException extends RuntimeException {

    private String message;

    public BindingException() {
        super();
    }

    public BindingException(Throwable cause) {
        super(cause);

    }

    public BindingException(String bindingMessage) {
        super(bindingMessage);
    }

    public BindingException(String bindingMessage, Throwable cause) {
        super(bindingMessage, cause);
    }


}
