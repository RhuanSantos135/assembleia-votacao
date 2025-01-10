package com.assembleia.votacao.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.domain.ZipCodeStackLocalAddress;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.service.ZipCodeStackService;

import java.util.List;
import java.util.Optional;

import static java.util.regex.Pattern.matches;
import static org.apache.logging.log4j.util.Strings.isEmpty;

public class Utils {



    private ZipCodeStackService zipCodeStackService;


    public static String geraSenhaCriptografada(String senha){
        return BCrypt.withDefaults().hashToString(12, senha.toCharArray());
    }

    public ZipCodeStackLocalAddress validaPostalCode(String postalCode) {

        validateRequiredField(postalCode, "Postal Code");

        var response = zipCodeStackService.getLocation(postalCode, "BR");

        ZipCodeStackLocalAddress  address = Optional.ofNullable(response)
                .map(res -> res.getResults().get(postalCode))
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .orElseThrow(()-> new BadRequestException("Postal code não encontrado!"));

        return address;


    }

    public static void validateRequiredField(Object fieldValue, String fieldName) {
        if (fieldValue == null || (fieldValue instanceof String && isEmpty((String) fieldValue))) {
            throw new BadRequestException(fieldName + " is a required field.");
        }
    }

    public static void validateName(String name) {
        if (isEmpty(name)) {
            validateRequiredField(name, "Name");
        }

        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new BadRequestException("Name should only contain letters and spaces.");
        }
    }

    public static void validateEmail(String email) {
        if (isEmpty(email)) {
            validateRequiredField(email, "Email");
        }

        var emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!matches(emailRegex, email)) {
            throw new BadRequestException("Invalid email format.");
        }

        if (!email.endsWith("@gmail.com")) {
            throw new BadRequestException("Unrecognized Gmail Account Access Attempt");
        }
    }

    public static void validatePassword(String password) {
        if (isEmpty(password)) {
            validateRequiredField(password, "Password");
        }

        var passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        if (!matches(passwordRegex, password)) {
            throw new BadRequestException("Password must be at least 8 characters long, contain at least one letter, one number, and one special character.");
        }
    }



}
