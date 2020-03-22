package br.com.ans.cursomc.services.validation;

import br.com.ans.cursomc.domain.enums.TipoCliente;
import br.com.ans.cursomc.dto.NovoClienteDTO;
import br.com.ans.cursomc.resources.exception.FieldMessage;
import br.com.ans.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 22/03/2020
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NovoClienteDTO> {
   public void initialize(ClienteInsert constraint) {
   }

   @Override
   public boolean isValid(NovoClienteDTO novoClienteDTO, ConstraintValidatorContext context) {
      List<FieldMessage> list = new ArrayList<>();

      if(novoClienteDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(novoClienteDTO.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
      }

      if(novoClienteDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPNJ(novoClienteDTO.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
      }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
                 .addPropertyNode(e.getFieldName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}