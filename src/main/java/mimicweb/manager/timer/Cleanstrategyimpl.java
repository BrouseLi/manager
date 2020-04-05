package mimicweb.manager.timer;

import mimicweb.manager.pojo.Disk;
import mimicweb.manager.util.DealFile;
import mimicweb.manager.util.DiskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class Cleanstrategyimpl implements Cleanstrategy {
    private float disks;
    private static final Logger logger = LoggerFactory.getLogger(Cleanstrategyimpl.class);
    @Autowired
    ScheduleTimerimpl scheduleTimer;
    @Override
    public String spaceStrategy() {
        String logpath= scheduleTimer.getLogPath();
        File file =new File(logpath);
        logger.info("获取到日志目录"+logpath);
        List<File> list =new ArrayList<File>();
        logger.info("当前日志所占空间"+getDirspace(getDirfileList(logpath,list),0)/1024+"MB");
        File[] files=file.listFiles();
        long size=0L;
        for (File file1:files) {
            if (file1.isDirectory()){
                size+=getDirspace(getDirfileList(file1.getPath(),new ArrayList()),0);
                if(size>disks){
                    return "空间恢复到安全阈值";
                }else {
                    DealFile.delFolder(file1.getPath());
                    logger.info("已删除文件夹"+file1.getName());
                }
            }
        }
        return null;
    }

    @Override
    public String timeStrategy() {
        System.out.println("==============================按照时间清理 ======================================");
        return null;
    }

    @Override
    public float getSpace() {
        DiskUtil.initDiskFree();
        return DiskUtil.free/1024;
    }
    public static long getDirspace(List<File>list,long lenth){
        for (File file:list) {
            lenth+=file.length();
        }
        return lenth/1024;
    }
    public static List<File> getDirfileList(String path,List<File>list){
        File file =new File(path);
        File[] files=file.listFiles();
        for (File files1:files) {
            if(files1.isDirectory()){
                getDirfileList(files1.getPath(),list);
            }else{
                    list.add(files1);
            }
        }
        return list;
    }
    @Override
    public  void excuteCleanstrategy(){
        logger.info("==============执行了定时任务================");
        float space=getSpace();
        logger.warn("空间剩余"+space/1024/1024/1024+"TB"+"高于4T");
        float base =Float.parseFloat(scheduleTimer.getBaseline());
        float baseline=(base*1024*1024*1024L);
        float disk=space-baseline;
        if(disk<0){
            //空间低于4T，告警
            this.disks=Math.abs(disk);
            logger.warn("空间剩余"+space/1024/1024+"GB"+"低于"+base+"T 准备删除日志");
            String strage=scheduleTimer.getCleanstrage();
            logger.info("=======================获取到清除策略=========="+strage+"=======================================");
            if("1".equals(strage)){
                spaceStrategy();
            }else{
                timeStrategy();
            }
        }
    }


}

