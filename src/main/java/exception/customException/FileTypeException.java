package exception.customException;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 *
 * 文件格式不在接受范围内异常
 */
public class FileTypeException extends Exception {

    private String typemessage;

    private FileTypeException() {
        super();
    }

    public FileTypeException(Throwable cause) {
        super(cause);

    }

    public FileTypeException(String supportTypeMes) {
        super(supportTypeMes);
        typemessage=supportTypeMes;
    }

    public FileTypeException(String supportType, Throwable cause) {
        super(supportType, cause);
        typemessage=supportType;
    }

    public String getTypemessage() {
        return typemessage;
    }

    public void setTypemessage(String typemessage) {
        this.typemessage = typemessage;
    }
}
