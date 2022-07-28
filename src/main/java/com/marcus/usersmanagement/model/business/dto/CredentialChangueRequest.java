package com.marcus.usersmanagement.model.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class CredentialChangueRequest implements Serializable {
    private static final long serialVersionUID = -3762853457296215903L;

    private List<String> credentials;

}
