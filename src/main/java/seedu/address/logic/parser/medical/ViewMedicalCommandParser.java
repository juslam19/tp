package seedu.address.logic.parser.medical;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.medical.ViewMedicalCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;



public class ViewMedicalCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewMedicalCommand
     * and returns an AddMedicalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMedicalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewMedicalCommand.MESSAGE_USAGE));
        }

        Nric nric = null;

        if (arePrefixesPresent(argMultimap, PREFIX_NRIC)) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        }

        return new ViewMedicalCommand(nric);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}