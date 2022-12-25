package com.cemetiere.weblab.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull
    private User user;
    @NotNull
    private String password;
}
