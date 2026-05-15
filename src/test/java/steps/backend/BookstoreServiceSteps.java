package steps.backend;

import actions.backend.AccountActions;
import actions.backend.BookstoreActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookstoreServiceSteps {
    private BookstoreActions bookstoreActions;

    @When("^books are added to account$")
    public void addBooksToAccount() {
        bookstoreActions = new BookstoreActions();
        bookstoreActions.addBooksToAccount();
    }

    @And("book {string} is updated for account")
    public void bookIsUpdatedForAccount(String replacementBook) {
        bookstoreActions.updateBookFromAccount(replacementBook);
    }

    @And("book {string} is deleted from account")
    public void bookIsDeletedFromAccount(String deleteBook) {
        bookstoreActions.deleteBookFromAccount(deleteBook);
    }

    @And("all books are deleted from account")
    public void allBooksAreDeletedFromAccount() {
        bookstoreActions.deleteBooks();
    }
}