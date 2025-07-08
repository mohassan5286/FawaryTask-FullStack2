package BookStore;

import java.time.LocalDate;

public class Book {
    private String ISBN ;
    private String title ;
    private LocalDate yearOfPublication ;

    public Book(String ISBN, String title, LocalDate yearOfPublication) {
        this.ISBN  = ISBN ;
        this.title = title ;
        this.yearOfPublication = yearOfPublication ;
    }

    public String getISBN() {
        return ISBN ;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN ;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication ;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication ;
    }

}
