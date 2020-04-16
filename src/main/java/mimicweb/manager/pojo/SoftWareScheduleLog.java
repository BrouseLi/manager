package mimicweb.manager.pojo;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SoftWareScheduleLog {
    /**
     * 用于生成软件调度日志
     */
    private String elementName;
    private String softName;
    private String scheduleReason;
    private String time= Date.from(Instant.now()).toString();
}
