package cn.miao.dao;

import cn.miao.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    /*
     * 查询用户记录数*/
    public int count();

    public List<User> getUserList(User userName);

    //    public List<User> getUserList1(User userName);  //使用自定义结果集
//    动态SQL if  进行分页操作
    public List<User> getUserList1(@Param("userName") String userName,
                                   @Param("userRole") Integer roleId);  //使用自定义结果集

    /*动态SQL Where*/
    public List<User> getUserList2(@Param("userName") String userName,
                                   @Param("userRole") Integer roleId);  //使用自定义结果集

    //    使用分页
    public List<User> getUserList3(@Param("userName") String userName,
                                   @Param("userRole") Integer roleId,
                                   @Param("from") Integer currentPageNo,
                                   @Param("pageSize") Integer pageSize);  //使用自定义结果集


    public List<User> getUserListByMap(Map<String, String> userMap);

    public List<User> getUserListByUserName(String userName);


    public int add(User user);

    public int modify(User user);

    public int updatePwd(@Param("id") Integer id, @Param("userPassword") String pwd);

    public int deleteUserById(@Param("id") Integer delId);

    //    根据角色Id查询出 用户列表信息
    public List<User> getUserListByRoleId(@Param("userRole") Integer roleId);

    //    根据用户Id 获取用户信息及 地址列表
    public List<User> getAddressListByUserId(@Param("id") Integer userId);

    /*
    根据用户角色列表 获取该角色列表下用户列表信息 foreach --array
    */
    public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);

    /*
    根据用户角色列表 获取该角色列表下用户列表信息 foreach --array
    */
    public List<User> getUserByRoleId_foreach_list(List<Integer> roleList);

    /*当传入多个参数时*/
    public List<User> getUserByconditionMap_foreach_map(Map<String, Object> conditionMap);

    /*将传入的单个参数的List，封装成Map*/
    public List<User> getUserByRoleId_foreach_map(Map<String, Object> roleMap);

    /* */
    public List<User> getUserList_choose(@Param("userName")String userName,
                                         @Param("userCode")String userCode,
                                         @Param("userRole")Integer userRole,
                                         @Param("creationDate")Date creationDate);

}
