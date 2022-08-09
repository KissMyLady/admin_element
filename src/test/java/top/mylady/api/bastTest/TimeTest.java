package top.mylady.api.bastTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/23 16:21
 * @Description:
 */
public class TimeTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeTest.class);

    //获取最近5年数据
    @Test
    public void test_2(){

        //sql获取: select YEAR(NOW()) from dual;

    }


    /**
     * 获取过去12个月的字符串时间
     */
    @Test
    public void test_1(){
        //获取最近6个月的时间字符串
        List<String> dateList = new ArrayList<>();
        Date date = new Date();

        Calendar calendar = new GregorianCalendar();
        for (int i = 0; i < 12; i++) {
            calendar.setTime(date);
            calendar.add(calendar.MONTH, -i);
            String dateTime = (calendar.get(Calendar.YEAR)) + "-" + (calendar.get(Calendar.MONTH) + 1);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
            try {
                String sfDate = sf.format(sf.parse(dateTime));//格式化后的时间
                logger.info("sfDate: {}", sfDate);

                //logger.info("格式化后的时间sfDate打印: {}", sfDate);
                dateList.add(sfDate + "-01");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
