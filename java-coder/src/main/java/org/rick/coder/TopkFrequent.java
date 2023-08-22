package org.rick.coder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Yubin Li
 * 使用最小堆+hashmap解决
 * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 * 字典顺序即排在字母表前面（ascii码更小）的优先级更高，因此compareTo()比较后，排前面的是负数，但因为优先级高，要转成正数
 */
public class TopkFrequent {
    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"i","love","code","love","leetcode","code", "leetcode"}, 2));
    }

    private static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<>(
                (s1, s2) -> count.get(s1).equals(count.get(s2)) ? s2.compareTo(s1) : count.get(s1) - count.get(s2)
        );
        for (String word : count.keySet()) {
            queue.add(word);
            if (queue.size() > k) queue.poll();
        }
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        Collections.reverse(result);
        return result;
    }
}
