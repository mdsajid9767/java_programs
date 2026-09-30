package lld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Book {
    private String name;
    private String isbn;
    private String authorName;
    private Member borrowedBy;

    public Book(String name, String isbn, String authorName) {
        this.name = name;
        this.isbn = isbn;
        this.authorName = authorName;
    }

    public String getName() {
        return name;
    }

    public Member getBorrowedBy() {
        return borrowedBy;
    }

    public boolean setBorrowedBy(Member m) {
        if (borrowedBy == null) {
            this.borrowedBy = m;
            return true;
        }
        return false;
    }

    public String getIsbn() {
        return isbn;
    }

    public void returnBook() {
        this.borrowedBy = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authorName='" + authorName + '\'' +
                ", borrowedBy=" + (borrowedBy != null ? borrowedBy.getName() : "None") +
                '}';
    }
}

class Member {
    private String name;
    private String membershipId;
    private List<Book> books = new ArrayList<>();

    public Member(String name, String membershipId) {
        this.name = name;
        this.membershipId = membershipId;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book borrowedBook = iterator.next();

            if (borrowedBook.equals(book)) {
                iterator.remove();
                return;
            }
        }

        System.out.println("Book not found " + book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", books=" + books +
                '}';
    }
}

class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void borrowBook(Member m, Book b) {
        if (b.setBorrowedBy(m)) {
            m.addBook(b);
            System.out.println(b.getName() + " book borrowed by " + m.getName());
            return;
        }

        System.out.println("Book is not available " + b.getName());
    }

    public void returnBook(Member m, Book b) {
        if (b.getBorrowedBy() != null) {
            b.returnBook();
            m.removeBook(b);
            System.out.println(b.getName() + " book returned by " + m.getName());
            return;
        }

        System.out.println("Book not found " + b.getName());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    Librarian librarian;

    Library(Librarian librarian) {
        books.add(new Book("Java", "J123", "James Gosling"));
        books.add(new Book("C", "C123", "Dennis Ritchie"));
        books.add(new Book("Python", "P123", "Guido van Rossum"));

        this.librarian = librarian;
    }

    void borrowBook(Member m, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                librarian.borrowBook(m, book);
                return;
            }
        }

        System.out.println("Book not found.");
    }

    void returnBook(Member m, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                librarian.returnBook(m, book);
                return;
            }
        }

        System.out.println("Book not found");
    }
}

public class LibrarySystemLLDMain {
    public static void main(String[] args) {
        Member m = new Member("Ankit", "Ankit123");

        Librarian librarian = new Librarian("Rizwan");

        Library library = new Library(librarian);

        library.borrowBook(m, "J123");
        library.returnBook(m, "J123");

    }
}
