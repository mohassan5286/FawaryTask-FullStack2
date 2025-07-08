package BookStore ;

import java.time.LocalDate ;

public class PaperBook extends Book {

    private int amount ;
    private double price ;

    public PaperBook(String ISBN, String title, LocalDate yearOfPublication, int amount, double price) {
        super(ISBN, title, yearOfPublication) ;
        this.amount = amount ;
        this.price = price ;
    }

    public int getAmount() {return amount ;}

    public void setAmount(int amount) {
        this.amount = amount ;
    }

    public double getPrice() {
        return price ;
    }

    public void setPrice(double price) {
        this.price = price ;
    }

}
