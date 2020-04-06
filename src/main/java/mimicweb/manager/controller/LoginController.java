package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import mimicweb.manager.pojo.ExecuteMessage;
import mimicweb.manager.pojo.LoginResult;
import mimicweb.manager.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @RequestMapping("/api/login")
    public String login(@RequestBody User user){
        ExecuteMessage<LoginResult> executeMessage=new ExecuteMessage();
        if("admin".equals(user.getUsername())&&"admin".equals(user.getPassword())){
            executeMessage.setCode(0);
            executeMessage.setMsg("登陆成功");
            List<LoginResult> list=new ArrayList<>();
            list.add(new LoginResult("admin-uuid","admin","admin","Admin","8dfhassad0asdjwoeiruty"));
            executeMessage.setData(list);
        }else{
            executeMessage.setCode(401);
            executeMessage.setMsg("用户名或密码错误");
        }
        return JSON.toJSONString(executeMessage);
    }
}
