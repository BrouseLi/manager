package mimicweb.manager.pojo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogStrage {
    private String strategy;
    private List<String> value;
    private List<Disk> chart;
}
