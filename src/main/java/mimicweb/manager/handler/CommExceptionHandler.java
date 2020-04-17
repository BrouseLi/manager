package mimicweb.manager.handler;

import mimicweb.manager.pojo.ExecuteMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@ControllerAdvice
public class CommExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ExecuteMessage handle(Exception e) {
        ExecuteMessage msg = new ExecuteMessage(1, "系统异常，异常原因：" + e.getMessage(),new ArrayList());
        return msg;
    }
}
