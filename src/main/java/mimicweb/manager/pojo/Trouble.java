package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trouble {
    /**
     * 用于生成故障日志
     */
    private String durationTime;//故障持续时间
    private String errorTpe;//故障标签
    private String name;//故障描述
    private String errorTag;//故障
    private String suggestion;//处理建议
    private String sev;//故障分级
    private String srcUserName="admin";//用户名
    private String projectGroupId="007";//厂商id
    private String projectGroup="珠海高凌";//厂商名称
}
