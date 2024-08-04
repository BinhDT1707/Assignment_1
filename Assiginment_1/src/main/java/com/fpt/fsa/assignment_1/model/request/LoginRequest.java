package com.fpt.fsa.assignment_1.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String accountName;
    private String password;
}
