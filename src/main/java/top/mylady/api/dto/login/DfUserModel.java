package top.mylady.api.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/7 18:02
 * @Description:
 */
@Data
public class DfUserModel implements Serializable {

    private String userId;         //用户id
    private String username;    //用户名
    private String nickname;    //昵称

    private List<Integer> roleIds;       //角色ids
    private Set<String> menuList;        //菜单
    private Set<String> permissionList;  //权限

    public DfUserModel(){};

    public DfUserModel(String id, String username) {
        this.userId = id;
        this.username = username;
    }
}
