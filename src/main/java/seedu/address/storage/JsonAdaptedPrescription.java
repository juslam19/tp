package seedu.address.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;

public class JsonAdaptedPrescription {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Prescription's %s field is missing!";

    private final String nric;
    private final String drugName;
    private final String date;
    private final String instruction;
    /**
     * Constructs a {@code JsonAdaptedPrescription} with the given prescription details.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("nric") String nric, @JsonProperty("drugname") String drugName,
                              @JsonProperty("date") String date, @JsonProperty("instruction") String instruction) {

        this.nric = nric;
        this.drugName = drugName;
        this.date = date;
        this.instruction = instruction;
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        nric = source.getPrescriptionTarget().value;
        drugName = source.getDrugName().drugName;
        date = source.getPrescriptionDate().date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        instruction = source.getInstruction().value;
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Prescription} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted prescription.
     */
    public Prescription toModelType() throws IllegalValueException {

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (drugName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DrugName.class.getSimpleName()));
        }
        if (!DrugName.isValidName(drugName)) {
            throw new IllegalValueException(DrugName.MESSAGE_CONSTRAINTS);
        }
        final DrugName modelDrugName = new DrugName(drugName);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PrescriptionDate.class.getSimpleName()));
        }
        if (!PrescriptionDate.isValidDate(date)) {
            throw new IllegalValueException(PrescriptionDate.MESSAGE_CONSTRAINTS);
        }
        final PrescriptionDate modelDate = new PrescriptionDate(date);

        if (instruction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Instruction.class.getSimpleName()));
        }
        if (!Instruction.isValidInstruction(instruction)) {
            throw new IllegalValueException(Instruction.MESSAGE_CONSTRAINTS);
        }
        final Instruction modelInstruction = new Instruction(instruction);

        return new Prescription(modelNric, modelDrugName, modelDate, modelInstruction);
    }
}
