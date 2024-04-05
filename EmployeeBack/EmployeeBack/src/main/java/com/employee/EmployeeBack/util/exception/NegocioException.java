package com.employee.EmployeeBack.util.exception;

/**
 * Clase Exception encargada de tratar excepciones de negocio personalizadas
 *
 * @version 1.0.0
 */
public class NegocioException extends RuntimeException{

    /**
     * Constructor de la clase
     * @param message mensaje de error
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor de la clase
     * @param message mensaje de error
     * @param cause causa del error
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}