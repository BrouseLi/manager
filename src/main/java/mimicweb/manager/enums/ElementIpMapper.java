package mimicweb.manager.enums;

import lombok.Getter;

@Getter
public enum ElementIpMapper {
    Arm执行体单元1("192.168.1.11"),
    Arm执行体单元2("192.168.1.12"),
    X86执行体单元1("192.168.1.13"),
    X86执行体单元2("192.168.1.14"),
    X86执行体单元3("192.168.1.15"),
    X86执行体单元4("192.168.1.16"),
    主数据中心("192.168.1.17"),
    备数据中心("192.168.1.18"),
    申威主("192.168.1.19"),
    申威备("192.168.1.20");
    private String name;
    public static ElementIpMapper fromTypeName(String elementName) {
        for (ElementIpMapper ipMapper : ElementIpMapper.values()) {
            if (ipMapper.getName().equals(elementName)) {
                return ipMapper;
            }
        }
        return null;
    }
    private ElementIpMapper(String name) {
        this.name=name;
    }

}
