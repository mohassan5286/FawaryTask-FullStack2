package BookStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final String ID ;
    private final String type ;
    private final List<Book> items = new ArrayList<>() ;

    public Inventory(String ID, String type) {
        this.ID = ID ;
        this.type = type ;
    }

    public String getType() {
        return type ;
    }

    public String getID() {
        return ID ;
    }

    public List<Book> getItems() {
        return items ;
    }

    public void add(Book book) {
        for(Book item : this.items) {
            if(item.getISBN().equals(book.getISBN())) {
                if(this.type.equals("PaperBook")) {
                    ((PaperBook) item).setAmount(((PaperBook) item).getAmount() + ((PaperBook) book).getAmount()) ;
                }
                return ;
            }
        }
        this.items.add(book) ;
    }

    public void remove(String ISBN) throws Exception {
        for (Book item: this.items) {
            if (item.getISBN().equals(ISBN)){
                this.items.remove(item) ;
                return ;
            }
        }
        throw new Exception("Book not found") ;
    }

    public List<Book> removeOutdatedBooks(int maxAge) {
        List<Book> removed = new ArrayList<>() ;
        int currentYear = LocalDate.now().getYear() ;

        for (Book item : new ArrayList<>(this.items)) {
            if ((currentYear - item.getYearOfPublication().getYear()) > maxAge) {
                this.items.remove(item) ;
                removed.add(item) ;
            }
        }
        return removed ;
    }
}
