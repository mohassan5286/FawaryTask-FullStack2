package BookStore;

import java.util.ArrayList;
import java.util.List;

public class BookStore {

    private final List<Inventory> inventories = new ArrayList<>() ;

    public List<Inventory> getInventories() {
        return inventories ;
    }

    public void add(Inventory inventory) {
        this.inventories.add(inventory) ;
    }

    public void remove(String ID) throws Exception {
        for (Inventory inventory: this.inventories) {
            if (inventory.getID().equals(ID)){
                this.inventories.remove(inventory) ;
                return ;
            }
        }
        throw new Exception("Book not found") ;
    }

}
