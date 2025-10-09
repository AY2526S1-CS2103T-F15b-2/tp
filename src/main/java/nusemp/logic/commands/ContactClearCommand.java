package nusemp.logic.commands;

import static java.util.Objects.requireNonNull;

import nusemp.model.AddressBook;
import nusemp.model.Model;

/**
 * Clears the address book.
 */
public class ContactClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
