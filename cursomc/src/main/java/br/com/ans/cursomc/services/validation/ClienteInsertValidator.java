package br.com.ans.cursomc.services.validation;

import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.domain.enums.TipoCliente;
import br.com.ans.cursomc.dto.NovoClienteDTO;
import br.com.ans.cursomc.repositories.ClienteRepository;
import br.com.ans.cursomc.resources.exception.FieldMessage;
import br.com.ans.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

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
   @Autowired
   private ClienteRepository clienteRepository;

   public void initialize(ClienteInsert constraint) {
   }

   @Override
   public boolean isValid(NovoClienteDTO novoClienteDTO, ConstraintValidatorContext context) {
      List<FieldMessage> list = new ArrayList<>();

      //Verifica se o CPF é válido.
      if(novoClienteDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(novoClienteDTO.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
      }

      //Verifica se o CNPJ é válido.
      if(novoClienteDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPNJ(novoClienteDTO.getCpfOuCnpj())){
         list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
      }

      Cliente clienteEmail = clienteRepository.findByEmail(novoClienteDTO.getEmail());

      //Verifica se o e-mail inserido já existe.
      if(clienteEmail != null){
         list.add(new FieldMessage("email", "E-mail existente!"));
      }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
                 .addPropertyNode(e.getFieldName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}