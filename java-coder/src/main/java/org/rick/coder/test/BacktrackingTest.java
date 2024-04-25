package org.rick.coder.test;

import org.junit.Test;

import java.util.*;

public class BacktrackingTest {
    @Test
    public void test77() {
        for (List<Integer> list: new Solution77().combine(4, 2)) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    //77 组合 middle
    class Solution77 {
        private List<List<Integer>> result = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();
        public List<List<Integer>> combine(int n, int k) {
            backtracking(n, k, 1);
            return result;
        }
        private void backtracking(int n, int k, int startIndex) {
            if (path.size() == k) { //终止条件：已经拿到规定长度的路径
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n; i++) {
                path.add(i); //做出选择
                backtracking(n, k, i + 1);
                path.removeLast(); //撤销选择
            }
        }
    }

    @Test
    public void test22() {
        new Solution22().generateParenthesis(1);
    }

    //22 括号生成 middle
    class Solution22 {
        List<String> result = new ArrayList<String>();
        public List<String> generateParenthesis(int n) {
            generateAll(new char[2 * n], 0);
            return result;
        }
        //todo:这块的递归最好能从脑海里过一下，能加深对递归的理解
        public void generateAll(char[] current, int pos) {
            if (pos == current.length) {
                if (valid(current)) {
                    result.add(new String(current));
                }
                return;
            }
            current[pos] = '(';
            generateAll(current, pos + 1);
            current[pos] = ')';
            generateAll(current, pos + 1);
        }

        public boolean valid(char[] current) {
            int balance = 0;
            for (char c: current) {
                if (c == '(') {
                    ++balance;
                } else {
                    --balance;
                }
                if (balance < 0) {
                    return false;
                }
            }
            return balance == 0;
        }
    }

    @Test
    public void test47() {
        new Solution47().permuteUnique(new int[]{1, 1, 2});
    }

    //47 全排列2 middle
    class Solution47 {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            backtracking(nums);
            return result;
        }
        private void backtracking(int[] nums) {
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            Set<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (hashSet.contains(nums[i])) continue;
                if (path.contains(nums[i])) continue;
                path.add(nums[i]);
                hashSet.add(nums[i]);
                backtracking(nums);
                path.removeLast();
            }
        }
    }

    @Test
    public void test491() {
        System.out.println(new Solution491().findSubsequences(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1}));
    }

    class Solution491 {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracking(nums, 0);
            return result;
        }
        private void backTracking(int[] nums, int startIndex){
            if(path.size() >= 2) result.add(new ArrayList<>(path));
            HashSet<Integer> hs = new HashSet<>();
            for(int i = startIndex; i < nums.length; i++){
                if(!path.isEmpty() && path.getLast() > nums[i] || hs.contains(nums[i])) //前两个判断是树枝剪枝，后一个判断是树层去重
                    continue;
                hs.add(nums[i]);
                path.add(nums[i]);
                backTracking(nums, i + 1);
                path.removeLast();
            }
        }
    }
}
