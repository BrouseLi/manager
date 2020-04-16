package mimicweb.manager.controller;

import mimicweb.manager.mapper.service.impl.HardWareLogicImpl;
import mimicweb.manager.mapper.service.impl.SoftLogicImpl;
import mimicweb.manager.pojo.ReceiveTrouble;
import mimicweb.manager.pojo.Trouble;
import mimicweb.manager.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReceiveTroubleController {
    @Autowired
    private HardWareLogicImpl hardWareLogic;
    @Autowired
    private SoftLogicImpl softLogic;
    @RequestMapping("/receiveMessage")
    public String receiveMessage(@RequestBody ReceiveTrouble receiveTrouble){
        String ip= receiveTrouble.getElementIp();
        Trouble trouble=new Trouble();
        trouble.setDurationTime(receiveTrouble.getDurationTime());
        trouble.setErrorTag(receiveTrouble.getErrorTpe());
        trouble.setName(receiveTrouble.getName());
        /**
         * 生成故障日志
         */
        String fileContent="";
        /**
         * 组装告警信息
         */

        fileContent+= receiveTrouble.getDurationTime()+"|"+ receiveTrouble.getErrorTpe()+"|"+ receiveTrouble.getName();
        FileUtil.writeFile("/User/liang/test/trouble.log",fileContent.getBytes());
        switch (receiveTrouble.getErrorTpe()){
            case "硬件":

                break;
            case "软件":
                break;
            case "数据":
                break;
            case "网络性能":
                break;

        }
        return "success";
    }
}
