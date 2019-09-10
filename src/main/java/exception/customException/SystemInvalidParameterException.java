package exception.customException;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 *
 *服务端参数不合法
 */
public class SystemInvalidParameterException extends RuntimeException {
    private String message;

    public SystemInvalidParameterException() {
        super();
    }

    public SystemInvalidParameterException(Throwable cause) {
        super(cause);

    }

    public SystemInvalidParameterException(String bindingMessage) {
        super(bindingMessage);
    }

    public SystemInvalidParameterException(String bindingMessage, Throwable cause) {
        super(bindingMessage, cause);
    }
}
