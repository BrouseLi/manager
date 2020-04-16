package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import mimicweb.manager.mapper.service.QueryService;
import mimicweb.manager.pojo.*;
import mimicweb.manager.util.Datadeal;
import mimicweb.manager.util.DiskUtil;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LogManagerController {
    @Autowired
    private QueryService queryService;
    @RequestMapping("/api/logman/severList")
    public String getLogMesg(){
        List<TempModel>list=queryService.queryExcute();
        return JSON.toJSON(Datadeal.dealLogData(list)).toString();
    }
    @RequestMapping("/api/logman/severData")
    public String getLogstatus(@RequestParam(name = "sid") String sid){
        ExecuteMessage<LogStrage> executeMessage=new ExecuteMessage<>();
        executeMessage.setCode(0);
        executeMessage.setMsg("返回信息");
        LogStrage logStrage=new LogStrage();
        List<StrageMap>logStrategyList=queryService.queryLogStrage();
        StrageMap strategyMap=logStrategyList.get(0);
        StrageMap strategyMap1=logStrategyList.get(1);
        List<String>listValue=new ArrayList<>();
        if(strategyMap.getSid().equals(sid)){
            listValue.add(strategyMap.getTime());
            listValue.add(strategyMap.getBase());
            logStrage.setStrategy(strategyMap.getStrategy());
            logStrage.setValue(listValue);
        }else{
            listValue.add(strategyMap1.getTime());
            listValue.add(strategyMap1.getBase());
            logStrage.setStrategy(strategyMap1.getStrategy());
            logStrage.setValue(listValue);
        }
        List<Disk>list=new ArrayList<>();
        DiskUtil.initDiskFree();
        list.add(new Disk("磁盘占用",String.valueOf((DiskUtil.total-DiskUtil.free)/1024/1024/1024)));
        list.add(new Disk("磁盘剩余",String.valueOf(DiskUtil.free/1024/1024/1024)));
        logStrage.setChart(list);
        return JSON.toJSONString(logStrage);
    }
    @RequestMapping("/api/logman/logStrategy")
    public String updateLogStrage(@RequestBody  StrageMap strageMap){
        ExecuteMessage executeMessage=new ExecuteMessage();
        int base=0;
        String time=strageMap.getTime();
            if("".equals(time)){
                try{
                    base=Integer.parseInt(strageMap.getBase());
                }catch (Exception e){
                    executeMessage.setCode(1);
                    executeMessage.setMsg("base 格式转化错误要求为数字");
                }
                int a = queryService.updateLogStrageByBase(strageMap.getSid(),String.valueOf(base));
                if(a>0){
                    executeMessage.setMsg("更新成功");
                    executeMessage.setCode(0);
                }
            }else{
                if(!CronExpression.isValidExpression(strageMap.getTime())){
                    executeMessage.setCode(1);
                    executeMessage.setMsg("时间格式不正确");
                    return JSON.toJSON(executeMessage).toString();
                }
                int a=queryService.updateLogStrageByTime(strageMap.getSid(),strageMap.getTime());
                if(a>0){
                    executeMessage.setCode(0);
                    executeMessage.setMsg("更新成功");
                }
            }
        executeMessage.setMsg("参数值为空");
        executeMessage.setMsg("1");
        return JSON.toJSON(executeMessage).toString();
    }
}
