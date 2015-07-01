package com.volkoval.jest.leetcode.versionnumbers;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 29.12.14
 * Time: 21:08
 */
public class LeetCodeProblems {

    private static final double EPS = 0.00000000001;

    public int compareVersion(String version1, String version2) {
        String[] v1Str = version1.split("\\.");
        String[] v2Str = version2.split("\\.");

        int minLen = v2Str.length > v1Str.length ?
                v1Str.length : v2Str.length;
        for (int k = 0; k < minLen; ++k) {
            int v1 = Integer.valueOf(v1Str[k]);
            int v2 = Integer.valueOf(v2Str[k]);

            if (v1 != v2) {
                return v1 > v2 ?
                        1 : -1;
            }
            else if (v1 != v2) {
                return v1 > v2 ?
                        1 : -1;
            }
        }
        if (v1Str.length == v2Str.length) {
            return 0;
        }
        int flag = v1Str.length > v2Str.length ?
                    1 : -1;
        if (flag == 1) {
            if (Integer.valueOf(v1Str[minLen]) > 0) {
                return flag;
            }
        }
        else {
            if (Integer.valueOf(v2Str[minLen]) > 0) {
                return flag;
            }
        }
        return 0;
    }

    @Test
    @Ignore
    public void testCompareVersion() {
        Assert.assertEquals("fail", 1, compareVersion("10.6.5", "10.6"));
        Assert.assertEquals("fail", 0, compareVersion("1", "1.0"));
        Assert.assertEquals("fail", -1, compareVersion("1.0", "1.1"));
        Assert.assertEquals("fail", -1, compareVersion("1.1", "13.0"));
        Assert.assertEquals("fail", -1, compareVersion("0.1", "1.0"));
        Assert.assertEquals("fail", 0, compareVersion("2.3", "2.3"));
        Assert.assertEquals("fail", 1, compareVersion("1.1", "1.0"));
        Assert.assertEquals("fail", -1, compareVersion("1.1", "2"));
        Assert.assertEquals("fail", 0, compareVersion("2", "2"));
    }

    public int trailingZeroes(int n) {
        int len = 0;
        for (long i = 5; i <= n; i *= 5) {
            len += n / i;
        }
        return len;
    }

