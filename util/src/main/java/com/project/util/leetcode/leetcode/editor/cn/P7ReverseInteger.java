//给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2533 👎 0


package com.project.util.leetcode.leetcode.editor.cn;
//Java：整数反转
class P7ReverseInteger{
    public static void main(String[] args) {
        Solution solution = new P7ReverseInteger().new Solution();
        solution.reverse(1534236469);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = chars.length -1; i >= 0 ; i--) {
            if (chars[i] != 0){
                result.append(chars[i]);
            }
            if (chars[i] == '-'){
                result = new StringBuilder("-").append(result);
                result.replace(result.length() - 1, result.length(), "");
            }
        }
        return Long.parseLong(result.toString()) < Integer.MIN_VALUE
                || Long.parseLong(result.toString()) > Integer.MAX_VALUE ? 0 : Integer.parseInt(result.toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}