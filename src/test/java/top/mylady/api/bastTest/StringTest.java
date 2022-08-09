package top.mylady.api.bastTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/13 17:22
 * @Description:
 */
public class StringTest {

    private static final Logger logger = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void test_5(){
        String fileName = "http://image.mylady.top/FiOUOplwiIcyDJumEj5EjOU-qZKO?e=1658495530&token=LfXjXvG1e6vPXV2UFLfXrJ14uNM792vqONhnLyB2:Odv-6NJztM5BIpxNIPoNIYRgPXU=";
        int len = fileName.length();
        if(fileName.length() >= 31){
            fileName = fileName.substring(len - 31, len);
            logger.info("切割后的fileName打印: {}", fileName);
        }
    }

    @Test
    public void test_4(){
        //134
        String imgUrl2 = "http://image.mylady.top/2022-07-20_153931.png?e=1658508911&token=LfXjXvG1e6vPXV2UFLfXrJ14uNM792vqONhnLyB2:MHMcNF2p9zELV0ZTyeCOBTHWRdA=";  //
        System.out.println(imgUrl2.length());

    }
    //正则校验
    @Test
    public void test_3(){
        String email_addr = "20181103@zyufl.edu.cn";

        String regular = "[a-zA-Z0-9]{3,20}@([a-zA-Z0-9]{2,10}|[a-zA-Z0-9]{2,10}[.][a-zA-Z0-9]{2,10})[.](com|cn|net)";
        boolean matches = email_addr.matches(regular);
        if(!matches){
            System.out.println("邮箱地址不正确");
        }
        System.out.println("邮箱地址通过校验: "+ email_addr);
    }

    @Test
    public void test_2(){


        String toAddress = "1312;1231231;123123; ;adasdxz;vxv;";
        String[] split = toAddress.split(";");

        for(String str : split){
            //System.out.println("str.trim(): "+ str.replace(" ", ""));

            String str2 = str.replace(" ", "");
            if(str2 == null || str2.equals("")){
                // System.out.println("1 str: "+ str2);
                continue;
            }
            System.out.println("2 str: "+ str2);
        }


    }


    @Test
    public void test_1(){

        String a1 = "[岗位] 年终考核管理岗";
        String substring = a1.substring(5, a1.length());
        System.out.println(substring);

        String replace = a1.replace("[岗位] ", "").trim();

        System.out.println(replace.trim());


    }
}
