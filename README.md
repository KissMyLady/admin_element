# Admin-Element 后台权限管理系统Demo
提供一套基于SpringBoot-shiro-vue的权限管理思路.

前后端都加以控制,做到按钮/接口级别的权限

源项目参考: [GitHub: Heeexy](https://github.com/Heeexy/SpringBoot-Shiro-Vue)

## DEMO
[源项目参考 测试地址](http://g.heeexy.com)
admin/123456 管理员身份登录,可以新增用户,角色.

角色可以分配权限

控制菜单是否显示,新增/删除按钮是否显示


# 预览图

图片1      
<img src="https://blog.mylady.top/static/books/2022/8/2022-08-09_213343.png" style="width:48%;" />     
图片2       
<img src="https://blog.mylady.top/static/books/2022/8/2022-08-09_213441.png" style="width:48%;" />     
图片3          
<img src="https://blog.mylady.top/static/books/2022/8/2022-08-09_213454.png" style="width:48%;" />

# 优化
1, 站长[巨人-Heeexy](https://github.com/Heeexy/SpringBoot-Shiro-Vue)的肩膀上进行优化开发.
1. 前端Vue框架使用 [vue-element-admin](https://panjiachen.github.io/vue-element-admin) 进行二次开发. [使用文档: https://panjiachen.github.io/vue-element-admin-site/zh/](https://panjiachen.github.io/vue-element-admin-site/zh/)    
2. 使用token作为登录凭证, 不使用session,避免跨域问题
3. 使用自定义注解+aop 替代shiro的功能,简化了配置，增强了可拓展性

# 设计思路

### 核心
 每个登录用户拥有各自的N条权限,比如 文章:查看/编辑/发布/删除

### 后端

基于 [RBAC新解](http://globeeip.iteye.com/blog/1236167) . 

通常我们的权限设计都是 用户--角色--权限 ,其中**角色**是我们写代码的人没法控制的,它可以有多条权限,每个用户又可以设计为拥有多个角色.因此如果从角色着手进行权限验证,系统都必须根据用户的配置动起来,非常复杂.

所以我们后台设计的关键点就在于: **后台接口只验证权限,不看角色.**

角色的作用其实只是用来管理分配权限的,真正的验证只验证**权限** ,而不去管你是否是那种角色.体现在代码上就是接口上注解为

```java
@RequiresPermissions("article:add")
```

而不是

```java
@RequiresRoles(value = {"admin","manager","writer"}, logical = Logical.OR) 
```

### 前端

采用了[vueAdmin-template](https://github.com/PanJiaChen/vueAdmin-template) , [ElementUI](https://github.com/ElemeFE/element) , 权限设计思路也是参考了 vueAdmin 的动态路由的设计.

后端负责了接口的安全性,而前端之所以要做权限处理,最主要的目的就是**隐藏掉不具有权限的菜单(路由)和按钮**.

登录系统后,后端返回此用户的权限信息,比如 
```json
{
    "msg": "",
    "code": "200",
    "info": {
        "userId": 19823,
        "username": "123456",
        "nickname": "管理员金毛狮王谢逊",
        "roleIds": [
            995009,
            993003
        ],
        "menuList": [
            "sys_permission",
            "role",
            "user",
            "logs",
            "article",
            "option"
        ],
        "permissionList": [
            "user:list",
            "user:add",
            "role:update",
            "sys_permission:delete",
            "option:change",
            "article:add",
            "logs:delete",
            "sys_permission:add",
            "user:update",
            "role:add",
            "logs:list",
            "sys_permission:list",
            "option:add",
            "article:list",
            "option:delete",
            "option:list",
            "role:list",
            "article:update",
            "role:delete",
            "sys_permission:change"
        ]
    }
}
```

根据**menuList**判断给此用户生成哪些路由, 根据**permissionList**判断给用户显示哪些按钮,能请求哪些接口.

### 数据库
最主要的是要有一张本系统内的全部权限明细表,比如下面这样
![权限表](https://blog.mylady.top/static/books/2022/8/2022-08-09_214358.png)
![权限数据](https://blog.mylady.top/static/books/2022/8/2022-08-09_214420.png)

如果某用户拥有表格中前五条权限,就可以查出他就拥有article和user两个菜单,至于页面内是否显示(新增)(修改)按钮,就根据他的permissionList来判断.

## 具体实现
有了思路,就可以根据各自的业务进行实现,本项目在此进行了简单的实现,后端代码在back文件夹,前端代码在vue文件夹.前端启动只需
```
npm install
npm run dev
```

后端就是常规的shiro配置,前端代码如果看不明白,可以加群讨论: 484257540     

## 分配权限页面效果
![分配权限页面](https://blog.mylady.top/static/books/2022/8/2022-08-09_213441.png)


# 鸣谢
[GitHub: Heeexy](https://github.com/Heeexy/SpringBoot-Shiro-Vue)