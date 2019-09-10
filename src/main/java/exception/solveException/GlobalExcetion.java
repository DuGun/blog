package exception.solveException;

import exception.customException.FileTypeException;
import exception.customException.SystemInvalidParameterException;
import exception.customException.UserInvalidParameterException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 * <p>
 * MVC中全局处理异常
 */
@ControllerAdvice
public class GlobalExcetion {

    @Resource
    private ResponseBean responseBean;


    //**********************以下对于其他异常处理**********************

    /**
     * 处理所上传的文件总MB大于所承载MB
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean wrongParameterException(MaxUploadSizeExceededException exception) {
        responseBean.setMsg("文件过大,不支持上传");
        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        responseBean.setData(null);
        return responseBean;
    }


    //**********************以下对于无法处理的异常**********************

    /**
     * 处理因IO的异常
     */
    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean iOException(Exception exception) {
        responseBean.setCode(ResponseBean.HTTP_CODE_SYSTEM_ERROR);
        responseBean.setMsg(ResponseBean.MSG_SYSTEM_ERROR_MESSAGE);
        responseBean.setData(null);
        exception.printStackTrace();
        return responseBean;
    }

    /**
     * 处理所有没被处理的异常，归纳于服务器异常
     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseBean exception(Exception exception) {
//        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
//        exception.printStackTrace();
//        return responseBean;
//    }


    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean runtimeException(RuntimeException exception) {
        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
        exception.printStackTrace();
        return responseBean;
    }


    //**********************以下对于数据库异常处理**********************

    /**
     * 处理插入数据库唯一值字段出现重复异常
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean wrongParameterException(DuplicateKeyException exception) {
        responseBean.setData(null);
        responseBean.setMsg(ResponseBean.MSG_SYSTEM_ERROR_MESSAGE);
        responseBean.setCode(ResponseBean.HTTP_CODE_SYSTEM_ERROR);
        return responseBean;
    }


    //**********************以下对于自定义异常处理**********************

    /**
     * 处理用户参数不合法异常
     */
    @ExceptionHandler(value = UserInvalidParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean userInvalidParameterException(UserInvalidParameterException exception) {
        responseBean.setMsg(exception.getMessage());
        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 处理服务端参数不合法异常
     */
    @ExceptionHandler(value = SystemInvalidParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean systemInvalidParameterException(SystemInvalidParameterException exception) {
        responseBean.setMsg(exception.getMessage());
        responseBean.setCode(ResponseBean.HTTP_CODE_SYSTEM_ERROR);
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 处理文件格式不在接受范围之内异常
     */
    @ExceptionHandler(value = FileTypeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean fileTypeException(FileTypeException exception) {
        responseBean.setMsg(exception.getTypemessage());
        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        responseBean.setData(null);
        return responseBean;
    }


    //**********************以下为校验框架异常的处理**********************

    /**
     * 处理校验框架中对于基本类型校验不符合的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean constraintViolationException(ConstraintViolationException exception) {

        StringBuffer errorMessage = new StringBuffer();

        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();

        for (ConstraintViolation<?> constraintViolation : set) {
            String message = constraintViolation.getMessage();
            errorMessage.append(message).append(" && ");
        }

        int index = errorMessage.indexOf("&&", errorMessage.length() - 3);

        errorMessage.delete(index, errorMessage.length());

        responseBean.setAll(ResponseBean.HTTP_CODE_BAD_REQUEST, errorMessage.toString(), null);

        return responseBean;
    }

    /**
     * 处理校验框架中--Controller层中对象不符合校验的异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean bindException(BindException e) {

        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        responseBean.setData(null);
        if(e.hasErrors()){
            List<ObjectError> list=e.getAllErrors();
            for(ObjectError error:list){
                System.out.println(error.getDefaultMessage());
                responseBean.setMsg(error.getDefaultMessage());
                return  responseBean;
            }
        }



        return responseBean;
    }

    /**
     * 处理校验框架中--Controller层中对象不符合校验的异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean bindException(MethodArgumentNotValidException e) {



       FieldError fieldError= e.getBindingResult().getFieldError();

       responseBean.setMsg(fieldError.getDefaultMessage());

       responseBean.setData(null);

       responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);


        return responseBean;
    }



    /**
     * 处理验框架中定义时约束声明不合法 约束条件不合法 组定义不合法 以及违反约束的异常
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean validationException(ValidationException exception) {
        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
        exception.printStackTrace();
        return responseBean;
    }

}
