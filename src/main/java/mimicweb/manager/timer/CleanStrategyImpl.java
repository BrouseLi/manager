package mimicweb.manager.timer;


import lombok.extern.slf4j.Slf4j;
import mimicweb.manager.util.DealFile;
import mimicweb.manager.util.DiskUtil;

import mimicweb.manager.util.Excutecommand;
import mimicweb.manager.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CleanStrategyImpl implements CleanStrategy {
    private float disks;
    @Autowired
    ScheduleTimerImpl scheduleTimer;

    @Override
    public String spaceStrategy() {
        String logPath= scheduleTimer.getLogPath();
        File file =new File(logPath);
        log.info("获取到存放日志目录"+logPath);
        List<File> list =new ArrayList<File>();
        File[] files=file.listFiles();
        long size=0L;
        if (files!=null){
            for (File file1:files) {
                if (file1.isDirectory()){
                    size+=getDirSpace(FileUtil.getDirFileList(file1.getPath(),new ArrayList()),0);
                    if(size>disks){
                        return "空间恢复到安全阈值";
                    }else {
                        DealFile.delFolder(file1.getPath());
                    }
                }
            }
        }else {
            log.info("日志所在目录已清空");
            return "日志目录已清空";
        }

        return null;
    }

    @Override
    public float getSpace() {
        DiskUtil.initDiskFree();
        return DiskUtil.free/1024;
    }
    public static long getDirSpace(List<File>list,long lenth){
        for (File file:list) {
            lenth+=file.length();
        }
        return lenth/1024;
    }
    @Override
    public  void cleanStrategy(){
        float space=getSpace();
        float base =Float.parseFloat(scheduleTimer.getBaseline());
        float baseline=(base*1024*1024*1024L);
        float disk=space-baseline;
        if(disk<0){
            //空间低于4T，告警
            this.disks=Math.abs(disk);
            log.warn("空间剩余"+space/1024/1024+"GB"+"低于"+base+"T 准备删除日志");
            spaceStrategy();
        }
    }

    @Override
    public String timeStrategy() {
        String logPath= scheduleTimer.getLogPath();
        return cleanAll(logPath);
    }


    @Override
    public String cleanAll(String logPath) {
        log.info("触法了时间清理，清除全部");
        return Excutecommand.execute("rm -rf "+ logPath+"/*");
    }


}

