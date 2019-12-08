package com.hwtx.pf4j;


import com.hwtx.pf4j.util.StringUtils;

/**
 * An exception used to indicate that a plugin problem occurred.
 * It's a generic plugin exception class to be thrown when no more specific class is applicable.
 *
 * @author Decebal Suiu
 */
public class PluginRuntimeException extends RuntimeException {

    public PluginRuntimeException() {
        super();
    }

    public PluginRuntimeException(String message) {
        super(message);
    }

    public PluginRuntimeException(Throwable cause) {
        super(cause);
    }

    public PluginRuntimeException(Throwable cause, String message, Object... args) {
        super(StringUtils.format(message, args), cause);
    }

    public PluginRuntimeException(String message, Object... args) {
        super(StringUtils.format(message, args));
    }

}
