package br.com.digitalhouse.exception;

public class ImagemNaoEncontradadException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImagemNaoEncontradadException(String mensagem) {
		super(mensagem);		
	}

	public ImagemNaoEncontradadException(Long id) {
		this(String.format("Não existe uma imagem com código %d", id));
	}
}
