package nusemp.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import nusemp.testutil.ContactBuilder;

public class ContactNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ContactNameContainsKeywordsPredicate firstPredicate = new ContactNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ContactNameContainsKeywordsPredicate secondPredicate = new ContactNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactNameContainsKeywordsPredicate firstPredicateCopy = new ContactNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ContactBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ContactBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ContactBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ContactBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ContactBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ContactBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(keywords);

        String expected = ContactNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}