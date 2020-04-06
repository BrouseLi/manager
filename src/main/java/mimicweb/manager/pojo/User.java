package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String uuid;
    private String name;
    private String token;
}
