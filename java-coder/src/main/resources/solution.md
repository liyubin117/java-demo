### 解题思路记录

#### 数组
1. 27 移除元素 easy 快慢指针，快指针指向要保留的元素值，慢指针指向更新后的数组的索引
2. 977 有序数组的平方 easy 双指针，选较大的那个值逆序放到结果集并移动指针
3. 209 长度最小的子数组 middle 滑动窗口，注意j是窗口的终止位置
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
4. 59 螺旋矩阵2 middle 边界是left right top bottom，循环到n*n次赋值结束
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
#### 链表
1. LCR 136 删除链表的节点 easy 虚拟头节点next指针指向head节点，即可使用统一的方式遍历删除指定节点
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
2. 707 设计链表 middle 
3. 19 删除链表倒数第N个节点 快慢指针 构建dummyHead虚拟头节点，快慢指针都指向该dummyHead，先让快指针跑N个节点，慢指针再开始跑，直到快指针到null，此时慢指针指向的就是待删除节点的前一个节点，再让慢指针next指向next.next，再返回虚拟头节点next
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
        // 具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }
```