package nusemp.model.contact;

import java.util.List;
import java.util.function.Predicate;

import nusemp.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Contact}'s {@code Address} matches any of the keywords given.
 */
public class ContactAddressContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public ContactAddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .anyMatch(keyword -> contact.getAddress().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactAddressContainsKeywordsPredicate)) {
            return false;
        }

        ContactAddressContainsKeywordsPredicate otherAddressContainsKeywordsPredicate =
                (ContactAddressContainsKeywordsPredicate) other;
        return keywords.equals(otherAddressContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}