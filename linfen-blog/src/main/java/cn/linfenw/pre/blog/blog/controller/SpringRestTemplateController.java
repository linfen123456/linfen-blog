package cn.linfenw.pre.blog.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//请求第三方数据
@RequestMapping("/restTemplate")
@RestController
public class SpringRestTemplateController {
    @Autowired
    private RestTemplate restTemplate;
    /***********HTTP GET method*************/
    @GetMapping("/image")
    public String getJson(){
        String url="https://api.dongmanxingkong.com/suijitupian/acg/1080p/index.php?return=json";
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        return json;
    }

    /**********HTTP POST method**************/
    @PostMapping(value = "/testPost")
    public Object postJson(@RequestBody JSONObject param) {
        System.out.println(param.toJSONString());
        param.put("action", "post");
        param.put("username", "tester");
        param.put("pwd", "123456748");
        return param;
    }

    @PostMapping(value = "/testPostApi")
    public Object testPost() {
        String url = "http://localhost:8081/girl/testPost";
        JSONObject postData = new JSONObject();
        postData.put("descp", "request for post");
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        return json;
    }
}
