package mimicweb.manager.mapper.service.impl;

import lombok.Setter;
import mimicweb.manager.mapper.service.Ipmi;
import org.springframework.stereotype.Service;

@Service
public class IpmiImpl implements Ipmi {
    @Override
    public boolean restartOs(String elementIp) {
        return false;
    }

    @Override
    public boolean updateBandBmcIp(String elementIp, String oldBmcIp, String newBmcIp) {
        return false;
    }

    @Override
    public boolean startOs(String elementIp, String bmcIp) {
        return false;
    }

    @Override
    public boolean shutdownOs(String elementIp, String bmcIp) {
        return false;
    }
}
