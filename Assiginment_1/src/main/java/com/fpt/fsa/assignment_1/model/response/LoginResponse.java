package com.fpt.fsa.assignment_1.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fsa.assignment_1.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    @JsonIgnoreProperties({"password"})
    private Account account;
}
