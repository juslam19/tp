package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.testresult.MedicalTest;
import seedu.address.model.testresult.Result;
import seedu.address.model.testresult.TestDate;
import seedu.address.model.testresult.TestResult;

/**
 * Jackson-friendly version of {@link TestResult}.
 */
class JsonAdaptedTestResult {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Test result's %s field is missing!";

    private final String patientNric;
    private final String testDate;
    private final String medicalTest;
    private final String result;

    /**
     * Constructs a {@code JsonAdaptedTestResult} with the given test result details.
     */
    @JsonCreator
    public JsonAdaptedTestResult(@JsonProperty("patientNric") String patientNric, @JsonProperty("date") String testDate,
                                 @JsonProperty("medicalTest") String medicalTest,
                                 @JsonProperty("result") String result) {

        this.patientNric = patientNric;
        this.testDate = testDate;
        this.medicalTest = medicalTest;
        this.result = result;
    }

    /**
     * Converts a given {@code TestResult} into this class for Jackson use.
     */
    public JsonAdaptedTestResult(TestResult source) {
        patientNric = source.getPatientNric().value;
        testDate = source.getTestDate().toDefaultString();
        medicalTest = source.getMedicalTest().value;
        result = source.getResult().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code TestResult} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted test result.
     */
    public TestResult toModelType() throws IllegalValueException {
        if (patientNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(patientNric)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Nric modelPatientNric = new Nric(patientNric);

        if (testDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TestDate.class.getSimpleName()));
        }
        if (!TestDate.isValidTestDate(testDate)) {
            throw new IllegalValueException(TestDate.MESSAGE_CONSTRAINTS);
        }
        final TestDate modelDate = new TestDate(testDate);

        if (medicalTest == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalTest.class.getSimpleName()));
        }
        if (!MedicalTest.isValidMedicalTest(medicalTest)) {
            throw new IllegalValueException(MedicalTest.MESSAGE_CONSTRAINTS);
        }
        final MedicalTest modelMedicalTest = new MedicalTest(medicalTest);

        if (result == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Result.class.getSimpleName()));
        }
        if (!Result.isValidResult(result)) {
            throw new IllegalValueException(Result.MESSAGE_CONSTRAINTS);
        }
        final Result modelResult = new Result(result);

        return new TestResult(modelPatientNric, modelDate, modelMedicalTest, modelResult);
    }
}
