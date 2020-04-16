package mimicweb.manager.timer;

import mimicweb.manager.mapper.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Service
public class ScheduleTimerImpl implements ScheduleTimer  {
    @Autowired
    QueryService queryService;
    @Override
    public String getCron() {
        return queryService.queryLogStrageTime();
    }
    public  String getLogPath(){
        return queryService.queryLogPath();
    }
    public   String getBaseline(){
        return queryService.queryBase();
    }
    public String getCleanStrategy(){
        return queryService.queryStrage();
    }
}
