package cc.mrbird.febs.common.exception;

/**
 * Redis 连接异常
 *
 * @author MrBird
 */
public class RedisConnectException extends Exception {

    private static final long serialVersionUID = 1639374111871115063L;

    public RedisConnectException(String message) {
        super(message);
    }
}
