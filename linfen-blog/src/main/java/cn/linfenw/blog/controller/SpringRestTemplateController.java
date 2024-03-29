package cn.linfenw.blog.controller;

import com.alibaba.fastjson.JSON;
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
        String url="http://www.dmoe.cc/random.php?return=json";
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String jsonStr = results.getBody();
        JSONObject json = JSON.parseObject(jsonStr);
        return json.getString("imgurl");
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

    @GetMapping(value = "/testUpdateSoftware")
    public String testUpdateSoftware() {
        String json = "{\n" +
                "  \"Code\": 0,\n" +
                "  \"Msg\": \"\",\n" +
                "  \"UpdateStatus\": 1,\n" +
                "  \"VersionCode\": 3,\n" +
                "  \"VersionName\": \"1.0.2\",\n" +
                "  \"ModifyContent\": \"1、优化api接口。\\r\\n2、添加使用demo演示。\\r\\n3、新增自定义更新服务API接口。\\r\\n4、优化更新提示界面。\",\n" +
                "  \"DownloadUrl\": \"https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk\",\n" +
                "  \"ApkSize\": 2048,\n" +
                "  \"ApkMd5\": \"\"\n" +
                "}";
        return json;
    }
}
