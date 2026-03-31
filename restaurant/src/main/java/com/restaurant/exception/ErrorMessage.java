package com.restaurant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageType.getMessage());
        if (ofStatic != null) {
            stringBuilder.append(" Error Source : " + ofStatic);
        }
        return stringBuilder.toString();
    }

}
