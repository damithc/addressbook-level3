package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TodoList;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableTodoListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTodoListTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTodoList.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTodoList.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTodoList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTodoList.class).get();
        TodoList addressBookFromFile = dataFromFile.toModelType();
        TodoList typicalPersonsTodoList = TypicalPersons.getTypicalTodoList();
        assertEquals(addressBookFromFile, typicalPersonsTodoList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTodoList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTodoList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTodoList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
