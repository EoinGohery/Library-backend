package CS4125.group5.repository;


import CS4125.group5.entity.AddedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddedBooksRepository extends JpaRepository<AddedBooks,Long> {
}
