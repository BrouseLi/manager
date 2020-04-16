package mimicweb.manager.pojo;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HardWareTroubleLog {
    private String elementName;
    private String HardWareName;
    private String Reason;
    private String time= Date.from(Instant.now()).toString();
}
