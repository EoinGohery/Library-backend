package CS4125.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {
    private long ratingId;
    private String bookName;
    private String userName;
    private long rating;
}
