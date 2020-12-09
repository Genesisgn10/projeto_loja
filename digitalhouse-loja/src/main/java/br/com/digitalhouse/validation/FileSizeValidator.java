package br.com.digitalhouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public void initialize(FileSize atributos) {
		this.maxSize = DataSize.parse(atributos.max());
	}
	
	@Override
	public boolean isValid(MultipartFile imagem, ConstraintValidatorContext context) {
		return imagem.getSize() <= this.maxSize.toBytes();
	}

}
