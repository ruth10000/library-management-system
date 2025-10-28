
class Book extends LibraryItem {
    public Book(int id, String title, int quantity) {
        super(id, title, quantity);
    }

    @Override
    public void displayDetails() {
        System.out.println("Book - " + logString());
    }
}