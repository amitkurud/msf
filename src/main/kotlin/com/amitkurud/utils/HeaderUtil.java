/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Header util.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    /**
     * Create alert http headers.
     *
     * @param applicationName the application name
     * @param message         the message
     * @param param           the param
     * @return the http headers
     */
    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        try {
            headers.add("X-" + applicationName + "-params", URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            // StandardCharsets are supported by every Java implementation so this exception will never happen
        }
        return headers;
    }

    /**
     * Create entity creation alert http headers.
     *
     * @param applicationName   the application name
     * @param enableTranslation the enable translation
     * @param entityName        the entity name
     * @param param             the param
     * @return the http headers
     */
    public static HttpHeaders createEntityCreationAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".created"
                : "A new " + entityName + " is created with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * Create entity update alert http headers.
     *
     * @param applicationName   the application name
     * @param enableTranslation the enable translation
     * @param entityName        the entity name
     * @param param             the param
     * @return the http headers
     */
    public static HttpHeaders createEntityUpdateAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".updated"
                : "A " + entityName + " is updated with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * Create entity deletion alert http headers.
     *
     * @param applicationName   the application name
     * @param enableTranslation the enable translation
     * @param entityName        the entity name
     * @param param             the param
     * @return the http headers
     */
    public static HttpHeaders createEntityDeletionAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".deleted"
                : "A " + entityName + " is deleted with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * Create failure alert http headers.
     *
     * @param applicationName   the application name
     * @param enableTranslation the enable translation
     * @param entityName        the entity name
     * @param errorKey          the error key
     * @param defaultMessage    the default message
     * @return the http headers
     */
    public static HttpHeaders createFailureAlert(String applicationName, boolean enableTranslation, String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);

        String message = enableTranslation ? "error." + errorKey : defaultMessage;

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-error", message);
        headers.add("X-" + applicationName + "-params", entityName);
        return headers;
    }
}
