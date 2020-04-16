package mimicweb.manager.controller;

import mimicweb.manager.util.SystemExcute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleMysqlController {
    @RequestMapping("/scheduleMysqlService")
    public String scheduleMysqlService(@RequestParam(name = "token")String token){
        if ("mimicWebSoftBrain".equals(token)){
            boolean a=SystemExcute.schedule("systemctl start mysql@bootstrap");
            boolean b=SystemExcute.schedule("systemctl starts mysqld");
            if (a&&b){
                return "success";
            }else {
                return "error";
            }
        }else{
            return "token 值不正确";
        }

    }
}
