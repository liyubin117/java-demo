package org.rick.coder;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * 二分查找
 * 前提是有序
 */
public class BinarySearch {
    public static void main(String[] args) {
        assertEquals(5, search(new int[]{-1,0,1,3,5,8,10}, 8));
        assertEquals(3, searchInsert(new int[]{1,2,4,8,16}, 5));
        assertEquals(4, searchInsert(new int[]{1,2,4,8,16}, 16));
        assertEquals(1, binarySearchPeakElement(new int[]{1,8,3,2,5,4}));
        int[][] arr = new int[3][4];
        arr[0] = new int[]{1,3,5,7};
        arr[1] = new int[]{10,11,16,20};
        arr[2] = new int[]{23,30,34,60};
        assertEquals(true, searchMatrix(arr, 3));
    }

    //704 二分查找
    private static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (r >= l) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    //35 搜索插入位置。给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    private static int searchInsert(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while (r >= l) {
            int mid = l + (r - l) / 2;;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    //162 寻找峰值
    //二分法，O(logN)
    private static int binarySearchPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    //此处应该用二分法，也用栈试了下可行，但时间复杂度O(N)
    private static int findPeakElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<nums.length; i++) {
            if (stack.isEmpty() || stack.peek() < nums[i]) {
                stack.push(nums[i]);
            } else if (stack.peek() > nums[i]) {
                break;
            }
        }
        return stack.size() - 1;
    }

    //74 二维数组二分查找
    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int l = 0, r = rows * cols - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int element = matrix[mid / cols][mid % cols];
            if (element == target) return true;
            if (element > target) r = mid - 1;
            else l = mid + 1;
        }
        return false;
    }
}
