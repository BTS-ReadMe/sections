package com.readme.sections.requestObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestKafkaMessage {
    private String name;
    private String msg;
}
