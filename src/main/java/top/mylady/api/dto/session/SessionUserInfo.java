package top.mylady.api.dto.session;

import lombok.Data;
import java.util.List;
import java.util.Set;

/**
 * 保存在session中的用户信息
 */
@Data
public class SessionUserInfo {

    private int userId;         //用户id
    private String username;    //用户名
    private String nickname;    //昵称

    private List<Integer> roleIds;       //角色ids
    private Set<String> menuList;        //菜单
    private Set<String> permissionList;  //权限

}
