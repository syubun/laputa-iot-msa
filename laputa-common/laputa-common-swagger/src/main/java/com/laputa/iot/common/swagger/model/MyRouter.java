package com.laputa.iot.common.swagger.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.net.URI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MyRouter {

    private String id;

    @NotNull
    private URI uri;
    private Map<String, Object> metadata = new HashMap();
    private int order = 0;
}
