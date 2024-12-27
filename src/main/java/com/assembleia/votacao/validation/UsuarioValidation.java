package com.assembleia.votacao.validation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.domain.ZipCodeStackLocalAddress;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.service.ZipCodeStackService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
public class UsuarioValidation {

    private ZipCodeStackService zipCodeStackService;


    public String geraSenhaCriptografada(String senha){
        return BCrypt.withDefaults().hashToString(12, senha.toCharArray());
    }


    public ZipCodeStackLocalAddress validaPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isEmpty()) {
            throw new BadRequestException("Postal Code deve estar preenchido.");
        }

        var response = zipCodeStackService.getLocation(postalCode, "BR");
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            throw new BadRequestException("Nenhum local encontrado para o código postal fornecido.");
        }

        List<ZipCodeStackLocalAddress> addresses = response.getResults().get(postalCode);
        if (addresses == null || addresses.isEmpty()) {
            throw new BadRequestException("Nenhum endereço encontrado para o código postal fornecido.");
        }

        return addresses.get(0);
    }


}
