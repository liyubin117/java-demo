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
滑动窗口，注意j是窗口的终止位置。具体：每一轮迭代，将 nums[end] 加到 sum ，如果 sum >= s ，则更新子数组的最小长度（此时子数组的长度是 end−start+1），然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum<s，在此过程中同样更新子数组的最小长度。在每一轮迭代的最后，将 end 右移。
```
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0, sum = 0, result = Integer.MAX_VALUE;
        for(; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
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

# 栈和队列
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
## 239 滑动窗口最大值 hard
使用优先级队列最大堆可以解决，元素是数组值、下标结构，堆顶元素即为当前堆的最大值，先初始化第一个窗口的值，再加入新的值到队列，循环判断当前堆顶元素这是否在窗口中，在则直接返回，不在则删除堆顶元素。其实还能用双端队列，但比较难理解
```
    public int[] maxSlidingWindow(int[] nums, int k) {
        Queue<int[]> queue = new PriorityQueue<>((p1, p2) -> p1[0] == p2[0] ? p2[1] - p1[1] : p2[0] - p1[0]);
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        res[index] = queue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            while (queue.peek()[1] < i - k + 1) queue.poll();
            res[++index] = Math.max(nums[i], queue.peek()[0]);
        }
        return res;
    }
```
## 347 前k个高频元素 middle
使用最小堆解决，元素是一个Map.Entry，键是数组值，值是出现次数
```
    public int[] topKFrequent(int[] nums, int k) {
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((p1, p2) -> p1.getValue() - p2.getValue());
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        int index = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) queue.offer(entry);
            else if (entry.getValue() > queue.peek().getValue()) {
                queue.poll();
                queue.offer(entry);
            }
        }
        for (int i = 0; i < k; i++) {
            res[index++] = queue.poll().getKey();
        }
        return res;
    }
```
# 递归
## 144 二叉树的前序遍历 easy
```
    //递归 中左右
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            recurse(root, result);
            return result;
        }
    
        private void recurse(TreeNode cur, List<Integer> result) {
            if (cur == null) return;
            result.add(cur.val);
            recurse(cur.left, result);
            recurse(cur.right, result);
        }
    }
    //迭代 中右左，因为栈是先进后出
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node != null) result.add(node.val);
            else continue;
            stack.push(node.right);
            stack.push(node.left);
        }
        return result;
    }
```
## 145 二叉树的前序遍历 easy
若用迭代栈，后序遍历是先中左右再双指针进行翻转
```
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node != null) result.add(node.val);
            else continue;
            stack.push(node.left);
            stack.push(node.right);
        }
        int left = 0, right = result.size() - 1;
        while (left <= right) {
            int temp = result.get(left);
            result.set(left, result.get(right));
            result.set(right, temp);
            left++;
            right--;
        }
        return result;
    }
```
## 95 二叉树的中序遍历 easy
若用迭代，循环迭代左侧节点入栈，直到无左侧节点时将当前节点定位到栈的顶端元素即父节点，将其出栈，再将当前节点定位到右孩子节点。直到栈无元素或当前节点为空
```
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
           if (cur != null){
               stack.push(cur);
               cur = cur.left;
           }else{
               cur = stack.pop();
               result.add(cur.val);
               cur = cur.right;
           }
        }
        return result;
    }
```
## 102 二叉树的层序遍历 middle
可用递归和迭代队列两种方式实现BFS，递归更容易理解
```
    class Solution {
        private List<List<Integer>> resList = new ArrayList<List<Integer>>();
    
        public List<List<Integer>> levelOrder(TreeNode root) {
            recurse(root,0);
            //checkFun02(root);
            return resList;
        }
    
        //BFS--递归方式
        private void recurse(TreeNode root, Integer deep) {
            if (root == null) return;
            deep++;
            if (res.size() < deep) {
                res.add(new ArrayList<Integer>());
            }
            res.get(deep - 1).add(root.val);
            recurse(root.left, deep);
            recurse(root.right, deep);
        }
    
        //BFS--迭代方式--借助队列
        public void checkFun02(TreeNode node) {
            if (node == null) return;
            Queue<TreeNode> que = new LinkedList<TreeNode>();
            que.offer(node);
    
            while (!que.isEmpty()) {
                List<Integer> itemList = new ArrayList<Integer>();
                int len = que.size();
    
                while (len > 0) {
                    TreeNode tmpNode = que.poll();
                    itemList.add(tmpNode.val);
    
                    if (tmpNode.left != null) que.offer(tmpNode.left);
                    if (tmpNode.right != null) que.offer(tmpNode.right);
                    len--;
                }
    
                resList.add(itemList);
            }
        }
    }
```
## 226 翻转二叉树 easy
使用前序遍历或后序遍历比较容易。若用中序遍历，recurse(left) swap(node) recurse(left) 
```
    public TreeNode invertTree(TreeNode root) {
        recurse(root);
        return root;
    }

    private void recurse(TreeNode node) {
        if (node == null) return;
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        recurse(node.left);
        recurse(node.right);
    }
```
## 101 对称二叉树 easy
只能使用后序遍历，需要先收集孩子的信息再向上一层返回。若外侧、内侧都对应相同则是对称的
```
    public boolean isSymmetric(TreeNode root) {
        return recurse(root.left, root.right);
    }
    private boolean recurse(TreeNode left, TreeNode right) {
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        if (left == null && right == null) return true;
        if (left.val != right.val) return false;
        boolean outer = recurse(left.left, right.right);
        boolean inner = recurse(left.right, right.left);
        if (outer && inner) return true;
        else return false;
    }
