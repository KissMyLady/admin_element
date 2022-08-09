package top.mylady.api.util.myUtils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/7 9:57
 * @Description:
 */
public class GetTimeUtils {

    public static void main(String[] args) {
        //Date futureTime = getFutureTime(30);
        //System.out.println(futureTime);

        String toDay = getToDay();
        //2022-06-23
        System.out.println(toDay);
    }

    /**
     * 获取当前天数
     * 返回: 2022-06-23
     */
    public static String getToDay(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }


    /**
     * 返回一个未来的时间
     */
    public static Date getFutureTime(int offsetData) {
        Date date = new Date();
        DateTime newDate2 = DateUtil.offsetDay(date, offsetData);
        return newDate2;
    }

    public static void test_2(){

        Date date2 = new Date();

        long date2Time = date2.getTime();
        System.out.println("date2Time: "+ date2Time);

    }

    /**
     * 判断时间是否过期
     */
    public static void test_1() throws Exception {
        String ss = "2021-11-02 12:12:00";
        String se = "2021-11-02 21:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(ss));
        calendar.add(Calendar.HOUR_OF_DAY, 3);  // +3小时

        Date c = new Date();  // 当前时间

        if (c.getTime() > calendar.getTimeInMillis()) {  // 超时
            System.out.println("hhhhhhhhhhhhhh");
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(sdf.parse(se));
        calendar1.add(Calendar.HOUR_OF_DAY, 3);  // +3小时
        if (c.getTime() > calendar1.getTimeInMillis()) {  // 超时
            System.out.println("dddddddddddddddddd");
        }

    }
}
