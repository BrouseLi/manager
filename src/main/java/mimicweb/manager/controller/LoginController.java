package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import mimicweb.manager.pojo.ExecuteMessage;
import mimicweb.manager.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @RequestMapping("/api/login")
    public String login(@RequestParam(name = "username")String username,@RequestParam(name="password") String password){
        ExecuteMessage<User> executeMessage=new ExecuteMessage();
        if("admin".equals(username)&&"admin".equals("password")){
            executeMessage.setCode(0);
            executeMessage.setMsg("登陆成功");
            List<User> list=new ArrayList<>();
            list.add(new User("admin-uuid",username,"8dfhassad0asdjwoeiruty"));
            executeMessage.setData(list);
        }else{
            executeMessage.setCode(401);
            executeMessage.setMsg("用户名或密码错误");
        }
        return JSON.toJSONString(executeMessage);
    }
}
