package com.empresa.api.dto;

import com.empresa.api.model.enuns.RoleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoleDto {

    @NotNull
    private RoleName roleName;

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