    @Test
    @Ignore
    public void testTrailingZeroes() {
        Assert.assertEquals("fail", 1, trailingZeroes(5));
        Assert.assertNotEquals("fail", 72, trailingZeroes(2147483647));
        Assert.assertEquals("fail", 72, trailingZeroes(295));
        Assert.assertEquals("fail", 6, trailingZeroes(25));
        Assert.assertEquals("fail", 49, trailingZeroes(200));
        Assert.assertEquals("fail", 0, trailingZeroes(0));
        Assert.assertEquals("fail", 0, trailingZeroes(1));
        Assert.assertEquals("fail", 0, trailingZeroes(2));
        Assert.assertEquals("fail", 0, trailingZeroes(3));
        Assert.assertEquals("fail", 0, trailingZeroes(4));
        Assert.assertEquals("fail", 1, trailingZeroes(6));
        Assert.assertEquals("fail", 1, trailingZeroes(7));
        Assert.assertEquals("fail", 1, trailingZeroes(8));
        Assert.assertEquals("fail", 2, trailingZeroes(10));
        Assert.assertEquals("fail", 2, trailingZeroes(11));
        Assert.assertEquals("fail", 2, trailingZeroes(12));
        Assert.assertEquals("fail", 2, trailingZeroes(13));
        Assert.assertEquals("fail", 2, trailingZeroes(14));
        Assert.assertEquals("fail", 3, trailingZeroes(15));
        Assert.assertEquals("fail", 3, trailingZeroes(16));
        Assert.assertEquals("fail", 3, trailingZeroes(17));
        Assert.assertEquals("fail", 3, trailingZeroes(18));
        Assert.assertEquals("fail", 3, trailingZeroes(19));
        Assert.assertEquals("fail", 4, trailingZeroes(20));
        Assert.assertEquals("fail", 4, trailingZeroes(21));
        Assert.assertEquals("fail", 4, trailingZeroes(22));
        Assert.assertEquals("fail", 4, trailingZeroes(23));
        Assert.assertEquals("fail", 4, trailingZeroes(24));
        Assert.assertEquals("fail", 6, trailingZeroes(26));
        Assert.assertEquals("fail", 6, trailingZeroes(27));
        Assert.assertEquals("fail", 6, trailingZeroes(28));
        Assert.assertEquals("fail", 6, trailingZeroes(29));
        Assert.assertEquals("fail", 7, trailingZeroes(30));
        Assert.assertEquals("fail", 7, trailingZeroes(31));
        Assert.assertEquals("fail", 7, trailingZeroes(32));
        Assert.assertEquals("fail", 7, trailingZeroes(33));
        Assert.assertEquals("fail", 7, trailingZeroes(34));
        Assert.assertEquals("fail", 8, trailingZeroes(35));
        Assert.assertEquals("fail", 8, trailingZeroes(36));
        Assert.assertEquals("fail", 8, trailingZeroes(37));
        Assert.assertEquals("fail", 8, trailingZeroes(38));
        Assert.assertEquals("fail", 8, trailingZeroes(39));
        Assert.assertEquals("fail", 9, trailingZeroes(40));
        Assert.assertEquals("fail", 9, trailingZeroes(41));
        Assert.assertEquals("fail", 9, trailingZeroes(42));
        Assert.assertEquals("fail", 9, trailingZeroes(43));
        Assert.assertEquals("fail", 9, trailingZeroes(44));
        Assert.assertEquals("fail", 10, trailingZeroes(45));
        Assert.assertEquals("fail", 10, trailingZeroes(46));
        Assert.assertEquals("fail", 10, trailingZeroes(47));
        Assert.assertEquals("fail", 10, trailingZeroes(48));
        Assert.assertEquals("fail", 10, trailingZeroes(49));
        Assert.assertEquals("fail", 12, trailingZeroes(50));
        Assert.assertEquals("fail", 12, trailingZeroes(51));
        Assert.assertEquals("fail", 12, trailingZeroes(52));
        Assert.assertEquals("fail", 12, trailingZeroes(53));
        Assert.assertEquals("fail", 12, trailingZeroes(54));
        Assert.assertEquals("fail", 13, trailingZeroes(55));
        Assert.assertEquals("fail", 13, trailingZeroes(56));
        Assert.assertEquals("fail", 13, trailingZeroes(57));
        Assert.assertEquals("fail", 13, trailingZeroes(58));
        Assert.assertEquals("fail", 13, trailingZeroes(59));
        Assert.assertEquals("fail", 14, trailingZeroes(60));
        Assert.assertEquals("fail", 14, trailingZeroes(61));
        Assert.assertEquals("fail", 14, trailingZeroes(62));
        Assert.assertEquals("fail", 14, trailingZeroes(63));
        Assert.assertEquals("fail", 14, trailingZeroes(64));
        Assert.assertEquals("fail", 15, trailingZeroes(65));
        Assert.assertEquals("fail", 15, trailingZeroes(66));
        Assert.assertEquals("fail", 15, trailingZeroes(67));
        Assert.assertEquals("fail", 15, trailingZeroes(68));
        Assert.assertEquals("fail", 15, trailingZeroes(69));
        Assert.assertEquals("fail", 16, trailingZeroes(70));
        Assert.assertEquals("fail", 16, trailingZeroes(71));
        Assert.assertEquals("fail", 16, trailingZeroes(72));
        Assert.assertEquals("fail", 16, trailingZeroes(73));
        Assert.assertEquals("fail", 16, trailingZeroes(74));
        Assert.assertEquals("fail", 18, trailingZeroes(75));
    }

