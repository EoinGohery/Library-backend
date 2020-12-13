package CS4125.group5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Dto {
    private String name;
    private String username;
    private String author;
    private String genre;
    private Long id;

    @Override
    public void getInformation() {
        System.out.println("A book request is used when you need to add a new book to the database");
    }
}
