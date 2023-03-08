package io.github.utilityframework.boot.web.response;

import io.github.utilityframework.boot.share.response.Data;
import io.github.utilityframework.boot.share.response.ResponseBody;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * The configuration of spring utility web response.
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.boot.utility.web.response")
public class ResponseProperties {

    /**
     * The name of key that storage data, such as:
     * <pre>{@code
     *     '${storageDataName}' : 'data'
     * }</pre>
     */
    private String storageDataName;
    /**
     * The type of unified response
     */
    private String type;
    /**
     * The class path of unified response body
     */
    private String bodyClassName;
    /**
     * The class path of unified response header
     */
    private String headerClassName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (StringUtils.isEmpty(type)) {
            this.type = "io.github.utilityframework.boot.web.response.JsonResponse";
        } else {
            try {
                Class.forName(type);
                this.type = type;
            } catch (ClassNotFoundException e) {
                throw new WebResponseException(e);
            }
        }
    }

    public String getBodyClassName() {
        return bodyClassName;
    }

    public void setBodyClassName(String bodyClassName) {
        if (StringUtils.isEmpty(bodyClassName)) {
            this.bodyClassName = "io.github.utilityframework.boot.web.response.DefaultResponseBody";
        } else {
            try {
                Class<?> bodyClass = Class.forName(bodyClassName);
                ResponseBody responseBody = bodyClass.getAnnotation(ResponseBody.class);
                if (responseBody != null) {
                    this.type = responseBody.type().getCanonicalName();
                }
                Field[] declaredFields = bodyClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    Data data = declaredField.getAnnotation(Data.class);
                    if (data != null) {
                        this.storageDataName = declaredField.getName();
                        break;
                    }
                }
                this.bodyClassName = bodyClassName;
            } catch (ClassNotFoundException e) {
                throw new WebResponseException(e);
            }
        }
    }

    public String getHeaderClassName() {
        return headerClassName;
    }

    public void setHeaderClassName(String headerClassName) {
        if (StringUtils.isEmpty(headerClassName)) {
            this.headerClassName = "io.github.utilityframework.boot.web.response.DefaultResponseHeader";
        } else {
            try {
                Class.forName(headerClassName);
                this.headerClassName = headerClassName;
            } catch (ClassNotFoundException e) {
                throw new WebResponseException(e);
            }
        }
    }

    public String getStorageDataName() {
        return storageDataName;
    }

    public void setStorageDataName(String storageDataName) {
        if (StringUtils.isEmpty(storageDataName)) {
            this.storageDataName = "data";
        } else {
            this.storageDataName = storageDataName;
        }
    }
}
