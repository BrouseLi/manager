package mimicweb.manager.util;


import mimicweb.manager.enums.ElementIpMapper;

public class ElementIPMapperUtil {
    public static String getElementNameByIp(String ip){
        return ElementIpMapper.fromTypeName(ip).name();
    }
}
