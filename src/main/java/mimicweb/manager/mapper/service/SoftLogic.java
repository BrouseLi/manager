package mimicweb.manager.mapper.service;

import java.util.List;

public interface SoftLogic {
    boolean checkMysql(String elementIp);//Mysql处理逻辑
    boolean checkElasticSearch(String elementIp);//elasticSearch 处理逻辑
    boolean checkExecute(List<String> list);// 通知keepalive，gfs数据恢复后，调用恢复执行体状态,
    String  receiveLogDiskMessage(String occupiedSpace, String quota);//接收gfs关于日志配额告警,单位 Mb
    String checkSoftProbe(String elementIp);
    String checkHardWareProbe(String elementIp);
}
