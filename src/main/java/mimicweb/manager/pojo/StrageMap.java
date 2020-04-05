package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StrageMap {
    private String strategy;
    private String sid;
    private String base;
    private String time;
    private String ip;
}
