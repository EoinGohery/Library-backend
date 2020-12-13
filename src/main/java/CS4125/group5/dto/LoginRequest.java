package CS4125.group5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Dto {
    private String username;
    private String password;

    @Override
    public void getInformation() {
        System.out.println("A Login request is used to pass username and password information");
    }
}