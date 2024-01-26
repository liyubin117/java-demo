package org.rick.coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//回溯
public class BackTrace {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(Arrays.toString(FullyArranged.permute(nums).toArray()));
        System.out.println(Arrays.toString(Subsets.subsets(nums).toArray()));
    }
}

/**
 * 输⼊⼀组不重复的数字，返回它们的全排列
 */
class FullyArranged {
    private static final List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    private static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num))
                continue;
            // 做选择
            track.add(num);
            // 进⼊下⼀层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}

/**
 * 子集，78
 */
class Subsets {
    private static final List<List<Integer>> result = new ArrayList<>();
    private static final Deque<Integer> path = new LinkedList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    private static void backTracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex == nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }

    }
}
