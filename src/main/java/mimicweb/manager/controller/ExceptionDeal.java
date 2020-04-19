package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import lombok.extern.slf4j.Slf4j;
import mimicweb.manager.pojo.ExecuteMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.ConnectException;

@Slf4j
@RestControllerAdvice
public class ExceptionDeal {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ConnectException.class)
    public String handleConnectException(ConnectException e)
    {
        log.info("handleConnectException");
        ExecuteMessage executeMessage=new ExecuteMessage();
        executeMessage.setMsg("数据库连接异常");
        executeMessage.setCode(401);
        return JSON.toJSONString(executeMessage);
    }
    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException e)
    {
        log.info("handleIOException");
        ExecuteMessage executeMessage=new ExecuteMessage();
        executeMessage.setMsg("文件不存在"+e.getLocalizedMessage());
        executeMessage.setCode(401);
        return JSON.toJSONString(executeMessage);
    }
}
