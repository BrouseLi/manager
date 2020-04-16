package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import mimicweb.manager.mapper.service.QueryService;
import mimicweb.manager.pojo.ExecuteMessage;
import mimicweb.manager.pojo.ScheduleStrage;
import mimicweb.manager.pojo.TempModel;
import mimicweb.manager.util.Datadeal;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private QueryService queryService;
    @RequestMapping("/api/sysmon/serverList")
    public String  replaceSchedule(){
        List<TempModel>list=queryService.queryExcute();
        System.out.println(list.toString());
        return JSON.toJSON(Datadeal.dealData(list)).toString();
    }
    @RequestMapping("/api/schedulerman/schedulerList")
    public String getScheduleList(){
        ExecuteMessage<ScheduleStrage> executeMessage=new ExecuteMessage<>();
        List<ScheduleStrage> list = queryService.querySchedule();
        executeMessage.setData(list);
        executeMessage.setCode(0);
        executeMessage.setMsg("获取成功");
        return JSON.toJSONString(executeMessage);
    }
    @RequestMapping("/api/schedulerman/startscheduler")
    public String startSchedule(@RequestParam String ip){
        if (queryService.startSchedule(ip)>0){
            return JSON.toJSON(new ExecuteMessage(0,"启动成功",new ArrayList<>())).toString();
        }else {
            return JSON.toJSON(new ExecuteMessage(1, "启动失败", new ArrayList<>())).toString();
        }
    }
    @RequestMapping("/api/schedulerman/stopscheduler")
    public String stopSchedule(@RequestParam String ip){
        if (queryService.stopSchedule(ip)>0){
            return JSON.toJSON(new ExecuteMessage(0,"关闭成功",new ArrayList<>())).toString();
        }else {
            return JSON.toJSON(new ExecuteMessage(1, "关闭失败", new ArrayList<>())).toString();
        }
    }
    @RequestMapping("/api/schedulerman/schedulerStrategy")
    public String updateScheduleStrage(@RequestBody ScheduleStrage scheduleStrage){
        if(!CronExpression.isValidExpression(scheduleStrage.getTime())){
            return JSON.toJSON(new ExecuteMessage(1, "时间格式不正确", new ArrayList<>())).toString();
        }
        if (queryService.updateScheduleStrage(scheduleStrage)>0){
            return JSON.toJSON(new ExecuteMessage(0,"更新成功",new ArrayList<>())).toString();
        }else {
            return JSON.toJSON(new ExecuteMessage(1, "更新失败", new ArrayList<>())).toString();
        }
    }
}
