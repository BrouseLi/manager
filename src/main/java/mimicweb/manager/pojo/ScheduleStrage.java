package mimicweb.manager.pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ScheduleStrage {
    private String sid;
    private String time;
    private String  ip;
    private String  status;
    private String  strategy;
}
