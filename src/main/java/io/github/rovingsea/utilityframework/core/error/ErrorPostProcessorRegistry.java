package io.github.rovingsea.utilityframework.core.error;

import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import io.github.rovingsea.utilityframework.UtilityContextComponent;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * All {@link ErrorPostProcessor} instances will be registered here.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ErrorPostProcessorRegistry extends UtilityContextComponent {

    /**
     * The map of all {@link ErrorPostProcessor}s
     */
    private Map<String, ErrorPostProcessor> errorPostProcessors;

    public ErrorPostProcessorRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    @PostConstruct
    public void initialize() {
        initErrorPostProcessors(getApplicationContext());
    }

    /**
     * @param context Context coexisting with spring-application
     */
    public void initErrorPostProcessors(ApplicationContext context) {
        Map<String, ErrorPostProcessor> errorPostProcessorMap
                = context.getBeansOfType(ErrorPostProcessor.class);
        if (CollectionUtils.isEmpty(errorPostProcessorMap)) {
            this.errorPostProcessors = new HashMap<>(0);
            return;
        }
        setErrorHandlers(errorPostProcessorMap);
    }

    public Map<String, ErrorPostProcessor> getErrorPostProcessors() {
        return this.errorPostProcessors;
    }

    public void setErrorHandlers(Map<String, ErrorPostProcessor> errorPostProcessorMap) {
        this.errorPostProcessors = errorPostProcessorMap;
    }

}
