package com.example.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class MySysUserOrgDeptVo {

    private Long id;
    private Long user;
    private Long org;
    private Long dept;
    private Boolean hasMain;
    private String myOrgName;
    private String myDeptName;

}
