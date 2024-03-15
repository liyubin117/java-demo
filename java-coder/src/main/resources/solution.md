# 数组
## 27 移除元素 easy
快慢指针，快指针指向要保留的元素值，慢指针指向删除元素后的数组的索引。快指针是读指针，慢指针是写指针
```
    public int removeElement(int[] nums, int val) {
        //双指针
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }
```
## 977 有序数组的平方 easy 
双指针，选较大的那个值逆序放到结果集并移动指针 
## 209 长度最小的子数组 middle 
滑动窗口，注意j是窗口的终止位置
```
    public int minSubArrayLen(int target, int[] nums) {
        //滑动窗口
        int result = Integer.MAX_VALUE, sum = 0, i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i++];
            }
        }
        result = result == Integer.MAX_VALUE ? 0 : result;
        return result;
    }
```
## 59 螺旋矩阵2 middle 
边界是left right top bottom，循环到n*n次赋值结束
```
    public int[][] generateMatrix(int n) {
            int left = 0, right = n-1, top = 0, bottom = n-1;
            int count = 1, target = n * n;
            int[][] res = new int[n][n];
            //for循环中变量定义成i或j的细节：按照通常的思维，i代表行，j代表列
            //这样，就可以很容易区分出来变化的量应该放在[][]的第一个还是第二个
            //对于变量的边界怎么定义：
                //从左向右填充：填充的列肯定在[left,right]区间
                //从上向下填充：填充的行肯定在[top,bottom]区间
                //从右向左填充：填充的列肯定在[right,left]区间
                //从下向上填充：填充的行肯定在[bootom,top]区间
            //通过上面的总结会发现边界的起始和结束与方向是对应的
            while(count <= target){
                //从左到右填充，相当于缩小上边界
                for(int j = left; j <= right; j++) res[top][j] = count++;
                //缩小上边界
                top++;
                //从上向下填充，相当于缩小右边界
                for(int i = top; i <=bottom; i++) res[i][right] = count++;
                //缩小右边界
                right--;
                //从右向左填充，相当于缩小下边界
                for(int j = right; j >= left; j--) res[bottom][j] = count++;
                //缩小下边界
                bottom--;
                //从下向上填充，相当于缩小左边界
                for(int i = bottom; i >= top; i--) res[i][left] = count++;
                //缩小左边界
                left++;
            }
            return res;
    }
```
## 15 三数之和且不可出现重复三元组 middle 
先排序，固定一个点i后双指针left/right，再移动这个点，要注意去重
```
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return result; // 排序之后如果第一个元素已经大于零，那么无论如何组合都不可能凑成三元组，直接返回结果就可以了
            if (i > 0 && nums[i] == nums[i - 1]) continue; //去重a
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if (tmp < 0) left++;
                else if (tmp > 0) right--;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++; //去重b
                    while (left < right && nums[right] == nums[right - 1]) right--; //去重c
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
```
## 18 四数之和 middle 
在三数之和基础上多了一层for循环，注意有一级剪枝/去重，二级剪枝/去重，且不能过早return结果，剪枝后break即可
```
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] >= 0 && nums[k] > target) break; //一级剪枝
            if (k > 0 && nums[k] == nums[k - 1]) continue; //一级去重a
            for (int i = k + 1; i < nums.length; i++) {
                if (nums[k] + nums[i] >= 0 && nums[k] + nums[i] > target) break; //二级剪枝
                if (i > k + 1 && nums[i] == nums[i - 1]) continue; //二级去重b
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) right--;
                    else if (sum < target) left++;
                    else {
                        result.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } 
                }
            }
        }
        return result;
    }
```

# 链表
## LCR 136 删除链表的节点 easy 
虚拟头节点next指针指向head节点，即可使用统一的方式遍历删除指定节点
```
    public ListNode deleteNode(ListNode head, int val) {
        //虚拟头节点
        ListNode dummyHead = new ListNode(-1), cur = dummyHead, tmp;
        dummyHead.next = head;
        while (cur != null) {
            tmp = cur.next;
            if (tmp != null && tmp.val == val) {
                cur.next = tmp.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }
```
## 707 设计链表 middle 
## 19 删除链表倒数第N个节点
快慢指针 构建dummyHead虚拟头节点，快慢指针都指向该dummyHead，先让快指针跑N个节点，慢指针再开始跑，直到快指针到null，此时慢指针指向的就是待删除节点的前一个节点，再让慢指针next指向next.next，再返回虚拟头节点next
```
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 只要快慢指针相差 n 个结点即可
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }

        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 此时 slowIndex 的位置就是待删除元素的前一个位置。
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }
```
## 206 反转链表 easy
```
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
```
## 143 重排链表 middle 
使用线性表支持下标的特性，但这个的空间复杂度是O(N)，还可使用寻找链表中点 + 链表逆序 + 合并链表的方式，空间复杂度O(1)
```
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int l = 0, r = list.size() - 1;
        while (l < r) {
            list.get(l).next = list.get(r);
            l++;
            if (l == r) break;
            list.get(r).next = list.get(l);
            r--;
        }
        list.get(l).next = null; //此时将实际上的最后一个节点指向null，否则会cycle
    }
```
## 237 删除链表中的节点 middle 
由于只给定了要删除的节点node，不能使用遍历的方法，而是将node的值改为下一个节点，再将node下一个节点指向下下一个节点即可
```
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
```
## 382 链表随机节点 middle
使用线性表
```
class Solution {
    List<ListNode> list = new ArrayList<>();
    Random random = new Random();

    public Solution(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
    }
    
    public int getRandom() {
        return list.get(random.nextInt(list.size())).val;
    }
}
```
## 160 相交链表 easy 
使用哈希集合寻找相交的起始节点
```
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode curA = headA, curB = headB;
        while (curA != null) {
            set.add(curA);
            curA = curA.next;
        }
        while (curB != null) {
            if (set.contains(curB)) return curB;
            curB = curB.next;
        }
        return null;
    }
```

