package mimicweb.manager.mapper.service;

public interface HardWareLogic {
    boolean checkCpu(String elementIP);//cpu处理逻辑
    boolean checkSoftDisk(String elementIP);//系统硬盘处理逻辑
    boolean CheckDataStoreDisk(String elementIP);//数据硬盘处理逻辑
    boolean checkLogDisk(String elementIP);//日志硬盘处理逻辑
    boolean checkBoardCard(String elementIp);//板卡处理逻辑
    boolean checkMemory(String elementIp);//内存处理逻辑
    boolean checkNetWorkStatus(String elementIP);//网口连接状态
    boolean sendToKeepAlive(String elementIp);//通知keepalive
}
