package BookStore ;

public class Service {

    public double buyPaperBook(BookStore bookStore, String ISBN, int amount, String mail) throws Exception {
        for (Inventory inventory: bookStore.getInventories()) {
            for (Book item: inventory.getItems()) {
                if (inventory.getType().equals("PaperBook")){
                    if (item.getISBN().equals(ISBN)) {
                        if (((PaperBook) item).getAmount() >= amount) {
                            ((PaperBook) item).setAmount(((PaperBook) item).getAmount() - amount) ;
                            return ((PaperBook) item).getPrice() * amount ;
                        }
                        else {
                            throw new Exception("You need more than available") ;
                        }
                    }
                }
            }
        }
        throw new Exception("Book not found") ;
    }

    public double buyEBook(BookStore bookStore, String ISBN, String mail) throws Exception {
        for (Inventory inventory: bookStore.getInventories()) {
            for (Book item: inventory.getItems()) {
                if (inventory.getType().equals("EBook")){
                    if (item.getISBN().equals(ISBN)) {
                        return ((EBook) item).getPrice() ;
                    }
                }
            }
        }
        throw new Exception("Book not found") ;
    }
}
