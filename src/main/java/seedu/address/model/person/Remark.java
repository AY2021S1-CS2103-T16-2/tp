package seedu.address.model.person;


import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Remark {

	public final String remarkString;

	public static final String MESSAGE_CONSTRAINTS =
			"Remarks should...[incomplete].";
	public static final String VALIDATION_REGEX = "INCOMPLETE";

	/**
	 * Constructs a {@code Name}.
	 *
	 * @param remarkString A valid name.
	 */
	public Remark(String remarkString) {
		requireAllNonNull(remarkString);
		checkArgument(isValidRemark(remarkString), MESSAGE_CONSTRAINTS);

		this.remarkString = remarkString;
	}

	/**
	 * Returns true if a given string is a valid name.
	 */
	public static boolean isValidRemark(String test) {
		//return test.matches(VALIDATION_REGEX);
		return true;
	}


	@Override
	public String toString() {
		return remarkString;
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof Remark // instanceof handles nulls
				&& remarkString.equals(((Remark) other).remarkString)); // state check
	}

	@Override
	public int hashCode() {
		return remarkString.hashCode();
	}

}
