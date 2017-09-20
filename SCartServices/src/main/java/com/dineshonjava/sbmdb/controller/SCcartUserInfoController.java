
package com.dineshonjava.sbmdb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dineshonjava.sbmdb.models.ScUserInfo;
import com.dineshonjava.sbmdb.models.ScUserInfoRepository;


@RestController
@RequestMapping("/sccart")
public class SCcartUserInfoController {
	
	@Autowired
	ScUserInfoRepository scUserInfoRepository;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<ScUserInfo> createProduct(@RequestBody ScUserInfo scUserInfo) {
		System.out.println("Enter ::: ");
		scUserInfo = scUserInfoRepository.save(scUserInfo);
		return new ResponseEntity<ScUserInfo>(scUserInfo, HttpStatus.OK);
    }
	
	
	
	@RequestMapping("/user/{name}")
	public Map<String, Object> getUserInfo(@PathVariable String name) {
		ScUserInfo user = scUserInfoRepository.findByName(name);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("role", "Premium");
		dataMap.put("userInfo", user);
	    return dataMap;
	}
}
