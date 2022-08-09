package top.mylady.api.util.myUtils;

import java.util.Random;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/18 16:52
 * @Description:
 */
public class GeneratorSomething {


    /**
     * 生成随机字符串
     */
    public static String genRandomStr() {
        String str = "0123456789!@#$%^&*()_=+|><?abcdefghijklmnopqrstuvwxyz!@#$%^&*()_=+|><?ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_=+|><?0123456789";
        Random random1 = new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number = random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }


    public static String genRandomStr(int lenght) {
        String str = "0123456789!@#$%^&*()_=+|><?abcdefghijklmnopqrstuvwxyz!@#$%^&*()_=+|><?ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_=+|><?0123456789";
        Random random1 = new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lenght; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number = random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = genRandomStr();
        System.out.println(s.length());
        System.out.println("s: "+ s);

    }

}
