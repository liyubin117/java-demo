package com.json;

import java.util.List;

public class User {
    private Integer id;

    public User(Integer id, List<String> addr) {
        this.id = id;
        this.addr = addr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getAddr() {
        return addr;
    }

    public void setAddr(List<String> addr) {
        this.addr = addr;
    }

    private List<String> addr;
}
