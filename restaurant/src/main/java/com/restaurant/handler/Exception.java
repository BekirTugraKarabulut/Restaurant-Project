package com.restaurant.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception<T> {

    private String hostName;

    private String path;

    private T message;

}
