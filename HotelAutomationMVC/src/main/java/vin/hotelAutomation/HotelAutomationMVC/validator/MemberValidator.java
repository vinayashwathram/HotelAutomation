package vin.hotelAutomation.HotelAutomationMVC.validator;

import org.apache.commons.validator.routines.EmailValidator;

import vin.hotelAutomation.HotelAutomationMVC.model.LoginInfo;
import vin.hotelAutomation.HotelAutomationMVC.model.MemberInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {

	// common-validator library.
	private EmailValidator emailValidator = EmailValidator.getInstance();

	// The classes is supported to Validate
	public boolean supports(Class<?> clazz) {
		return (clazz == MemberInfo.class) || (clazz == LoginInfo.class) ;
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof MemberInfo){
			MemberInfo memberInfo = (MemberInfo) target;

			// Check the fields of memberInfo.
			// (See more in property file: messages/validator.property)
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.memberForm.userName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.memberForm.password");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.memberForm.firstName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.memberForm.lastName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.memberForm.email");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.memberForm.gender");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.memberForm.age");

			if (!emailValidator.isValid(memberInfo.getEmail())) {
				// Error in email field.
				errors.rejectValue("email", "Pattern.memberForm.email");
			}
			
		}
		if(target instanceof LoginInfo){
			//LoginInfo loginInfo = (LoginInfo) target;

			// Check the fields of memberInfo.
			// (See more in property file: messages/validator.property)
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.memberForm.userName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.memberForm.password");
			
		}


	}

}
