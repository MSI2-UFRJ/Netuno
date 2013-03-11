package br.com.ufrj.msi2.netuno.modelo.exceptions;

public class ResultadoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 8337944128541903616L;

	public ResultadoNaoEncontradoException() {}

	public ResultadoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResultadoNaoEncontradoException(String message) {
		super(message);
	}

	public ResultadoNaoEncontradoException(Throwable cause) {
		super(cause);
	}

}
