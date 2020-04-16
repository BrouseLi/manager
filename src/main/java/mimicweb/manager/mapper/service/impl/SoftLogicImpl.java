package mimicweb.manager.mapper.service.impl;

import lombok.extern.slf4j.Slf4j;
import mimicweb.manager.mapper.service.HardWareLogic;
import mimicweb.manager.mapper.service.QueryService;
import mimicweb.manager.mapper.service.SoftLogic;
import mimicweb.manager.timer.CleanStrategyImpl;
import mimicweb.manager.util.ElementIPMapperUtil;
import mimicweb.manager.util.SendMessageUtil;
import mimicweb.manager.util.SystemExcute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class SoftLogicImpl implements SoftLogic {
    private static final String token="mimicWebSoftBrain";
    @Autowired
    private HardWareLogic hardWareLogic;
    @Autowired
    QueryService queryService;
    @Autowired
    CleanStrategyImpl cleanStrategy;
    @Override
    public boolean checkMysql(String elementIp) {
        if("success".equals(SendMessageUtil.sendMysqlMessage(elementIp,token))){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkElasticSearch(String elementIp) {
        /**
         * 通知elasticsearch所在服务端，重启
         */

        return false;
    }

    /**
     *
     * @param list 执行体池列表
     * @return
     */
    @Override
    public boolean checkExecute(List<String> list) {
        /**
         * 通知keepalive切换，
         */
        boolean keepAliveFlag=hardWareLogic.sendToKeepAlive(queryService.queryVoterIp(list.get(0)));
        //本地调用 调度器客户端，发送需调度 ip 列表
        String executeIP="all";
        for (String str:list){
            executeIP+=","+str;
        }
        boolean scheduleResult=SystemExcute.schedule("java -jar agent.jar "+executeIP);
        return keepAliveFlag&&scheduleResult;
    }

    @Override
    public String receiveLogDiskMessage(String occupiedSpace, String quota) {
        /**
         * 接收到，日志占用空间，剩余空间。调用日志清理方法
         */
        return cleanStrategy.spaceStrategy();

    }

    @Override
    public String checkSoftProbe(String elementIp) {
        /**
         * 生成管理日志，发送到对应服务端，执行更新恢复操作
         */
        return SendMessageUtil.sendProbeServer(elementIp,token,ElementIPMapperUtil.getElementNameByIp(elementIp)+"softProbe");
    }

    @Override
    public String checkHardWareProbe(String elementIp) {
        /**
         * 上报安管，探针程序需要更新
         */
        return SendMessageUtil.sendProbeServer(elementIp,token,ElementIPMapperUtil.getElementNameByIp(elementIp)+"hardWareProbe");
    }
}
