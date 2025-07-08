package BookStore;

import java.time.LocalDate;

public class EBook extends Book {

    private String fileType ;
    private double price ;

    public EBook(String ISBN, String title, LocalDate yearOfPublication, String fileType, double price) {
        super(ISBN, title, yearOfPublication) ;
        this.fileType = fileType ;
        this.price = price ;
    }

    public String getFileType() {
        return fileType ;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType ;
    }

    public double getPrice() {
        return price ;
    }

    public void setPrice(double price) {
        this.price = price ;
    }

}