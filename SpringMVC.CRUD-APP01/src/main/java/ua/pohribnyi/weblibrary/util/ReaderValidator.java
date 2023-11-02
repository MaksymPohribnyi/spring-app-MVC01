package ua.pohribnyi.weblibrary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.pohribnyi.weblibrary.dao.ReaderDAO;
import ua.pohribnyi.weblibrary.model.Reader;

@Component
public class ReaderValidator implements Validator {

	private final ReaderDAO readerDAO;

	@Autowired
	public ReaderValidator(ReaderDAO readerDAO) {
		this.readerDAO = readerDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Reader.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Reader reader = (Reader) target;
		if (readerDAO.show(reader.getName()).isPresent()) {
			errors.rejectValue("name", null, "This Name of reader is not unique");
		}
	}

}
