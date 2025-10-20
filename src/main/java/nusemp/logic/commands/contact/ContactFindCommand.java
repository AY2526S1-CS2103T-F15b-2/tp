package nusemp.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import nusemp.commons.util.ToStringBuilder;
import nusemp.logic.Messages;
import nusemp.logic.commands.Command;
import nusemp.logic.commands.CommandResult;
import nusemp.logic.commands.CommandType;
import nusemp.model.Model;
import nusemp.model.contact.Contact;

/**
 * Finds and lists all contacts whose fields contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ContactFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = CommandType.CONTACT + " " + COMMAND_WORD
            + ": Finds all contacts whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... OR --name KEYWORD [MORE_KEYWORDS]... "
            + "[--email KEYWORD [MORE_KEYWORDS]...] [--tag KEYWORD [MORE_KEYWORDS]...]\n"
            + "Examples:\n"
            + "  " + CommandType.CONTACT + " " + COMMAND_WORD + " alice bob charlie\n"
            + "  " + CommandType.CONTACT + " " + COMMAND_WORD + " --name alice\n"
            + "  " + CommandType.CONTACT + " " + COMMAND_WORD + " --email gmail\n"
            + "  " + CommandType.CONTACT + " " + COMMAND_WORD + " --tag friend\n"
            + "  " + CommandType.CONTACT + " " + COMMAND_WORD + " --name alice --email gmail --tag friend";

    private final Predicate<Contact> predicate;

    public ContactFindCommand(Predicate<Contact> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactFindCommand)) {
            return false;
        }

        ContactFindCommand otherContactFindCommand = (ContactFindCommand) other;
        return predicate.equals(otherContactFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
