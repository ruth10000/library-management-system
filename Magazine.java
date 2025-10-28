
  class Magazine extends LibraryItem {
        public Magazine(int id, String title, int quantity) {
            super(id, title, quantity);
        }

        @Override
        public void displayDetails() {
            System.out.println("Magazine - " + logString());
        }
    }

