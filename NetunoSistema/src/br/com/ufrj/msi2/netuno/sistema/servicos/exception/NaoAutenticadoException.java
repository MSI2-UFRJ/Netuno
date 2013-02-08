package br.com.ufrj.msi2.netuno.sistema.servicos.exception;

public class NaoAutenticadoException extends Exception {
	private static final long serialVersionUID = 8337944128541903616L;
	
	public NaoAutenticadoException() {}
	
	public NaoAutenticadoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NaoAutenticadoException(String message) {
		super(message);
	}
	public NaoAutenticadoException(Throwable cause) {
		super(cause);
	}
}
