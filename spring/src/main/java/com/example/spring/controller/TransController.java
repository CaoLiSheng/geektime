package com.example.spring.controller;

import com.example.spring.dto.MySysUserOrgDeptVo;
import com.example.spring.response.MyResponse;
import com.example.spring.util.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trans")
public class TransController {

    @Autowired
    private HttpHelper httpHelper;

    @GetMapping("/listByUsername/{username}")
    public MyResponse<List<MySysUserOrgDeptVo>> listByUsername(@PathVariable("username") String username) {
        return httpHelper.exchange("http://10.88.88.74:9001/sys/sysUserOrgDept/public/listByUsername?deptType=4&username=" + username,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<MyResponse<List<MySysUserOrgDeptVo>>>() {
                }, null);
    }

}