```
## 104 二叉树的最大深度 easy
后序遍历，找出左右孩子的较大高度+1后即为该节点的高度，根节点的高度是这个二叉树的最大深度。
```
    class Solution {
        int result = 0;
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
```
## 111 二叉树的最小深度 easy
后序遍历，根节点的最小高度即这个二叉树的最小深度。若左右孩子有为空的，则找左右孩子较大深度+1作为该节点的高度，若左右孩子都非空，则找左右孩子较小深度+1作为该节点的高度
```
class Solution {
    int result = 0;
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null || root.right == null) return Math.max(leftDepth, rightDepth) + 1;
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
```
## 222 完全二叉树的的节点个数 easy
后序遍历，找出左右孩子的节点个数再加1，即当前节点所在子树的节点个数
```
    class Solution {
        int result = 0;
        public int countNodes(TreeNode root) {
            if (root == null) return 0;
            int left = countNodes(root.left);
            int right = countNodes(root.right);
            return result = left + right + 1;
        }
    }
```
## 110 平衡二叉树 easy
后序遍历，在求节点高度的过程中，判断左右节点高度差是否小于2，若不是则直接返回-1即不符合平衡二叉树，其判断不符合的依据是高度差大于1、左节点已不符合、右节点已不符合
```
    class Solution {
        public boolean isBalanced(TreeNode root) {
            return getHeight(root) != -1 ? true : false;
        }
        private int getHeight(TreeNode node) {
            if (node == null) return 0;
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
            int height = Math.max(leftHeight, rightHeight) + 1;
            return height;
        }
    }
```
## 257 二叉树的所有路径 easy
前序遍历+回溯
```
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        recurse(root, new ArrayList<String>(), result);
        return result;
    }
    private void recurse(TreeNode node, List<String> path, List<String> result) {
        path.add(String.valueOf(node.val)); //中序遍历
        if (node.left == null && node.right == null) result.add(String.join("->", path)); //遇到叶节点
        if (node.left != null) {
            recurse(node.left, path, result);
            path.remove(path.size() - 1); //回溯
        }
        if (node.right != null) {
            recurse(node.right, path, result);
            path.remove(path.size() - 1); //回溯
        }
    }
```
## 404 左叶子之和 easy
后序遍历，当中间节点满足左孩子是左叶节点时，加上左叶节点的值
```
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        return sumOfLeftLeaves(root.left) 
            + sumOfLeftLeaves(root.right) 
            + (root.left!=null && root.left.left==null && root.left.right==null ? root.left.val : 0);
    }
```
## 513 找树左下角的值 middle
前中后序遍历都行，因为都是先遍历左节点再遍历右节点。两个全局变量：一个记录最大深度maxDepth，一个记录结果result。终止条件是叶子节点，若此时深度大于最大深度，则更新maxDepth和result
```java
    class Solution {
        private int maxDepth = Integer.MIN_VALUE;
        private int result = 0;
    
        public int findBottomLeftValue(TreeNode root) {
            recurse(root, 1);
            return result;
        }
    
        private void recurse(TreeNode node, int depth) {
            if (node.left == null && node.right == null && depth > maxDepth) {
                maxDepth = depth;
                result = node.val;
            }
            if (node.left != null) {
                depth++;
                recurse(node.left, depth);
                depth--; //回溯
            }
            if (node.right != null) {
                depth++;
                recurse(node.right, depth);
                depth--; //回溯
            }
        }
    }
```
## 112 路径总和 easy
前序遍历，终止条件是叶子节点时的sum等于目标值返回true，注意回溯。原理和257找出所有路径类似
```java
    class Solution {
        private int sum = 0;
        private boolean flag = false;
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            return recurse(root, targetSum);
        }
        private boolean recurse(TreeNode node, int targetSum) {
            sum += node.val;
            if (node.left == null && node.right == null && sum == targetSum) flag = true;
            if (node.left != null) {
                hasPathSum(node.left, targetSum);
                sum -= node.left.val;
            }
            if (node.right != null) {
                hasPathSum(node.right, targetSum);
                sum -= node.right.val;
            }
            return flag;
        }
    }
```
## 106 从中序与后序遍历序列构造二叉树 middle
先从后序最后一个节点确定根节点，再从中序切割出左右，再从后序切割出中，注意区间统一是左闭右开，迭代确定二叉树

第一步：如果数组大小为零的话，说明是空节点了。

第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。

第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点

第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）

第五步：切割后序数组，切成后序左数组和后序右数组

第六步：递归处理左区间和右区间
```java
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();  // 方便根据数值查找位置
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) { // 用map保存中序序列的数值对应位置
                map.put(inorder[i], i);
            }
            return findNode(inorder, 0, inorder.length, postorder, 0, postorder.length);  // 前闭后开
        }
    
        public TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
            // 参数里的范围都是前闭后开
            if (inBegin >= inEnd || postBegin >= postEnd) {  // 不满足左闭右开，说明没有元素，返回空树
                return null;
            }
            int rootIndex = map.get(postorder[postEnd - 1]);  // 找到后序遍历的最后一个元素在中序遍历中的位置
            TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
            int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数
            root.left = findNode(inorder, inBegin, rootIndex,
                                postorder, postBegin, postBegin + lenOfLeft); //用左中序、左后序构造左子树
            root.right = findNode(inorder, rootIndex + 1, inEnd,
                                postorder, postBegin + lenOfLeft, postEnd - 1); //用右中序、右后序构造右子树
            return root;
        }
    }
```

# 模拟
## 2899 上一个遍历的整数 easy
```
    public List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>(), ans = new ArrayList<>();
        int index = 1;
        boolean flag = true;
        for (int num : nums) {
            if (num != -1) {
                seen.add(num);
                flag = false;
                index = 1;
            } else {
                if (flag) index++;
                int pos = seen.size() - index;
                ans.add(pos >= 0 ? seen.get(pos) : -1);
                flag = true;
            }
        }
        return ans;
    }
```