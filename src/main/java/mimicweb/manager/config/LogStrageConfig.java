package mimicweb.manager.config;

import lombok.extern.slf4j.Slf4j;
import mimicweb.manager.timer.Cleanstrategy;
import mimicweb.manager.timer.ScheduleTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
@Slf4j
public class LogStrageConfig implements SchedulingConfigurer
{
    @Autowired
    private ScheduleTimer scheduleTimer;
    @Autowired
    private Cleanstrategy cleanstrategy;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> cleanstrategy.excuteCleanstrategy(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1从配置文件获取执行周期
                    String cron = scheduleTimer.getscon();
                    log.info("获取到定时"+cron+"===================");
                    //2.2 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
