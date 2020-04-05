package mimicweb.manager.pojo;

import lombok.*;

import java.util.List;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteMessage<T> {
    private Integer code;
    private String msg;
    private List<T> data;
}
