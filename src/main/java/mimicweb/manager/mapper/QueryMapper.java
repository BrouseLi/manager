package mimicweb.manager.mapper;

import mimicweb.manager.pojo.ScheduleStrage;
import mimicweb.manager.pojo.StrageMap;
import mimicweb.manager.pojo.TempModel;
import mimicweb.manager.pojo.VoterTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QueryMapper {
    @Select("select pool_ip,label,voter_ip,voter_state from pool")
    List<TempModel> select();
    @Select("select strategy,base,time,ip,sid from logstrage")
    List<StrageMap> selectLogStrage();
    @Select("SELECT voter_ip ,voter_state,voter_strategy FROM pool GROUP BY voter_ip")
    List<VoterTemp> selectVoter();
    @Select("select * from schedule")
    List<ScheduleStrage> selectSchedule();
    @Select("select time from logstrage where sid=\"主日志服务器\"")
    String selectLogStrageTime();
    @Select("select logpath from logstrage where sid=\"主日志服务器\"")
    String selectLogPath();
    @Select("select base from logstrage where sid=\"主日志服务器\"")
    String selectLogBase();
    @Select("select strategy from logstrage where sid=\"主日志服务器\"")
    String selectStrage();
    @Select("select bmcip from ipmapper where vmip=#{vmip}")
    String getBmcIp(String vmip);
    @Select("select voter_ip from pool where pool_ip=" +
            "(" +
            "select vmip from ipmapper where eip=#{eip}" +
            ");")
    String getVoterIp(String eip);
    @Update("update logstrage set time=#{time} where sid=#{sid}")
    int updateTime(String sid,String time);
    @Update("update logstrage set base=#{base} where sid=#{sid}")
    int updateSpace(String base,String sid);
    @Update("update schedule set status=1 where ip=#{ip}")
    int startSchedule(String ip);
    @Update("update schedule set status=0 where ip=#{ip}")
    int stopSchedule(String ip);
    @Update("update schedule set time=#{time},strategy=#{strategy} where ip=#{ip}")
    int replaceScheduleStrage(ScheduleStrage scheduleStrage);
    //数据库连接中断
}
