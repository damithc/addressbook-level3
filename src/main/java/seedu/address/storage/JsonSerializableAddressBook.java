package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.person.Person;

/**
 * An Immutable TodoList that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTodoList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTodoList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTodoList(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTodoList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTodoList}.
     */
    public JsonSerializableTodoList(ReadOnlyTodoList source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TodoList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TodoList toModelType() throws IllegalValueException {
        TodoList addressBook = new TodoList();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
