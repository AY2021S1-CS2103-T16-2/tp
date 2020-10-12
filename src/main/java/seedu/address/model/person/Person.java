package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the client list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be non-null. Only name needs to be present.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * If one of phone or email is null for both self and other person, check the other field instead.
     * If both of phone and email are null for both self and other person, return true.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        // If both of phone and email are null for both self and other person, return true.
        if (otherPerson != null
                && otherPerson.getName().equals(getName())
                && getPhone() == null && otherPerson.getPhone() == null
                && getEmail() == null && otherPerson.getEmail() == null) {
            return true;
        }

        // If one of phone or email is null for both self and other person, check the other field instead.
        // If at least one of phone or email are the same and non-null, return true,
        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (isSameNullable(otherPerson.getPhone(), getPhone())
                || isSameNullable(otherPerson.getEmail(), getEmail()));
    }

    /**
     * Returns true if both objects are non-null, and are equal to each other.
     * This is a utility method used by isSamePerson to handle for nulls.
     * If either objects are null, false is returned, as the other identity field
     * would be used to check for "sameness" instead.
     *
     * @param obj First object to test for is same.
     * @param otherObj Second object to test for is same.
     * @return Boolean representing if 2 objects are the same.
     */
    private boolean isSameNullable(Object obj, Object otherObj) {
        return obj != null
                && obj.equals(otherObj);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        return otherPerson.getName().equals(getName())
                && equalsNullable(otherPerson.getPhone(), getPhone())
                && equalsNullable(otherPerson.getEmail(), getEmail())
                && equalsNullable(otherPerson.getAddress(), getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    private boolean equalsNullable(Object obj, Object otherObj) {
        if (obj == null) {
            return otherObj == null;
        } else {
            return obj.equals(otherObj);
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getName());
        if (getPhone() != null) {
            builder.append(" Phone: ")
                    .append(getPhone());
        }
        if (getEmail() != null) {
            builder.append(" Email: ")
                    .append(getEmail());
        }
        if (getAddress() != null) {
            builder.append(" Address: ")
                    .append(getAddress());
        }
        if (getTags().size() > 0) {
            builder.append(" Tags: ");
            getTags().forEach(builder::append);
        }

        return builder.toString();
    }

}