# 哈希表
常用于解决多个集合间是否有出现过的元素，结构可以有数组、set、map，当哈希值可控，较少时使用数组，较多时使用set；哈希值不可控时使用map
## 242 有效的字母异位词 easy 
哈希值是固定的即26个字母，构建int[26]，字符串的每个元素-'a'即可作为数组索引，遍历第一个字符串加值，第二个字符串减值，若最终都为0则说明true
```
    public boolean isAnagram(String s, String t) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0) return false;
        }
        return true;
    }
```
## 349 两个数组的交集 easy 
由于值最大是1000，定义哈希数组int[1001]，一个写，一个读，找到大于0的值，其索引即交集元素
```
    public int[] intersection(int[] nums1, int[] nums2) {
        int hash[] = new int[1001];
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hash[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            if (hash[nums2[i]] > 0) sets.add(nums2[i]);
        }
        int results[] = new int[sets.size()], index = 0;
        for (int num: sets) {
            results[index++] = num;
        }
        return results;
    }
```
## 454 四数相加 middle 
由于哈希值不可控，使用map结构。两个数组遍历后写入hash，另外两个数组遍历后找-key并将count加上所得的map值
```
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> hash = new HashMap<>();
        int count = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                hash.compute(i + j, (key, value) -> value == null ? 1 : value + 1);
            }
        }
        for (int k : nums3) {
            for (int l : nums4) {
                count = count + hash.getOrDefault(-(k + l), 0);
            }
        }
        return count;
    }
```

# 字符串
## 344 反转字符串 easy 
双指针
```
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
```
## 541 反转字符串2 easy 
注意遍历时是2k递增的，每次处理时要判断end索引值是否够k个元素，若不是则直接取最后一个元素
```
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            int start = i;
            int end = Math.min(start + k - 1, arr.length - 1);
            while (start < end) {
                char tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
                start++;
                end--;
            }
        }
        return new String(arr);
    }
```
## 151 反转字符串里的单词 middle 
比较简单的做法是对字符串进行trim split转换后再倒序添加到新的字符串，但空间复杂度高。还可以用双指针，1.去除首尾以及中间多余空格 2.反转整个字符串 3.反转各个单词
```
//简单的做法，使用java内置方法
    public String reverseWords(String s) {
        List<String> list = Arrays.asList(s.trim().split("( )+"));
        String result = "";
        for (int i = list.size() - 1; i > 0; i--) {
            result += list.get(i).trim() + " ";
        }
        result += list.get(0).trim();
        return result;
    }
//双指针
    public String reverseWords(String s) {
        // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }
```
## 459 重复的子字符串 easy
可以用kmp算法，但比较复杂，直接归纳出特性然后判断
```
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
```

# 栈
先进后出，擅长相邻元素的消除
## 232 用两个栈实现队列 easy
``` 
    class MyQueue {
        Deque<Integer> inStack;
        Deque<Integer> outStack;
    
        public MyQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }
        
        public void push(int x) {
            inStack.push(x);
        }
        
        public int pop() {
            if (outStack.isEmpty()) {
                in2Out();
            }
            return outStack.pop();
        }
        
        public int peek() {
            if (outStack.isEmpty()) {
                in2Out();
            }
            return outStack.peek();
        }
        
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }
    
        private void in2Out() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
```
## 225 用一个队列模拟栈 easy
```
    class MyStack {
        Queue<Integer> queue;
    
        public MyStack() {
            queue = new ArrayDeque<>();
        }
        
        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            while (--size > 0) {
                queue.offer(queue.poll());
            }
        }
        
        public int pop() {
            return queue.poll();
        }
        
        public int top() {
            return queue.peek();
        }
        
        public boolean empty() {
            return queue.isEmpty();
        }
    }
```
## 20 有效的括号 easy
```
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c.equals('(') || c.equals('[') || c.equals('{')) stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                Character top = stack.pop();
                if (c.equals(')') && !top.equals('(')) return false;
                if (c.equals(']') && !top.equals('[')) return false;
                if (c.equals('}') && !top.equals('{')) return false;
            }
        }
        return stack.isEmpty();
    }
```
## 1047 删除字符串中的所有相邻重复项 easy
```
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        String result = "";
        for (Character c : s.toCharArray()) {
                if (!stack.isEmpty() && stack.peek().equals(c)) stack.pop();
                else stack.push(c);
        }
        while (!stack.isEmpty()) {
            result += stack.removeLast();
        }
        return result;
    }
```
## 150 逆波兰表达式 middle
即后缀表达式，二叉树的后序遍历
```
    public int evalRPN(String[] tokens) {
        Deque<String> stack = new ArrayDeque<>();
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        map.put("+", (x, y) -> x + y);
        map.put("-", (x, y) -> x - y);
        map.put("*", (x, y) -> x * y);
        map.put("/", (x, y) -> x / y);
        for (String token : tokens) {
            if (!map.containsKey(token)) stack.push(token);
            else {
                Integer y = Integer.valueOf(stack.pop());
                Integer x = Integer.valueOf(stack.pop());
                stack.push(String.valueOf(map.get(token).apply(x, y)));
            }
        }
        return Integer.valueOf(stack.pop());
    }
```