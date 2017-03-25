package com.dalgim.sample.soap.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dalgim on 25.03.2017.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PersonDTO {

    private String login;
    private String password;
    private String firstname;
    private String lastname;

}
