package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class ToggleModeCommand extends Command {

    public static final String COMMAND_WORD = "togglemode";

    @Override
    public CommandResult execute(Model model) {

        return new CommandResult("Toggled app mode");
    }
}