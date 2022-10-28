package io.github.rovingsea.utilityframework.spring.web.utils;

import io.github.rovingsea.utilityframework.spring.web.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.spring.web.exception.ExceptionEnum;
import io.github.rovingsea.utilityframework.spring.web.exception.ExceptionPostProcessor;
import io.github.rovingsea.utilityframework.spring.web.exception.ExpectedException;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.ExpectedExceptionHandler;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Throw the exception information conveniently and concretely,
 * including the response code and the information in the response body.
 * </p>
 * <p>
 * As there are too many methods involved, here is an example to summarize:
 * <pre>{@code
 *     public static void status400(Enum<? extends ExceptionEnum> en) {
 *         status400(en, null);
 *     }
 * }</pre>
 * The above code will not convey <i>opinion</i>, it will only be processed by {@link ExpectedExceptionHandler}.<br>
 *
 * <pre>{@code
 *     public static void status400(Enum<? extends ExceptionEnum> en, Object opinion) {
 *         ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
 *         throw new ExpectedException(exceptionEnum, HttpStatus.BAD_REQUEST, opinion);
 *     }
 * }</pre>
 * The above code will throw an exception containing response status,
 * response body content, and opinion of {@link ExceptionPostProcessor#postProcessAfterThrow(Object)}
 * </p>
 * <p>
 * Finally, the thrown exception will be handled in {@link ExceptionDispatcher}
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
public abstract class Throw {

    /**
     * Throw an exception, and the response code carries {@link HttpStatus#INTERNAL_SERVER_ERROR}
     * by default.
     *
     * @param en implementation enumeration of BaseEnum.
     */
    public static void exception(Enum<? extends ExceptionEnum> en) {
        exception(en, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Throw an exception with an opinion, and the response code carries {@link HttpStatus#INTERNAL_SERVER_ERROR}
     * by default.
     *
     * @param en      implementation enumeration of BaseEnum.
     * @param opinion see {@link ExpectedException#getOpinion()}
     */
    public static void exception(Enum<? extends ExceptionEnum> en, Object opinion) {
        exception(en, opinion, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Throw an exception, and the response code determined by parameter <i>httpStatus</i>.
     *
     * @param en         implementation enumeration of BaseEnum.
     * @param httpStatus the response status.
     */
    public static void exception(Enum<? extends ExceptionEnum> en, HttpStatus httpStatus) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        exception(exceptionEnum.getCode(), exceptionEnum.getMessage(), httpStatus);
    }

    /**
     * Throw an exception with an opinion, and the response code determined by parameter <i>httpStatus</i>.
     *
     * @param en         implementation enumeration of BaseEnum.
     * @param opinion    see {@link ExpectedException#getOpinion()}
     * @param httpStatus the response status.
     */
    public static void exception(Enum<? extends ExceptionEnum> en, Object opinion, HttpStatus httpStatus) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, httpStatus, opinion);
    }


    /**
     * Throw an exception, and the response code determined by parameter httpStatus.
     *
     * @param code       exception code in the response body
     * @param message    exception message in the response body
     * @param httpStatus the response status
     */
    public static void exception(int code, String message, HttpStatus httpStatus) {
        throw new ExpectedException(code, message, httpStatus);
    }

    // --- 4xx Client Error ---

    /**
     * {@code 400 Bad Request}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
     */
    public static void status400(Enum<? extends ExceptionEnum> en) {
        status400(en, null);
    }

    public static void status400(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.BAD_REQUEST, opinion);
    }

    /**
     * {@code 401 Unauthorized}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7235#section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    public static void status401(Enum<? extends ExceptionEnum> en) {
        status401(en, null);
    }

    public static void status401(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.UNAUTHORIZED, opinion);
    }

    /**
     * {@code 402 Payment Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.2">HTTP/1.1: Semantics and Content, section 6.5.2</a>
     */
    public static void status402(Enum<? extends ExceptionEnum> en) {
        status402(en, null);
    }

    public static void status402(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.PAYMENT_REQUIRED, opinion);
    }

    /**
     * {@code 403 Forbidden}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.3">HTTP/1.1: Semantics and Content, section 6.5.3</a>
     */
    public static void status403(Enum<? extends ExceptionEnum> en) {
        status403(en, null);
    }

    public static void status403(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.FORBIDDEN, opinion);
    }

    /**
     * {@code 404 Not Found}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    public static void status404(Enum<? extends ExceptionEnum> en) {
        status404(en, null);
    }

    public static void status404(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.NOT_FOUND, opinion);
    }

    /**
     * {@code 405 Method Not Allowed}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.5">HTTP/1.1: Semantics and Content, section 6.5.5</a>
     */
    public static void status405(Enum<? extends ExceptionEnum> en) {
        status405(en, null);
    }

    public static void status405(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.METHOD_NOT_ALLOWED, opinion);
    }

    /**
     * {@code 406 Not Acceptable}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.6">HTTP/1.1: Semantics and Content, section 6.5.6</a>
     */
    public static void status406(Enum<? extends ExceptionEnum> en) {
        status406(en, null);
    }

    public static void status406(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.NOT_ACCEPTABLE, opinion);
    }

    /**
     * {@code 407 Proxy Authentication Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7235#section-3.2">HTTP/1.1: Authentication, section 3.2</a>
     */
    public static void status407(Enum<? extends ExceptionEnum> en) {
        status407(en, null);
    }

    public static void status407(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.PROXY_AUTHENTICATION_REQUIRED, opinion);
    }

    /**
     * {@code 408 Request Timeout}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.7">HTTP/1.1: Semantics and Content, section 6.5.7</a>
     */
    public static void status408(Enum<? extends ExceptionEnum> en) {
        status408(en, null);
    }

    public static void status408(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.REQUEST_TIMEOUT, opinion);
    }

    /**
     * {@code 409 Conflict}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
     */
    public static void status409(Enum<? extends ExceptionEnum> en) {
        status409(en, null);
    }

    public static void status409(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.CONFLICT, opinion);
    }

    /**
     * {@code 410 Gone}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.9">
     * HTTP/1.1: Semantics and Content, section 6.5.9</a>
     */
    public static void status410(Enum<? extends ExceptionEnum> en) {
        status410(en, null);
    }

    public static void status410(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.GONE, opinion);
    }

    /**
     * {@code 411 Length Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.10">
     * HTTP/1.1: Semantics and Content, section 6.5.10</a>
     */
    public static void status411(Enum<? extends ExceptionEnum> en) {
        status411(en, null);
    }

    public static void status411(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.LENGTH_REQUIRED, opinion);
    }

    /**
     * {@code 412 Precondition failed}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7232#section-4.2">
     * HTTP/1.1: Conditional Requests, section 4.2</a>
     */
    public static void status412(Enum<? extends ExceptionEnum> en) {
        status412(en, null);
    }

    public static void status412(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.PRECONDITION_FAILED, opinion);
    }


    /**
     * {@code 413 Payload Too Large}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.11">
     * HTTP/1.1: Semantics and Content, section 6.5.11</a>
     * @since 4.1
     */
    public static void status413(Enum<? extends ExceptionEnum> en) {
        status413(en, null);
    }

    public static void status413(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.PAYLOAD_TOO_LARGE, opinion);
    }

    /**
     * {@code 414 URI Too Long}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.12">
     * HTTP/1.1: Semantics and Content, section 6.5.12</a>
     * @since 4.1
     */
    public static void status414(Enum<? extends ExceptionEnum> en) {
        status414(en, null);
    }

    public static void status414(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.URI_TOO_LONG, opinion);
    }

    /**
     * {@code 415 Unsupported Media Type}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.13">
     * HTTP/1.1: Semantics and Content, section 6.5.13</a>
     */
    public static void status415(Enum<? extends ExceptionEnum> en) {
        status415(en, null);
    }

    public static void status415(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.UNSUPPORTED_MEDIA_TYPE, opinion);
    }

    /**
     * {@code 416 Requested Range Not Satisfiable}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7233#section-4.4">HTTP/1.1: Range Requests, section 4.4</a>
     */
    public static void status416(Enum<? extends ExceptionEnum> en) {
        status416(en, null);
    }

    public static void status416(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, opinion);
    }

    /**
     * {@code 417 Expectation Failed}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.14">
     * HTTP/1.1: Semantics and Content, section 6.5.14</a>
     */
    public static void status417(Enum<? extends ExceptionEnum> en) {
        status417(en, null);
    }

    public static void status417(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.EXPECTATION_FAILED, opinion);
    }

    /**
     * {@code 418 I'm a teapot}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2324#section-2.3.2">HTCPCP/1.0</a>
     */
    public static void status418(Enum<? extends ExceptionEnum> en) {
        status418(en, null);
    }

    public static void status418(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.I_AM_A_TEAPOT, opinion);
    }

    /**
     * {@code 422 Unprocessable Entity}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918#section-11.2">WebDAV</a>
     */
    public static void status422(Enum<? extends ExceptionEnum> en) {
        status422(en, null);
    }

    public static void status422(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.UNPROCESSABLE_ENTITY, opinion);
    }

    /**
     * {@code 423 Locked}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918#section-11.3">WebDAV</a>
     */
    public static void status423(Enum<? extends ExceptionEnum> en) {
        status423(en, null);
    }

    public static void status423(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.LOCKED, opinion);
    }

    /**
     * {@code 424 Failed Dependency}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918#section-11.4">WebDAV</a>
     */
    public static void status424(Enum<? extends ExceptionEnum> en) {
        status424(en, null);
    }

    public static void status424(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.FAILED_DEPENDENCY, opinion);
    }

    /**
     * {@code 425 Too Early}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc8470">RFC 8470</a>
     * @since 5.2
     */
    public static void status425(Enum<? extends ExceptionEnum> en) {
        status425(en, null);
    }

    public static void status425(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.TOO_EARLY, opinion);
    }

    /**
     * {@code 426 Upgrade Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2817#section-6">Upgrading to TLS Within HTTP/1.1</a>
     */
    public static void status426(Enum<? extends ExceptionEnum> en) {
        status426(en, null);
    }

    public static void status426(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.UPGRADE_REQUIRED, opinion);
    }

    /**
     * {@code 428 Precondition Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585#section-3">Additional HTTP Status Codes</a>
     */
    public static void status428(Enum<? extends ExceptionEnum> en) {
        status428(en, null);
    }

    public static void status428(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.PRECONDITION_REQUIRED, opinion);
    }

    /**
     * {@code 429 Too Many Requests}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585#section-4">Additional HTTP Status Codes</a>
     */
    public static void status429(Enum<? extends ExceptionEnum> en) {
        status429(en, null);
    }

    public static void status429(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.TOO_MANY_REQUESTS, opinion);
    }

    /**
     * {@code 431 Request Header Fields Too Large}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585#section-5">Additional HTTP Status Codes</a>
     */
    public static void status431(Enum<? extends ExceptionEnum> en) {
        status431(en, null);
    }

    public static void status431(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, opinion);
    }

    /**
     * {@code 451 Unavailable For Legal Reasons}.
     *
     * @see <a href="https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status-04">
     * An HTTP Status Code to Report Legal Obstacles</a>
     * @since 4.3
     */
    public static void status451(Enum<? extends ExceptionEnum> en) {
        status451(en, null);
    }

    public static void status451(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, opinion);
    }

    // --- 5xx Server Error ---

    /**
     * {@code 500 Internal Server Error}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
     */
    public static void status500(Enum<? extends ExceptionEnum> en) {
        status500(en, null);
    }

    public static void status500(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.INTERNAL_SERVER_ERROR, opinion);
    }

    /**
     * {@code 501 Not Implemented}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.2">HTTP/1.1: Semantics and Content, section 6.6.2</a>
     */
    public static void status501(Enum<? extends ExceptionEnum> en) {
        status501(en, null);
    }

    public static void status501(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.NOT_IMPLEMENTED, opinion);
    }

    /**
     * {@code 502 Bad Gateway}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.3">HTTP/1.1: Semantics and Content, section 6.6.3</a>
     */
    public static void status502(Enum<? extends ExceptionEnum> en) {
        status502(en, null);
    }

    public static void status502(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.BAD_GATEWAY, opinion);
    }

    /**
     * {@code 503 Service Unavailable}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.4">HTTP/1.1: Semantics and Content, section 6.6.4</a>
     */
    public static void status503(Enum<? extends ExceptionEnum> en) {
        status503(en, null);
    }

    public static void status503(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.SERVICE_UNAVAILABLE, opinion);
    }

    /**
     * {@code 504 Gateway Timeout}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.5">HTTP/1.1: Semantics and Content, section 6.6.5</a>
     */
    public static void status504(Enum<? extends ExceptionEnum> en) {
        status504(en, null);
    }

    public static void status504(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.GATEWAY_TIMEOUT, opinion);
    }

    /**
     * {@code 505 HTTP Version Not Supported}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.6.6">HTTP/1.1: Semantics and Content, section 6.6.6</a>
     */
    public static void status505(Enum<? extends ExceptionEnum> en) {
        status505(en, null);
    }

    public static void status505(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.HTTP_VERSION_NOT_SUPPORTED, opinion);
    }


    /**
     * {@code 506 Variant Also Negotiates}
     *
     * @see <a href="https://tools.ietf.org/html/rfc2295#section-8.1">Transparent Content Negotiation</a>
     */
    public static void status506(Enum<? extends ExceptionEnum> en) {
        status506(en, null);
    }

    public static void status506(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.VARIANT_ALSO_NEGOTIATES, opinion);
    }

    /**
     * {@code 507 Insufficient Storage}
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918#section-11.5">WebDAV</a>
     */
    public static void status507(Enum<? extends ExceptionEnum> en) {
        status507(en, null);
    }

    public static void status507(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.INSUFFICIENT_STORAGE, opinion);
    }

    /**
     * {@code 508 Loop Detected}
     *
     * @see <a href="https://tools.ietf.org/html/rfc5842#section-7.2">WebDAV Binding Extensions</a>
     */
    public static void status508(Enum<? extends ExceptionEnum> en) {
        status508(en, null);
    }

    public static void status508(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.LOOP_DETECTED, opinion);
    }

    /**
     * {@code 509 Bandwidth Limit Exceeded}
     */
    public static void status509(Enum<? extends ExceptionEnum> en) {
        status509(en, null);
    }

    public static void status509(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, opinion);
    }

    /**
     * {@code 510 Not Extended}
     *
     * @see <a href="https://tools.ietf.org/html/rfc2774#section-7">HTTP Extension Framework</a>
     */
    public static void status510(Enum<? extends ExceptionEnum> en) {
        status510(en, null);
    }

    public static void status510(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.NOT_EXTENDED, opinion);
    }

    /**
     * {@code 511 Network Authentication Required}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585#section-6">Additional HTTP Status Codes</a>
     */
    public static void status511(Enum<? extends ExceptionEnum> en) {
        status511(en, null);
    }

    public static void status511(Enum<? extends ExceptionEnum> en, Object opinion) {
        ExceptionEnum exceptionEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        throw new ExpectedException(exceptionEnum, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, opinion);
    }

}
