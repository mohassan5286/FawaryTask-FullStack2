package BookStore ;

import java.time.LocalDate;

public class DemoBook extends Book {

    public DemoBook(String ISBN, String title, LocalDate yearOfPublication) {
        super(ISBN, title, yearOfPublication) ;
    }

}
