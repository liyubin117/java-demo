package org.rick.coder;

import java.util.Arrays;

/**
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数 。
 * 碰撞双指针，前提是数组有序
 */
public class NumRescueBoats {
    public static void main(String[] args) {
        System.out.println(get(new int[]{3,2,2,1}, 3));
    }

    private static int get(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;
        int[] sortedPeople = Arrays.stream(people).sorted().toArray();
        int i = 0, j = sortedPeople.length - 1, result = 0;
        while (j >= i) {
            if (sortedPeople[i] + sortedPeople[j] <= limit) i++;
            j--;
            result++;
        }
        return result;
    }
}
