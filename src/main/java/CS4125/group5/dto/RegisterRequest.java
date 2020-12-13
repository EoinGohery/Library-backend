package CS4125.group5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Dto{
    String email;
    String username;
    String password;

    @Override
    public void getInformation() {
        System.out.println("Register request is used to pass new user info to database");
    }
}