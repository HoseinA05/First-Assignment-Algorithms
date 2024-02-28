import java.util.*;

public class Exercises2 {

    /*
    Given an array of integers nums and an integer target, return indices of the two numbers
    such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.
    */

    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }

        return null;
    }

    /*
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000

    For example, 2 is written as II in Roman numeral, just two ones added together.
    12 is written as XII, which is simply X + II.
    The number 27 is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right.
    However, the numeral for four is not IIII.
    Instead, the number four is written as IV.
    Because the one is before the five we subtract it making four.
    The same principle applies to the number nine, which is written as IX.
    There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.

    Given a roman numeral, convert it to an integer.
    */

    public int romanToInt(String s) {
        int sum = 0;

        for (int i = 0; i < s.length()-1; i++) {
            int letterValue = romanLetterToInt(s.charAt(i));
            if(letterValue >= romanLetterToInt(s.charAt(i+1)))
                sum += letterValue;
            else
                sum -= letterValue;
        }
        sum += romanLetterToInt(s.charAt(s.length()-1));
        return sum;
    }

    private int romanLetterToInt(char c){
        c = Character.toLowerCase(c);
        switch (c){
            case 'i':
                return 1;
            case 'v':
                return 5;
            case 'x':
                return 10;
            case 'l':
                return 50;
            case 'c':
                return 100;
            case 'd':
                return 500;
            case 'm':
                return 1000;
            default:
                return 0;
        }
    }

    /*
    Given an array nums of distinct integers, return all the possible permutations.
    You can return the answer in any order.
    */

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        permuteRecur(nums, new ArrayList<>(), new boolean[nums.length], result);

        return result;
    }

    private void permuteRecur(int[] nums, List<Integer> curr, boolean[] used, List<List<Integer>> result){
        if(curr.size() == nums.length){
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
//  Avoiding Duplicate nums And repeated permutes(nums is sorted).
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue; // Skip duplicates
            }
            used[i] = true;
            curr.add(nums[i]);
            permuteRecur(nums, curr, used, result);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        Exercises2 ex = new Exercises2();

// Test 1
//         System.out.print(Arrays.toString(ex.twoSum(new int[]{1, 2, 4, 5, 6, 7}, 13)));

// Test 2
//        System.out.print(ex.romanToInt("Xl"));

//  Test 3
//        System.out.print(ex.permute(new int[]{1, 5, 5}));
    }
}