package com.learning.leetcode.test;

import com.learning.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 *
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode329LongestIncreasingPathTest extends BaseTest {

    @Test
    public void helper(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                logger.info("key:{}", getKey(i, j));
            }
        }
    }

    @Test
    public void test(){
        int[][] matrix = {  {2, 4, 5},
                            {3, 2, 6},
                            {2, 2, 1}
                        };

        assert 4 == longestIncreasingPath(matrix);
    }

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int column = matrix[0].length;

        int num = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                num = Math.max(num, helper(matrix, i, j));
            }
        }
        return num;
    }

    private long getKey(int i, int j){
        return (long) i << 32 | j;
    }

    private static Map<Long, Integer> map = new HashMap<>();

    public int helper(int[][] matrix, int i, int j) {
        long key = (long) i << 32 | j;//计算以当前坐标为起点的组合键
        if (map.containsKey(key)) {
            //如果当前答案已经计算过
            return map.get(key);
        }
        int ans = 1;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {//向上
            ans = Math.max(ans, helper(matrix, i - 1, j) + 1);
            logger.info("向上, i={}, j={}, ans={}", i, j, ans);
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {//向下
            ans = Math.max(ans, helper(matrix, i + 1, j) + 1);
            logger.info("向下, i={}, j={}, ans={}", i, j, ans);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {//向左
            ans = Math.max(ans, helper(matrix, i, j - 1) + 1);
            logger.info("向左, i={}, j={}, ans={}", i, j, ans);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {//向右
            ans = Math.max(ans, helper(matrix, i, j + 1) + 1);
            logger.info("向右, i={}, j={}, ans={}", i, j, ans);
        }
        map.put(key, ans);//记录当前答案
        return ans;
    }
}
