package mimicweb.manager.mapper.service;

import mimicweb.manager.mapper.QueryMapper;
import mimicweb.manager.pojo.ScheduleStrage;
import mimicweb.manager.pojo.StrageMap;
import mimicweb.manager.pojo.TempModel;
import mimicweb.manager.pojo.VoterTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QueryService {
    @Autowired
    private QueryMapper queryMapper;
    public List<TempModel> queryExcute(){
        return queryMapper.select();
    }
    public List<StrageMap> queryLogStrage(){
        return queryMapper.selectLogStrage();
    }
    public List<ScheduleStrage> querySchedule(){
        return queryMapper.selectSchedule();
    }
    public String queryBmcIp(String elementIp){
        return queryMapper.getBmcIp(elementIp);
    }
    public List<VoterTemp> queryVoterList(){
        return  queryMapper.selectVoter();
    }
    public String queryLogStrageTime(){
        return queryMapper.selectLogStrageTime();
    }
    public String queryLogPath(){
        return queryMapper.selectLogPath();
    }
    public  String queryBase(){
        return queryMapper.selectLogBase();
    }
    public String queryStrage(){
        return queryMapper.selectStrage();
    }
    public String queryVoterIp(String eip){
        return queryMapper.getVoterIp(eip);
    }
    public int updateLogStrageByTime(String sid,String time ){
        return queryMapper.updateTime(sid, time);
    }
    public int updateLogStrageByBase(String sid,String base ){
        return queryMapper.updateSpace(base, sid);
    }
    public int startSchedule(String ip){
        return queryMapper.startSchedule(ip);
    }
    public int stopSchedule(String ip){
        return  queryMapper.stopSchedule(ip);
    }
    public int updateScheduleStrage(ScheduleStrage scheduleStrage){
        return queryMapper.replaceScheduleStrage(scheduleStrage);
    }
}
