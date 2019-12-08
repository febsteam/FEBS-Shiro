package cc.mrbird.febs.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author MrBird
 */
public class FebsResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public FebsResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public FebsResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public FebsResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public FebsResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public FebsResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public FebsResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
