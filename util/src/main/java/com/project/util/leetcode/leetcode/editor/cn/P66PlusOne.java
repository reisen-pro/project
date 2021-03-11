//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 
// 👍 637 👎 0


package com.project.util.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：加一
public class P66PlusOne {
    public static void main(String[] args) {
        Solution solution = new P66PlusOne().new Solution();
        System.out.println(Arrays.toString(solution.plusOne(new int[]{8, 9, 9})));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            boolean flag = true;
            for (int digit : digits) {
                if (digit != 9) flag = false;
            }
            // 全是9
            if (flag) {
                int[] result = new int[digits.length + 1];
                for (int i = 1; i < result.length; i++) {
                    result[i] = 0;
                }
                result[0] = 1;
                return result;
            }else {
                int len = digits.length - 1;
                int count = 0;
                if (digits[len] == 9) {
                    for (int j = len; j > 0; j--) {
                        if (digits[j] == 9) {
                            count ++;
                        }else {
                            break;
                        }
                    }
                    for (int i = 0; i < count ; i++) {
                        digits[len - i] = 0;
                    }
                    digits[len-count] = digits[len-count] + 1;
                }else {
                    digits[len] = digits[len] + 1;
                }
            }
            return digits;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}