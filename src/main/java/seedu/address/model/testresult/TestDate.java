package seedu.address.model.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a TestResult's test date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTestDate(String)}
 */
public class TestDate {

    public static final String MESSAGE_CONSTRAINTS = "Test Date should not be blank, "
        + "and should be in the format YYYY-MM-DD";

    public final LocalDate date;

    /**
     * Constructs an {@code TestDate}.
     *
     * @param testDate A valid test date.
     */
    public TestDate(String testDate) {
        requireNonNull(testDate);
        checkArgument(isValidTestDate(testDate), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(testDate);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTestDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestDate // instanceof handles nulls
                && date.equals(((TestDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
