package CS4125.group5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddedBooks {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book book;
}
