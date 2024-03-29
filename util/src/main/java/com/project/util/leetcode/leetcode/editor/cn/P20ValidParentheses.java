//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2163 👎 0


package com.project.util.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

//Java：有效的括号
class P20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        solution.isValid("([[][]{({}({}))}])");
        System.out.println(Math.pow(10,4));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if (s.length() < 1 || s.length() > Math.pow(10,4)) {return false;}
            int len = s.length()/2;
            for (int i = 0; i <= len; i++) {
                if (s.contains("()")) {
                    s = s.replace("()", "");
                }
                if (s.contains("{}")) {
                    s = s.replace("{}", "");
                }
                if (s.contains("[]")) {
                    s = s.replace("[]", "");
                }
            }
            return s.length() == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}