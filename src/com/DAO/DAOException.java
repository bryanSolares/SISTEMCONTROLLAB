package com.DAO;

public class DAOException extends Exception {

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message) {
        super(message);
    }

}
