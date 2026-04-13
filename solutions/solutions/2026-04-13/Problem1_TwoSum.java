// ============================================================
// Problem : Two Sum (LeetCode #1)
// Difficulty: Easy
// Date     : 2026-04-13
// Link     : https://leetcode.com/problems/two-sum/
// ============================================================
//
// 📝 PROBLEM STATEMENT:
// Given an array of integers nums and an integer target,
// return indices of the two numbers such that they add up to target.
// You may assume that each input has exactly one solution,
// and you may not use the same element twice.
//
// Example:
//   Input : nums = [2, 7, 11, 15], target = 9
//   Output: [0, 1]  → because nums[0] + nums[1] = 2 + 7 = 9
// ============================================================


// -----------------------------------------------
// 🔴 APPROACH 1 — Brute Force
// -----------------------------------------------
// Idea: Check every pair of numbers and see if they add up to target
//
// Time Complexity : O(n²) — two nested loops
// Space Complexity: O(1)  — no extra space used
// -----------------------------------------------
class TwoSumBrute {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};  // No solution found
    }
}


// -----------------------------------------------
// 🟡 APPROACH 2 — Better (Sorting + Two Pointer)
// -----------------------------------------------
// Idea: Sort the array and use two pointers from both ends.
// NOTE: This doesn't preserve original indices, so only use
//       when indices are NOT required.
//
// Time Complexity : O(n log n) — due to sorting
// Space Complexity: O(1)
// -----------------------------------------------
import java.util.Arrays;

class TwoSumTwoPointer {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target)      return new int[]{left, right};
            else if (sum < target)  left++;
            else                    right--;
        }
        return new int[]{};
    }
}


// -----------------------------------------------
// 🟢 APPROACH 3 — Optimized (HashMap)
// -----------------------------------------------
// Idea: For each number, calculate what complement is needed
//       (target - current). If complement already exists in
//       the map, we found our pair. Otherwise store current.
//
// Time Complexity : O(n) — single pass
// Space Complexity: O(n) — HashMap storage
// -----------------------------------------------
import java.util.HashMap;

class TwoSumOptimized {
    public int[] twoSum(int[] nums, int target) {
        // Map stores: number → its index
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If complement exists in map, we found the answer
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Otherwise, store current number and its index
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    // ✅ Test it
    public static void main(String[] args) {
        TwoSumOptimized sol = new TwoSumOptimized();
        int[] result = sol.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
        // Expected: [0, 1]
    }
}