package seedu.address.testutil;

import seedu.address.model.TodoList;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TodoList ab = new TodoListBuilder().withPerson("John", "Doe").build();}
 */
public class TodoListBuilder {

    private TodoList addressBook;

    public TodoListBuilder() {
        addressBook = new TodoList();
    }

    public TodoListBuilder(TodoList addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code TodoList} that we are building.
     */
    public TodoListBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public TodoList build() {
        return addressBook;
    }
}
