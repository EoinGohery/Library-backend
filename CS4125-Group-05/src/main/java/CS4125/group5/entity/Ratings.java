package CS4125.group5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ratings {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long ratingId;
    @NonNull
    private long rating;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book book;
}
