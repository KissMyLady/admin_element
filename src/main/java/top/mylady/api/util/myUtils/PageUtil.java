package top.mylady.api.util.myUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/2/17 23:43
 * @Description: 分页算法, 参考: https://www.cnblogs.com/loong-hon/p/10863635.html
 */
public class PageUtil {

    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List<?> startPage(List<?> list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return new ArrayList<>();
        }
        if (list.size() == 0) {
            return new ArrayList<>();
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List<?> pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

}