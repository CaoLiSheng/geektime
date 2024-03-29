package com.example.spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysUserOrgDeptVo {

    @JsonProperty("uid")
    @JsonAlias({"id", "mid"})
    private Long myId;
    private Long userId;
    private Long orgId;
    private Long deptId;
    private Boolean hasMain;
    private String orgName;
    private String deptName;

}
