package com.vmeknowledge.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
@Component
public class MysqlInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    public void initializeAllMappers() {
        Map<String, Object> mappers = applicationContext.getBeansWithAnnotation(org.apache.ibatis.annotations.Mapper.class);

        for (Map.Entry<String, Object> entry : mappers.entrySet()) {
            Object mapper = entry.getValue();
            log.info("Mapper key:{}", entry.getKey());
            try {
                Method initMethod = mapper.getClass().getDeclaredMethod("init");
                initMethod.invoke(mapper);
                log.info("Executed init for: {}", mapper.getClass().getName());
            } catch (NoSuchMethodException e) {
                log.error("No init method in: {}", mapper.getClass().getName());
            } catch (Exception e) {
                log.error("Failed to execute init for: {}; Reason: {}", mapper.getClass().getName(), e.toString());
            }
        }
    }
}
