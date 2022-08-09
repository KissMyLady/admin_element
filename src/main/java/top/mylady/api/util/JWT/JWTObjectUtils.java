package top.mylady.api.util.JWT;
import org.apache.commons.lang.StringUtils;


/**
 * @Author: KissMyLady
 * @Description:
 * @Date: Created in 2021/10/30 15:10
 */
public class JWTObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    public static Integer toInt(Object obj) {

        return toLong(obj).intValue();
    }
}
