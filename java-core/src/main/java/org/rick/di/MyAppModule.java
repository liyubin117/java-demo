package org.rick.di;

import com.google.inject.AbstractModule;

public class MyAppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LogService.class).to(LogServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(Application.class).to(MyApp.class);
        //链式绑定，LogService -> LogServiceImpl -> DbLogServiceImpl
        bind(LogServiceImpl.class).to(DbLogServiceImpl.class);
    }
}