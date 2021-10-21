package org.rick.di;

public class DbLogServiceImpl extends LogServiceImpl{
    @Override
    public void log(String msg){
        System.out.println("db log:"+msg);
    }
}
