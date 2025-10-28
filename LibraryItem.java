
import java.io.*;
import java.time.LocalDate;


public abstract class LibraryItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String title;
    protected int quantity;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isLost;

    public LibraryItem(int id, String title, int quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.isLost = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getQuantity() { return quantity; }
    public boolean isLost() { return isLost; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    public boolean isAvailable() {
        return quantity > 0 && !isLost;
    }

    public void borrowItem() throws Exception {
        if (!isAvailable()) throw new Exception("Item not available or marked as lost.");
        quantity--;
        borrowDate = LocalDate.now();
        dueDate = borrowDate.plusDays(14);
    }

    public void returnItem() {
        quantity++;
        if (LocalDate.now().isAfter(dueDate)) {
            isLost = true;
        }
        borrowDate = null;
        dueDate = null;
    }

    public abstract void displayDetails();

    public String logString() {
        return "ID=" + id + ", Title=" + title + ", Qty=" + quantity + (isLost ? " [LOST]" : "");
    }
}
