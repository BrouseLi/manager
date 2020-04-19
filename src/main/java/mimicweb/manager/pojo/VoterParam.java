package mimicweb.manager.pojo;

import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VoterParam {
    private List<String> ip;
    private String strategy;
}
