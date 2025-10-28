
import java.io.Serializable;
import java.time.LocalDate;


class Member implements Serializable {
    private static final long serialVersionUID = 2L;

    private final int id;
    private final String name;
    private final String password;
    private LibraryItem borrowedItem;

    public Member(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public boolean authenticate(String input) {
        return password.equals(input);
    }

    public void borrowItem(LibraryItem item) throws Exception {
        if (borrowedItem != null) throw new Exception("You already borrowed an item.");
        item.borrowItem();
        borrowedItem = item;
        LibraryManagementSystem.log(name + " borrowed: " + item.logString() + " | Due: " + item.getDueDate());
    }

    public void returnItem() throws Exception {
        if (borrowedItem == null) throw new Exception("You have not borrowed any item.");
        boolean isLate = LocalDate.now().isAfter(borrowedItem.getDueDate());
        borrowedItem.returnItem();
        if (isLate) {
            LibraryManagementSystem.log(name + " returned LATE: " + borrowedItem.logString() + " - Marked LOST");
        } else {
            LibraryManagementSystem.log(name + " returned: " + borrowedItem.logString());
        }
        borrowedItem = null;
    }

    public void displayBorrowed() {
        if (borrowedItem != null) {
            System.out.println("Borrowed: Title = " + borrowedItem.getTitle() + ", Quantity = 1");
        } else {
            System.out.println("No borrowed item.");
        }
    }
}

