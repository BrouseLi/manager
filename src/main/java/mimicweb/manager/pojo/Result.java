package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    private String scheduleMessage;//调度结果描述
    private String code;//调度响应码
}
