package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TodoList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TodoListStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(TodoListStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TodoList methods ==============================

    @Override
    public Path getTodoListFilePath() {
        return addressBookStorage.getTodoListFilePath();
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException {
        return readTodoList(addressBookStorage.getTodoListFilePath());
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readTodoList(filePath);
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList addressBook) throws IOException {
        saveTodoList(addressBook, addressBookStorage.getTodoListFilePath());
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveTodoList(addressBook, filePath);
    }

}
