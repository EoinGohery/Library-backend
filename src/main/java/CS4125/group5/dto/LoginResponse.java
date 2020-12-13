package CS4125.group5.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse implements Dto{
    private String jwt;
    private String username;
    private Instant expiresAt;

    @Override
    public void getInformation() {
        System.out.println("Used to store the users JWT, username and how long until the JWT expires");
    }
}
