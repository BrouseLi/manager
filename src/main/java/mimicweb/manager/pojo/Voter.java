package mimicweb.manager.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Voter {
    private String ip;
    private String sid;
    private String strategy;
}
