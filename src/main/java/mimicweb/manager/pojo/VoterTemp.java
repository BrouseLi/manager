package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoterTemp {
    private String voter_ip;
    private String voter_state;
    private String voter_strategy;
}
