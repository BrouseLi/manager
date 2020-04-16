package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceiveTrouble {
    /**
     * 接收实体参数，用于逻辑判断取值
     */
    private String elementIp;//故障节点ip地址
    private String durationTime;//故障持续时间
    private String errorTpe;//故障标签
    private String name;//故障描述
}
