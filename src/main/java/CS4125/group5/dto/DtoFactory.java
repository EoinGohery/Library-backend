package CS4125.group5.dto;

import CS4125.group5.repository.AddedBooksRepository;
import CS4125.group5.repository.BookRepository;
import CS4125.group5.repository.RatingRepository;
import CS4125.group5.repository.UserRepository;


public class DtoFactory {
    public Dto getEntity (String entity){
        if(entity == null){
            return null;
        }
        if(entity.equalsIgnoreCase("LOGINREQUEST")){
            return new LoginRequest();

        } else if(entity.equalsIgnoreCase("BOOKREQUEST")){
            return new BookRequest();

        }else if(entity.equalsIgnoreCase("RATINGREQUEST")){
            return new RatingDto();

        }else if(entity.equalsIgnoreCase("LOGINRESPONSE")){
            return new LoginResponse();

        }
        else if(entity.equalsIgnoreCase("REGISTER")){
            return new RegisterRequest();

        }

        return null;
    }
}
