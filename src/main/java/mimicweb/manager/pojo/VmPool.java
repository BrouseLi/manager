package mimicweb.manager.pojo;

import lombok.*;

import java.util.List;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VmPool {
    private String value;
    private String label;
    private List<ExcuteUnit> children;
}
