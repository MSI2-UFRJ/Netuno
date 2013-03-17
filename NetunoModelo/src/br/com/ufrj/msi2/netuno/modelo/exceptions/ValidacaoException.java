package br.com.ufrj.msi2.netuno.modelo.exceptions;

public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 3455535115668092428L;

	public ValidacaoException() {}

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(String message) {
		super(message);
	}

	public ValidacaoException(Throwable cause) {
		super(cause);
	}

}
