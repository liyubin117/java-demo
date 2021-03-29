package com.coder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 有一只兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子，假如兔子都不死，问每个月的兔子总数为多少？
 * 对题目的分析：
 *    每个月的兔子都分为两类：1.上个月继承下来的兔子 2.这个月有生育能力生下来的兔子(每个兔子生一个)
 *      有生育能力的兔子==两个月前的兔子数量
 *      因此假设第n个月，则第n个月的兔子数量为 f(n) = f(n-1) + f(n-2)
 *      分析完毕发现这道题是斐波那契数列的变形
 */
public class RabbitCount {
    public static void main(String[] args) {
        Method1.invoke();
    }


    static class Method1{
        public static void invoke(){
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                int month = scanner.nextInt();

                //1.初始时农场有一只年龄为0的兔子
                ArrayList<Rabbit> farm = new ArrayList<>();
                Rabbit rabbit = new Rabbit(0);
                farm.add(rabbit);

                //2.每过去一个月：在这一个月*里面*，满了两个月的兔子会生产，同时它们的年龄会增加
                for (int i = 0; i < month; i++) {
                    int n = farm.size();
                    for (int j = 0; j < n; j++) {
                        Rabbit rabbit1 = farm.get(j);
                        if (rabbit1.getAge() >= 2) {
                            farm.add(new Rabbit(1));//这里是1不是0，需要注意。因为这个月过去了小兔子也满一个月了
                        }
                        rabbit1.setAge(rabbit1.getAge() + 1);
                    }
                }
                //得到兔子的总数
                int number = farm.size();
                System.out.println(number + "\t" + farm);
            }
        }

        static class Rabbit {
            private int age;

            public Rabbit(int age) {
                this.age = age;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public String toString(){
                return "R["+this.age+"]";
            }
        }
    }

}
