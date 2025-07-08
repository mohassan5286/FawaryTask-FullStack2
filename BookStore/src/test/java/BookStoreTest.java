import BookStore.* ;

import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.BeforeEach ;
import static org.junit.jupiter.api.Assertions.* ;

import java.time.LocalDate ;

public class BookStoreTest {

    private BookStore store ;
    private Service service ;
    private Inventory invPaper ;

    @BeforeEach
    public void setup() {

        store   = new BookStore() ;
        service = new Service()   ;

        invPaper = new Inventory("INV1", "PaperBook") ;
        Inventory invEBook = new Inventory("INV2", "EBook") ;
        Inventory invDemo  = new Inventory("INV3", "DemoBook") ;

        invPaper.add(new PaperBook("ISBN-001", "Java Design", LocalDate.of(2018, 1, 1), 5, 99.0)) ;
        invPaper.add(new PaperBook("ISBN-002", "Spring Boot", LocalDate.of(2019, 2, 1), 3, 89.0)) ;
        invPaper.add(new PaperBook("ISBN-001", "Java Design", LocalDate.of(2018, 1, 1), 2, 99.0)) ;
        invPaper.add(new PaperBook("ISBN-999", "Outdated Book", LocalDate.of(2000, 1, 1), 1, 50.0));

        invEBook.add(new EBook("ISBN-101", "AI Guide", LocalDate.of(2021, 3, 1), "pdf", 39.99)) ;
        invEBook.add(new EBook("ISBN-102", "Deep Learning", LocalDate.of(2022, 1, 1), "epub", 49.99)) ;

        invDemo.add(new DemoBook("ISBN-201", "Preview Only", LocalDate.of(2000, 1, 1))) ;

        store.add(invPaper) ;
        store.add(invEBook) ;
        store.add(invDemo)  ;

    }

    @Test
    public void testPaperBookQuantity() {
        PaperBook result = (PaperBook) store.getInventories().get(0).getItems().get(0) ;
        assertEquals("ISBN-001", result.getISBN()) ;
        assertEquals(7, result.getAmount()) ;
    }

    @Test
    public void testBuyPaperBookSuccess() throws Exception {
        double paidAmount = service.buyPaperBook(store, "ISBN-001", 3, "user@example.com") ;
        assertEquals(297.0, paidAmount) ;

        PaperBook updated = (PaperBook) store.getInventories().get(0).getItems().get(0) ;
        assertEquals(4, updated.getAmount()) ;
    }

    @Test
    public void testBuyPaperBookInsufficientStock() {
        Exception ex = assertThrows(Exception.class,
                () -> service.buyPaperBook(store, "ISBN-001", 10, "user@example.com")
        ) ;
        assertEquals("You need more than available", ex.getMessage()) ;
    }

    @Test
    public void testBuyPaperBookNotFound() {
        Exception ex = assertThrows(Exception.class,
                () -> service.buyPaperBook(store, "NON-EXISTENT", 1, "user@example.com")
        );
        assertEquals("Book not found", ex.getMessage()) ;
    }

    @Test
    public void testBuyEBookSuccess() throws Exception {
        double paidAmount = service.buyEBook(store, "ISBN-101", "user@example.com");
        assertEquals(39.99, paidAmount) ;
    }

    @Test
    public void testBuyEBookNotFound() {
        Exception ex = assertThrows(Exception.class,
                () -> service.buyEBook(store, "EB999", "user@example.com")
        );
        assertEquals("Book not found", ex.getMessage());
    }
    @Test
    public void testRemoveBookFromInventory() throws Exception {
        invPaper.remove("ISBN-002");
        assertEquals(2, invPaper.getItems().size()) ;
    }
    @Test
    public void testRemoveInventorySuccess() throws Exception {
        store.remove("INV1") ;
        assertEquals(2, store.getInventories().size()) ;
    }
    @Test
    public void testRemoveInventoryNotFound() {
        Exception ex = assertThrows(Exception.class,
                () -> store.remove("INVALID-ID")
        );
        assertEquals("Book not found", ex.getMessage()) ;
    }

    @Test
    public void testRemoveOutdatedBooks() {
        int maxAge = 10 ;
        int currentSize = invPaper.getItems().size() ;

        var removedBooks = invPaper.removeOutdatedBooks(maxAge) ;

        assertEquals(1, removedBooks.size()) ;
        assertEquals("ISBN-999", removedBooks.get(0).getISBN()) ;
        assertEquals(currentSize - 1, invPaper.getItems().size()) ;
    }

}