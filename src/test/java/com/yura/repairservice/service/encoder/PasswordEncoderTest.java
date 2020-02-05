package com.yura.repairservice.service.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordEncoderTest {
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

    private String plainPassword;
    private String encodedPassword;

    public PasswordEncoderTest(String plainPassword, String encodedPassword) {
        this.plainPassword = plainPassword;
        this.encodedPassword = encodedPassword;
    }

    @Parameters
    public static Collection<Object[]> passwords() {
        return Arrays.asList(new Object[][]{
                {"hello", "5d41402abc4b2a76b9719d911017c592"},
                {"password", "5f4dcc3b5aa765d61d8327deb882cf99"},
                {"java", "93f725a07423fe1c889f448b33d21f46"},
                {"admin", "21232f297a57a5a743894a0e4a801fc3"},
        });
    }

    @Test
    public void encodeShouldEncodePassword() {
        String expected = this.encodedPassword;
        String actual = passwordEncoder.encode(this.plainPassword);

        assertEquals(expected, actual);
    }

}