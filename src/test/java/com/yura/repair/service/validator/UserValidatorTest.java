package com.yura.repair.service.validator;

import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.exception.InvalidUserParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorTest {
    private final Validator<UserDto> userValidator = new UserValidator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidParameterExceptionForNullUser() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("User is null");

        userValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidName() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Name is not correct");

        userValidator.validate(UserDto.builder()
                .withName("1")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidSurname() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Surname is not correct");

        userValidator.validate(UserDto.builder()
                .withName("Yura")
                .withSurname("1")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidEmail() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Email is not correct");

        userValidator.validate(UserDto.builder()
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("@com.com")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidPassword() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Password is not correct");

        userValidator.validate(UserDto.builder()
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("yura@gmail.com")
                .withPassword("1123456")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidPhone() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Phone is not correct");

        userValidator.validate(UserDto.builder()
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("yura@gmail.com")
                .withPassword("12345678")
                .withPhone("123")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        userValidator.validate(UserDto.builder()
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("yura@gmail.com")
                .withPassword("12345678")
                .withPhone("0501918025")
                .build());
    }

}