package com.example.spring.mapstruct;

import com.example.spring.dto.MySysUserOrgDeptVo;
import com.example.spring.model.SysUserOrgDeptVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserOrgDeptVoMapper extends MyResponseMapper<List<SysUserOrgDeptVo>, List<MySysUserOrgDeptVo>> {

    SysUserOrgDeptVoMapper INSTANCE = Mappers.getMapper( SysUserOrgDeptVoMapper.class );

    @Mapping(source = "myId", target = "id")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "deptId", target = "dept")
    @Mapping(source = "orgId", target = "org")
    @Mapping(source = "orgName", target = "myOrgName")
    @Mapping(source = "deptName", target = "myDeptName")
    MySysUserOrgDeptVo mapToMySysUserOrgDeptVo(SysUserOrgDeptVo vo);

}
