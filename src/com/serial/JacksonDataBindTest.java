package com.serial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JacksonDataBindTest {

    @Test
    public void testObject2Jsonstr() throws JsonProcessingException, ParseException {
        User user = new User();
        user.setName("Eric");
        user.setEmail("justcode@ikeepstudying.com");
        user.setAge(20);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-08-23"));


        ObjectMapper mapper = new ObjectMapper();
        //User类转JSON
        //输出结果：{"name":"Lisha","age":20,"birthday":809107200000,"email":"justcode@ikeepstudying.com"}
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        //Java集合转JSON
        //输出结果：[{"name":"Lisha","age":20,"birthday":809107200000,"email":"justcode@ikeepstudying.com"}]
        List<User> users = new ArrayList<User>();
        users.add(user);
        String jsonlist = mapper.writeValueAsString(users);
        System.out.println(jsonlist);
    }

//    @Test
//    public void testJsonstr2Object() throws IOException {
//        String jsonStr = "{\"userName\":\"v1\",\"age\":10}";
//        User map = objectMapper.readValue(jsonStr, User.class);
//        System.out.println(map);
//    }
}

class User {
    private String name;
    private Integer age;
    private Date birthday;
    private String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}