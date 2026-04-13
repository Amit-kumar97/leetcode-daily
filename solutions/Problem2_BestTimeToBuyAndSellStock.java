// ============================================================
// Problem : Best Time to Buy and Sell Stock (LeetCode #121)
// Difficulty: Medium
// Date     : 2026-04-13
// Link     : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// ============================================================
//
// 📝 PROBLEM STATEMENT:
// You are given an array prices[] where prices[i] is the price
// of a stock on the i-th day.
// You want to maximize profit by choosing ONE day to buy and
// ONE future day to sell.
// Return the maximum profit. If no profit is possible, return 0.
//
// Example:
//   Input : prices = [7, 1, 5, 3, 6, 4]
//   Output: 5  → Buy on day 2 (price=1), sell on day 5 (price=6)
// ============================================================


// -----------------------------------------------
// 🔴 APPROACH 1 — Brute Force
// -----------------------------------------------
// Idea: Try every pair (buy day, sell day) where sell > buy.
//       Calculate profit for each pair and track maximum.
//
// Time Complexity : O(n²) — two nested loops
// Space Complexity: O(1)
// -----------------------------------------------
class BestTimeBrute {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }
}


// -----------------------------------------------
// 🟢 APPROACH 2 — Optimized (Single Pass / Kadane's idea)
// -----------------------------------------------
// Idea: Track the MINIMUM price seen so far as we scan left to right.
//       At each day, calculate profit if we sell today
//       (today's price - min price so far).
//       Update maximum profit accordingly.
//
// Why it works: The best sell point uses the lowest buy point
//               before it. One pass is enough!
//
// Time Complexity : O(n) — single loop
// Space Complexity: O(1) — only two variables
// -----------------------------------------------
class BestTimeOptimized {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;  // Lowest buy price seen
        int maxProfit = 0;                 // Best profit so far

        for (int price : prices) {
            if (price < minPrice) {
                // Found a cheaper day to buy → update min
                minPrice = price;
            } else {
                // Calculate profit if we sell today
                int profit = price - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }

    // ✅ Test it
    public static void main(String[] args) {
        BestTimeOptimized sol = new BestTimeOptimized();

        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        // Expected: 5

        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));
        // Expected: 0 (prices only go down, no profit possible)
    }
}