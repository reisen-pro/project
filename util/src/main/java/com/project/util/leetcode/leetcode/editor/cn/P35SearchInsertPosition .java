//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 822 👎 0


package com.project.util.leetcode.leetcode.editor.cn;
//Java：搜索插入位置
class P35SearchInsertPosition{
    public static void main(String[] args) {
        Solution solution = new P35SearchInsertPosition().new Solution();
        solution.searchInsert(new int[]{1,3,5,6},5);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums[0] > target) {return 0;}
        if (nums[nums.length-1] < target) {
            return nums.length;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] < target && nums[i] > target) {
                return i;
            }
            if (nums[i-1] == target && nums[i] > target) {
                return i-1;
            }
            if (nums[i-1] < target && nums[i] == target) {
                return i;
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}