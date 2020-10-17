package com.emanuel.banco.controller.dto.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.emanuel.banco.controller.dto.PropostaDTO;
import com.emanuel.banco.exceptions.FieldMenssage;
import com.emanuel.banco.repository.PropostaRepository;
import com.emanuel.banco.utils.BR;

public class PropostaInsertValidator implements ConstraintValidator<PropostaInsert, PropostaDTO> {
	
	@Autowired
	PropostaRepository propostaRepository;
	
	List<FieldMenssage> list = null;
 
    @Override
    public void initialize(PropostaInsert constraintAnnotation) {
    }
 
    @Override
    public boolean isValid(PropostaDTO dto, ConstraintValidatorContext context) {
    	 list = new ArrayList<>();
    	
    	if(!BR.isValidCPF(dto.getCpf())) {
    		list.add(new FieldMenssage("cpf", "CPF inválido"));
    	}
    	if(propostaRepository.findByCpf(dto.getCpf()) != null) {
    		list.add(new FieldMenssage("cpf", "CPF já cadastrado"));
		}
    	if(propostaRepository.findByEmail(dto.getEmail()) != null) {
    		list.add(new FieldMenssage("email", "Email já cadastrado"));
    	}
    	
    	LocalDate agora = LocalDate.now();
    	int idade = Period.between(dto.getDataNascimento(), agora).getYears();
    	
    	if(idade < 18) {
    		list.add(new FieldMenssage("dataNascimento", "A idade minima é 18 anos."));
    	}
    	
    	for (FieldMenssage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getField())
					.addConstraintViolation();
    	}
    
    	return list.isEmpty();
    }

}
