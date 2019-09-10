package exception.customException;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 *
 *客户端参数不合法
 */
public class UserInvalidParameterException extends RuntimeException {
    private String message;

    public UserInvalidParameterException() {
        super();
    }

    public UserInvalidParameterException(Throwable cause) {
        super(cause);

    }

    public UserInvalidParameterException(String bindingMessage) {
        super(bindingMessage);
    }

    public UserInvalidParameterException(String bindingMessage, Throwable cause) {
        super(bindingMessage, cause);
    }
}
