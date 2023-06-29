package com.fpbinar6.code.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fpbinar6.code.utils.ResponseHandler;

import io.jsonwebtoken.ClaimJwtException;
import jakarta.security.auth.message.AuthException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());
        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleSqlException(final SQLException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }
    @ExceptionHandler({ DataAccessException.class })
    public ResponseEntity<Object> handleDataAccessException(final DataAccessException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler({ AuthException.class })
    public ResponseEntity<Object> handleCostumeAuthException(final RuntimeException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> handleAuthException(final RuntimeException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler({ ClaimJwtException.class })
    public ResponseEntity<Object> handleExpiredJwtException(final RuntimeException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleAll(final RuntimeException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        logger.info("request: " + request.getContextPath());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}
