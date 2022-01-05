//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 数组 👍 784 👎 0


package com.project.util.leetcode.leetcode.editor.cn;

//只出现一次的数字 II

import java.util.HashMap;

public class P137SingleNumberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P137SingleNumberIi().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            HashMap<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                if (!map.containsKey(num)) map.put(num, 0);
                else map.put(num, map.get(num) + 1);
            }
            for (Integer num : map.keySet()) {
                if (map.get(num) == 0)
                    result = num;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}