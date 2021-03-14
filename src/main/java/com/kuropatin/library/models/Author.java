package com.kuropatin.library.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Author {

    /** @final constants define the names of fields in the database */
    public static final String AUTHOR_id = "id";
    public static final String AUTHOR_firstName = "first_name";
    public static final String AUTHOR_lastName = "last_name";
    public static final String AUTHOR_birthDate = "birth_date";
    public static final String AUTHOR_sex = "sex";

    private long id;

    @NotEmpty(message = "First name should not be empty")
    @Size(max = 30, message = "Name should be between 1 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(max = 30, message = "Name should be between 1 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Birth date should not be empty")
    @Pattern(regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", message = "Birth date should be in format dd.mm.yyyy and should be valid")
    private String birthDate;

    @NotEmpty(message = "Specify author's sex")
    private String sex;

    public Author() {

    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }
}