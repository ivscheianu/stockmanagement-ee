package com.ivscheianu.common.exception;

import static java.util.Objects.nonNull;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Singleton;
import javax.ejb.TransactionRolledbackLocalException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
@Singleton
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final String SOMETHING_WENT_WRONG = "Whoops!? Something went wrong. At least I tried :)";

    @Override
    public Response toResponse(final Exception exception) {
        log.error("Exception received by " + getClass().getSimpleName() + ":", exception);
        if (exception instanceof WebApplicationException) {
            return buildResponseFromWebAppException((WebApplicationException) exception);
        }
        if (exception instanceof EJBTransactionRolledbackException && nonNull(exception.getCause())) {
            if (firstCauseIsWebAppException(exception)) {
                return buildResponseFromWebAppException((WebApplicationException) exception.getCause());
            } else if (secondCauseIsWebAppException(exception)) {
                return buildResponseFromWebAppException((WebApplicationException) exception.getCause().getCause());
            }
        }
        return buildInternalErrorResponse();
    }

    private boolean firstCauseIsWebAppException(final Exception exception) {
        return exception.getCause() instanceof WebApplicationException;
    }

    private boolean secondCauseIsWebAppException(final Exception exception) {
        return exception.getCause() instanceof TransactionRolledbackLocalException &&
               nonNull(exception.getCause().getCause()) &&
               exception.getCause().getCause() instanceof WebApplicationException;
    }

    private Response buildResponseFromWebAppException(final WebApplicationException webAppException) {
        return Response
            .status(webAppException.getResponse().getStatus())
            .entity(webAppException.getMessage())
            .build();
    }

    private Response buildInternalErrorResponse() {
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(SOMETHING_WENT_WRONG)
            .build();
    }
}
