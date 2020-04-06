package mimicweb.manager.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResult {
    private String uuid;
    private String username;
    private String password;
    private String name;
    private String token;
}
