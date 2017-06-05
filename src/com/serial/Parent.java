package com.serial;

import java.io.Serializable;
//循环引用：Parent引用Child，Child引用Parent
class Parent implements Serializable {
    String name;
    Child child;
    
    public Parent(String name) {
        this.name = name;
    }
    public Child getChild() {
        return child;
    }
    public void setChild(Child child) {
        this.child = child;
    }
}

class Child implements Serializable {
    String name;
    Parent parent;
    
    public Child(String name) {
        this.name = name;
    }
    public Parent getParent() {
        return parent;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
    }    
} 