package mimicweb.manager.timer;
public interface Cleanstrategy {
    //保留最后NGB的日志
    String spaceStrategy();
    //删除n天之前的日志
    String timeStrategy();
    //获取磁盘所剩空间
    float getSpace();
    void excuteCleanstrategy();
}
