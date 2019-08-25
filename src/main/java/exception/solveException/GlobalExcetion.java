package exception.solveException;

import exception.customException.BindingException;
import exception.customException.FileTypeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class GlobalExcetion {
    @Resource
    private ResponseBean responseBean;


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean exception(Exception exception) {
        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
        exception.printStackTrace();
        return responseBean;
    }

//    @ExceptionHandler(value = DataIntegrityViolationException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseBean diyDataIntegrityViolationException(Exception exception) {
//        //以null方式插入非null字段,这儿出问题根源：自身逻辑或数据库更改
//        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
//        exception.printStackTrace();
//        return responseBean;
//    }

    @ExceptionHandler(value = BindingException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean bindingException(Exception exception) {
        responseBean.setMsg(exception.getCause().getMessage());
        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        return responseBean;

    }


    @ExceptionHandler(value = FileTypeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean fileTypeException(Exception exception) {
        responseBean.setMsg(exception.getCause().getMessage());
        responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
        return responseBean;
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean iOException(Exception exception) {
        responseBean.setCode(ResponseBean.HTTP_CODE_SYSTEM_ERROR);
        responseBean.setMsg(ResponseBean.MSG_SYSTEM_ERROR_MESSAGE);
        exception.printStackTrace();
        return responseBean;
    }

//    //处理Controller层中基本类型字段,这个异常包括了定义时约束声明不合法 约束条件不合法 组定义不合法 以及违反约束的异常
//    @ExceptionHandler(value = ValidationException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseBean validation(javax.validation.ValidationException exception) {
//        responseBean.setAll(ResponseBean.HTTP_CODE_SYSTEM_ERROR, ResponseBean.MSG_SYSTEM_ERROR_MESSAGE, null);
//
//        exception.printStackTrace();
//
//        return responseBean;
//    }

    //处理Controller层中对象
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean validation(BindException e) {

        List<ObjectError> list = e.getAllErrors();

        StringBuffer errorMessage = new StringBuffer();
        for (ObjectError o : list) {
            Object[] objects=o.getArguments();
            DefaultMessageSourceResolvable defaultMessageSourceResolvable= (DefaultMessageSourceResolvable) objects[0];
            errorMessage.append("字段:").append(defaultMessageSourceResolvable.getDefaultMessage()).append(" ").append(o.getDefaultMessage()).append(" && ");
        }

        int index = errorMessage.indexOf("&&", errorMessage.length() - 3);

        errorMessage.delete(index, errorMessage.length());

        responseBean.setAll(ResponseBean.HTTP_CODE_BAD_REQUEST, errorMessage.toString(), null);

        return responseBean;
    }

    //对于Controller中基本类型违反约束时被捕捉
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean validation2(ConstraintViolationException exception) {
        responseBean.setAll(ResponseBean.HTTP_CODE_BAD_REQUEST, ResponseBean.MSG_PARAMETER_ERROR_MESSAGE, null);

        System.out.println("---------ConstraintViolationException报告违反约束的结果。");
        return responseBean;
    }

}
