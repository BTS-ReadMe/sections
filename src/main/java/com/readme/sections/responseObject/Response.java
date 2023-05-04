package com.readme.sections.responseObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private Object data;
}
