package mimicweb.manager.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TempModel {
    private String pool_ip;
    private String label;
    private String voter_ip;
    private String voter_state;
}
