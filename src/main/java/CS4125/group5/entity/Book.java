package CS4125.group5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long bookId;
    @NotBlank(message = "Name is required")
    private String bookName;
    @NotBlank(message = "Author is required")
    private String author;
    @NotBlank(message = "Genre is required")
    private String genre;
}
