package ua.krasnovnikita.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.krasnovnikita.repository.UserRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueUserName constraintAnnotation) {

	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.findByName(userName) == null;
	}

}
