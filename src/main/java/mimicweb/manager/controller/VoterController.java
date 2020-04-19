package mimicweb.manager.controller;

import com.alibaba.fastjson.JSON;
import mimicweb.manager.mapper.service.QueryService;
import mimicweb.manager.pojo.ExecuteMessage;
import mimicweb.manager.pojo.Voter;
import mimicweb.manager.pojo.VoterParam;
import mimicweb.manager.pojo.VoterTemp;
import mimicweb.manager.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VoterController {
    @Autowired
    QueryService queryService;
    @RequestMapping("/api/voterman/voter")
    public String replaceVoterStrategy(VoterParam voter) {
        if (StringUtils.isEmpty(voter)){
            return JSON.toJSONString(new ExecuteMessage(401,"参数错误",new ArrayList()));
        }else{
            List<String> list=voter.getIp();
            String strategy=voter.getStrategy();
            if (list.size()<2){
                return JSON.toJSONString(new ExecuteMessage(401,"目标ip为空",new ArrayList()));
            }
            String result1="";
            String result2="";
            for (String ip :list){
                String URL="http://"+ip+":8080/voterStrategy";
                switch (strategy){
                    case "1":
                        result1= SendMessageUtil.sendMessage(URL,"majorityVoting");
                        break;
                    case "2":
                        result2= SendMessageUtil.sendMessage(URL,"consensusVoting");
                        break;
                }
            }
            if (result1.contains("link")||result2.contains("link")){
                return JSON.toJSONString(new ExecuteMessage(401,"策略下发中断",new ArrayList()));
            }else if (!result2.contains("link")&&!"".equals(result2)){
                return JSON.toJSONString(new ExecuteMessage(0,"一致表决策略更新成功",new ArrayList()));
            }else if (!result1.contains("link")&&!"".equals(result1)){
                return JSON.toJSONString(new ExecuteMessage(0,"大数表决策略更新成功",new ArrayList()));
            }else {
               return JSON.toJSONString(new ExecuteMessage(401,"策略格式错误",new ArrayList()));
            }
        }
    }
    @RequestMapping("/api/voterman/voterList")
    public String getVoterList(){
        ExecuteMessage<Voter> executeMessage=new ExecuteMessage<>();
            List<VoterTemp> list = queryService.queryVoterList();
            List<Voter>list1=new ArrayList<>();
            for (VoterTemp voterTemp:list){
                if ("0".equals(voterTemp.getVoter_state())){
                    list1.add(new Voter(voterTemp.getVoter_ip(),"主服务器",voterTemp.getVoter_strategy()));
                }
                if ("1".equals(voterTemp.getVoter_state())){
                    list1.add(new Voter(voterTemp.getVoter_ip(),"主服务器",voterTemp.getVoter_strategy()));
                }
            }
            executeMessage.setData(list1);
            executeMessage.setCode(0);
            executeMessage.setMsg("数据获取成功");

        return JSON.toJSONString(executeMessage);
    }
}
