package util;


import org.springframework.stereotype.Component;

/**
 * 既然想要实现restful，
 * 那我们要保证每次返回的格式都是相同的，
 * 因此建立了一个ResponseBean来统一返回的格式。
 */

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Component
public class ResponseBean {


    /** 参数有误状态码 **/
    /** 权限不足,或者不允许访问等**/
    public static final int HTTP_CODE_ORBIDDEN=403;
    /** 资源不存在 路径错误等 **/
    public static final int HTTP_CODE_NOT_FOUND=404;
    /** 请求有误 **/
    public static final int HTTP_CODE_BAD_REQUEST=400;
    /** 服务器异常 **/
    public static final int HTTP_CODE_SYSTEM_ERROR=500;
    /** 正确操作 **/
    public static final int HTTP_CODE_OK=200;

    /** 信息 **/
    /** 后台异常 **/
    public static final String MSG_SYSTEM_ERROR_MESSAGE="SystemError";
    /** 请求参数有误 **/
    public static final String MSG_PARAMETER_ERROR_MESSAGE="ParameterError";
    /** 操作成功 **/
    public static final String MSG_SUCCESSFUL_OPERATION_MESSAGE="SuccessfulOperation";
    /** 操作失败 **/
    public static final String MSG_OPERATION_FAILED_MESSAGE ="OperationFailed";


    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(){
        super();
    }


    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setAll(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;

    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String longMessage(String message){
        return "----------------"+"消息:"+"message"+"----------------";
    }

}
