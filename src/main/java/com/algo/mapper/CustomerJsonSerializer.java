package com.algo.mapper;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yzy on 2017/2/13.
 */
public class CustomerJsonSerializer {

    private static final String DYNC_INCLUDE = "dynamic_include";
    private static final String DYNC_FILTER = "dynamic_filter";
    private ObjectMapper mapper = new ObjectMapper();

    @JsonFilter(value = DYNC_INCLUDE)
    interface DynamicInclude{

    }

    @JsonFilter(value = DYNC_FILTER)
    interface DynamicFileter{

    }

    public void filter(Class<?> clazz,String include,String filter){
        if(clazz == null)
            return;
        SimpleFilterProvider provider = new SimpleFilterProvider();
        if(StringUtils.isNotBlank(include)){
            provider.addFilter(DYNC_INCLUDE, SimpleBeanPropertyFilter.filterOutAllExcept(include.split(",")));
            mapper.addMixIn(clazz,DynamicInclude.class);
        }
        if(StringUtils.isNotBlank(filter)){
            provider.addFilter(DYNC_FILTER,SimpleBeanPropertyFilter.serializeAllExcept(filter.split(",")));
            mapper.addMixIn(clazz,DynamicFileter.class);
        }
        mapper.setFilterProvider(provider);
    }

    public String toJson(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

}
