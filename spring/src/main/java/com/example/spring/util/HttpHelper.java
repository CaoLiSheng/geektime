package com.example.spring.util;

import com.alibaba.fastjson.JSONObject;
import com.example.spring.exception.MyException;
import com.example.spring.mapstruct.Holder;
import com.example.spring.mapstruct.MyMapper;
import com.example.spring.mapstruct.ResponseCarMapper;
import com.example.spring.mapstruct.SysUserOrgDeptVoMapper;
import com.example.spring.model.Car;
import com.example.spring.model.SysUserOrgDeptVo;
import com.example.spring.response.MyResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class HttpHelper {

    private Map<String, ParameterizedTypeReference<?>> ptrConfigs;
    private Map<String, MyMapper> mappers;

    public HttpHelper() {
        ptrConfigs = new HashMap<>();
        ptrConfigs.put("http://localhost:8080/car",
                new ParameterizedTypeReference<MyResponse<Car>>() {
        });
        ptrConfigs.put("http://10.88.88.74:9001/sys/sysUserOrgDept/public/listByUsername",
                new ParameterizedTypeReference<MyResponse<List<SysUserOrgDeptVo>>>() {
        });

        mappers = new HashMap<>();
        mappers.put("http://localhost:8080/car", ResponseCarMapper.INSTANCE);
        mappers.put("http://10.88.88.74:9001/sys/sysUserOrgDept/public/listByUsername", SysUserOrgDeptVoMapper.INSTANCE);
    }

    private ParameterizedTypeReference<?> getPtr(String url) {
        for (String cfg : ptrConfigs.keySet()) {
            if (url.startsWith(cfg)) {
                return ptrConfigs.get(cfg);
            }
        }
        throw new RuntimeException("没有找到URL["+url+"]Ptr配置项");
    }

    private <T> MyMapper<?, T> getMapper(String url, ParameterizedTypeReference<T> ptr) {
        for (String cfg : mappers.keySet()) {
            if (url.startsWith(cfg)) {
                return mappers.get(cfg);
            }
        }
        throw new RuntimeException("没有找到URL["+url+"]Mapper配置项");
    }

    public <T> Holder<T> transform(Object obj, ParameterizedTypeReference<T> ptrTo, MyMapper<?, T> mapper) {
        System.out.println("debug-----------begin");
        System.out.println(obj);
        Holder<T> tHolder = mapper.objectToHolder(obj);
        System.out.println(tHolder);
        System.out.println("debug-----------end");
        return tHolder;
    }

    public <T> T exchange(String url, HttpMethod method, JSONObject jsonObj, ParameterizedTypeReference<T> ptr, Map<String,?> uriVariables) {
        System.out.println(String.format("%s-%s", method, url));

        ParameterizedTypeReference<?> origPtr = getPtr(url);
        MyMapper<?, T> mapper = getMapper(url, ptr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null == jsonObj ? "" : jsonObj.toString(), headers);
        ResponseEntity<?> responseEntity = null == uriVariables ?
                restTemplate.exchange(url, method, entity, origPtr) :
                restTemplate.exchange(url, method, entity, origPtr, uriVariables);

        System.out.println(responseEntity);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Object body = Objects.requireNonNull(responseEntity.getBody(), "接口返回为空");
            return transform(new Holder(body), ptr, mapper).getData();
        } else {
            throw new MyException(responseEntity.getStatusCode().getReasonPhrase());
        }
    }

}
