package exception.customException;

//文件格式不在接受范围异常
public class FileTypeException extends RuntimeException {

    private String message;

    private FileTypeException() {
        super();
    }

    private FileTypeException(Throwable cause) {
        super(cause);

    }

    public FileTypeException(String typeMessage) {
        super(typeMessage);
    }

    public FileTypeException(String typeMessage, Throwable cause) {
        super(typeMessage, cause);
    }


}
