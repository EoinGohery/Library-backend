package CS4125.group5.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto implements Dto {
    private long ratingId;
    private String bookName;
    private String userName;
    private long rating;

    @Override
    public void getInformation() {
        System.out.println("Rating is used when you need to add a new rating to the database");
    }
}
