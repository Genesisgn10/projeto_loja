package br.com.digitalhouse.exception;

public class ClienteNaoEncontradodException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradodException(String mensagem) {
		super(mensagem);		
	}

	public ClienteNaoEncontradodException(Long id) {
		this(String.format("Não existe um cadastro de cliente com código %d", id));
	}
}
