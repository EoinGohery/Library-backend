package CS4125.group5.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {
    @Test
    @DisplayName("Test should pass if no bad language is added")
    void containsSwearWords() throws Exception {
        LibraryService libraryService = new LibraryService(null,null,null,null
        );
        Assertions.assertFalse(libraryService.containsSwearWords("Witches"));
    }
    @Test
    @DisplayName("Test should throw exception when exception contains swear words")
    void containsSwearWordsShouldFail() throws Exception {

        LibraryService libraryService = new LibraryService(null,null,null,null
        );
        Exception exception = assertThrows(Exception.class,()->{
        libraryService.containsSwearWords("Shize");
        });
    }
}