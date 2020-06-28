package com.test.record;

/**
 * @ClassName SentenceReverse
 * @Author majp
 * @Description 句子反转，不需要额外空间
 * @Date 2020-06-28 0028 21:49
 * Version 1.0
 **/
public class SentenceReverse {
    public static void reverseCharArr(char[] arr, int start, int end) {
        if (end <= start) {
            return;
        }
        int i = start;
        int j = end;
        while (i < j) {
            // 位操作交换两个字符
            arr[i] = (char) (arr[i] ^ arr[j]);
            arr[j] = (char) (arr[i] ^ arr[j]);
            arr[i] = (char) (arr[i] ^ arr[j]);
            i++;
            j--;
        }
    }

    public static String reverseSentence(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        char[] arr = str.toCharArray();
        int len = arr.length;
        // 先反转整个句子
        reverseCharArr(arr, 0, len - 1);
        // 反转每个单词
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == ' ') {
                reverseCharArr(arr, index, i - 1);
                index = i + 1;
            }else if(i==len-1){
                reverseCharArr(arr,index,i);
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String str = "what are you doing";
        System.out.println(reverseSentence(str));
    }

}
