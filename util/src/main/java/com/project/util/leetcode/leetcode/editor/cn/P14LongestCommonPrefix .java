//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1462 👎 0


package com.project.util.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//Java：最长公共前缀
class P14LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0 || strs.length > 200) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }

            HashMap<Integer, String> map = new HashMap<Integer, String>();
            TreeSet<Integer> set = new TreeSet<Integer>();
            for (String str : strs) {
                set.add(str.length());
                map.put(str.length(), str);
            }
            // 最小的字符串
            String minStr = map.get(set.first());
            int i = 1;
            int k = 0;
            int l = strs.length;
            for (String str : strs) {
                String result = minStr.substring(0, i);
                k++;
                if (k%l == 0) {i++;}
                if (!str.startsWith(result)) {
                    return str.substring(0,i-1);
                }
            }
            return "";
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}