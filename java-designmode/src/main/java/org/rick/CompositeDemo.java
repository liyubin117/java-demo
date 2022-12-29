package org.rick;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式，解决部分-整体场景下，一致地操作简单与复杂对象
 */
public class CompositeDemo {
    @Test
    public void test1() {
        MenuComponent menu = new Menu("root", 0);
        menu.add(new MenuItem("file1", 1));
        MenuComponent hack = new Menu("hack", 1);
        hack.add(new MenuItem("sb1", 2));
        hack.add(new MenuItem("sb2", 2));
        menu.add(hack);
        menu.print();
    }
}

abstract class MenuComponent {

    protected String name;
    protected int level;

    //添加菜单
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    //移除菜单
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    //获取指定的子菜单
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    //获取菜单名称
    public String getName() {
        return name;
    }

    //打印方法
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
    }
}

class Menu extends MenuComponent {

    private final List<MenuComponent> menuComponentList;

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
        menuComponentList = new ArrayList<>();
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponentList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponentList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponentList.get(i);
    }

    @Override
    public void print() {
        super.print();
        for (MenuComponent menuComponent : menuComponentList) {
            menuComponent.print();
        }
    }
}

class MenuItem extends MenuComponent {

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }
}