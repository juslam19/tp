package seedu.address.model.consultation.exceptions;

/**
 * Signals that the operation will result in duplicate Consultations
 * (Consultations are considered duplicates if they have the same
 * identifiers: nric, date, time).
 */
public class DuplicateConsultationException extends RuntimeException {
    public DuplicateConsultationException() {
        super("Operation would result in duplicate consultations");
    }
}
