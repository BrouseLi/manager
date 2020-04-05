package mimicweb.manager.util;


import mimicweb.manager.pojo.*;

import java.util.ArrayList;
import java.util.List;


public class Datadeal {
    public static ExecuteMessage<VmPool> dealData(List<TempModel> list){
        List<VmPool>list1=new ArrayList<>();
        ExecuteMessage<VmPool> executeMessage=new ExecuteMessage<>();
        VmPool vmPool=new VmPool();
        VmPool vmPool1=new VmPool();
        List<ExcuteUnit>list2=new ArrayList<>();
        List<ExcuteUnit>list3=new ArrayList<>();
        for (TempModel temp:list){
            executeMessage.setCode(0);
            executeMessage.setMsg("返回信息");
            if (temp.getVoter_state().equals("0")){
                vmPool.setLabel("主服务器");
                vmPool.setValue(temp.getVoter_ip());
                if (temp.getLabel().equals("4")){
                    ExcuteUnit excuteUnit=new ExcuteUnit("日志服务器1",temp.getPool_ip());
                    list2.add(excuteUnit);
                }else{
                    ExcuteUnit excuteUnit=new ExcuteUnit("执行体单元 "+temp.getLabel(),temp.getPool_ip());
                    list2.add(excuteUnit);
                }
            }else{
                vmPool1.setLabel("备服务器");
                vmPool1.setValue(temp.getVoter_ip());
                if (temp.getLabel().equals("5")){
                    ExcuteUnit excuteUnit=new ExcuteUnit("日志服务器2",temp.getPool_ip());
                    list3.add(excuteUnit);
                }else{
                    ExcuteUnit excuteUnit=new ExcuteUnit("执行体单元 "+temp.getLabel(),temp.getPool_ip());
                    list3.add(excuteUnit);
                }
            }
        }
        vmPool.setChildren(list2);
        vmPool1.setChildren(list3);
        list1.add(vmPool);
        list1.add(vmPool1);
        executeMessage.setData(list1);
        return executeMessage;
    }
    public static ExecuteMessage<LogExcuter> dealLogData(List<TempModel> list){
        List<LogExcuter>list1=new ArrayList<>();
        ExecuteMessage<LogExcuter> executeMessage=new ExecuteMessage<>();
        for(TempModel temp:list){
            if (temp.getLabel().equals("4")){
                executeMessage.setCode(0);
                executeMessage.setMsg("返回数据");
                list1.add(new LogExcuter("主日志服务器",temp.getPool_ip()));
            }else if(temp.getLabel().equals("5")){
                list1.add(new LogExcuter("备日志服务器",temp.getPool_ip()));
            }
        }
        executeMessage.setData(list1);
        return executeMessage;
    }
}
