package mimicweb.manager.mapper.service.impl;

import mimicweb.manager.mapper.service.HardWareLogic;
import mimicweb.manager.mapper.service.Ipmi;
import mimicweb.manager.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HardWareLogicImpl  implements HardWareLogic {
    private static final String token="mimicWebHardWareBrain";
    @Autowired
    Ipmi ipmi;
    @Override
    public boolean checkCpu(String elementIP) {
        ipmi.restartOs(elementIP);
        return sendToKeepAlive(elementIP);
    }

    @Override
    public boolean checkSoftDisk(String elementIP) {
        ipmi.restartOs(elementIP);
        return sendToKeepAlive(elementIP);
    }

    @Override
    public boolean CheckDataStoreDisk(String elementIP) {
        return sendToKeepAlive(elementIP);
    }

    @Override
    public boolean checkLogDisk(String elementIP) {
        return sendToKeepAlive(elementIP);
    }

    @Override
    public boolean checkBoardCard(String elementIp) {
        return sendToKeepAlive(elementIp);
    }

    @Override
    public boolean checkMemory(String elementIp) {
        return sendToKeepAlive(elementIp);
    }

    @Override
    public boolean checkNetWorkStatus(String elementIP) {
        return sendToKeepAlive(elementIP);
    }

    @Override
    public boolean sendToKeepAlive(String elementIp) {
        String result= SendMessageUtil.sendKeepaliveMessage(elementIp,token);
        if(result.contains("link")||"error".equals(result)){
            return false;
        }else{
            return true;
        }
    }
}