    public int titleToNumber(String s) {
        int firstAlpha = 'A' - 1;
        int len = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i > 0) {
                len *= 26;
            }
            len += s.charAt(i) - firstAlpha;
        }
        return len;
    }

    @Test
    @Ignore
    public void testTitleToNumber() {
        Assert.assertEquals("fail", 53, titleToNumber("BA"));
        Assert.assertEquals("fail", 1, titleToNumber("A"));
        Assert.assertEquals("fail", 2, titleToNumber("B"));
        Assert.assertEquals("fail", 26, titleToNumber("Z"));
        Assert.assertEquals("fail", 28, titleToNumber("AB"));
    }

    public String convertToTitle(int n) {
        int firstAlpha = 'A' - 1;
        StringBuilder title = new StringBuilder();
        while (n > 0) {
            int letter = n % 26 == 0 ?
                    26 : n % 26;
            title.append(Character.toChars(firstAlpha + letter));
            n = (n - 1) / 26;
        }
        return title.reverse().toString();
    }

    @Test
    @Ignore
    public void testConvertToTitle() {
        Assert.assertEquals("fail", "AAA", convertToTitle(703));
        Assert.assertEquals("fail", "BA", convertToTitle(53));
        Assert.assertEquals("fail", "A", convertToTitle(1));
        Assert.assertEquals("fail", "B", convertToTitle(2));
        Assert.assertEquals("fail", "Z", convertToTitle(26));
        Assert.assertEquals("fail", "AB", convertToTitle(28));
    }

    public int majorityElement(int[] num) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < num.length; ++i) {
            if (numMap.get(num[i]) == null) {
                numMap.put(num[i], 0);
            }
            numMap.put(num[i], numMap.get(num[i]) + 1);
        }
        TreeMap<Integer, Integer> reverseMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 > o1 ?
                        1 : o2 < o1 ? -1 : 0;
            }
        });
        for (Map.Entry<Integer, Integer> entry :  numMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap.firstEntry().getValue();
    }

    @Test
    @Ignore
    public void testMajorityElement() {
        Assert.assertEquals("fail", 1, majorityElement(new int[]{1, 2, 3, 1, 1, 1, 1, 3,}));
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 1, lenB = 1;
        ListNode iterA = headA, iterB = headB;
        for (; iterA.next != null || iterB.next != null;) {
            if (iterA.next != null) {
                iterA = iterA.next;
                lenA++;
            }
            if (iterB.next != null) {
                iterB = iterB.next;
                lenB++;
            }
        }
        if (iterA.val != iterB.val) {
            return null;
        }
        int minLen = lenB > lenA ?
                lenA : lenB;
        int maxLen = lenA > lenB ?
                lenA : lenB;
        ListNode longestList = lenA > lenB ?
                headA : headB;
        ListNode shortestList = lenB > lenA ?
                headA : headB;
        while (maxLen > minLen) {
            maxLen--;
            longestList = longestList.next;
        }
        while (longestList.val != shortestList.val) {
            longestList = longestList.next;
            shortestList = shortestList.next;
        }
        return longestList;
    }

    @Test
    public void testGetIntersectionNode() {
        ListNode first = new ListNode(10), second = new ListNode(10);
        ListNode node1 = first;
        for (int i : new int[] {11, 12, 13, 14, 15, 16, 17, 18}) {
            node1.next = new ListNode(i);
            node1 = node1.next;
        }
        ListNode node2 = second;
        for (int i : new int[] {31, 32, 33, 34, 35, 36, 37, 38}) {
            node2.next = new ListNode(i);
            node2 = node2.next;
        }
        for (int i : new int[] {51, 52, 53, 54, 55, 56, 57, 59}) {
            node1.next = new ListNode(i);
            node1 = node1.next;
            node2.next = new ListNode(i);
            node2 = node2.next;
        }
        node1.next = new ListNode(59);
        Assert.assertNotNull("fail", getIntersectionNode(first, second));
    }
}
