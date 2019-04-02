package cn.miao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id ;
    private String userCode;  //用户编码
    private String userName;  //用户名称

    private String userPassword;   //用户密码
    private int gender;  //性别
    private Date birthday;   //出生日期
    private String phone;   //电话
    private String address;   //地址
    private Integer userRole;    //用户角色
    private Integer createBy  ;   //创建者
    private Date creationDate;     //创建时间
    private Integer modifyBy;   //更新者
    private Date modifyDate;   //更新者

//   role表
    private String userRoleName;  //用户角色名称

    private Role role;

    private int age;    //年龄

    private List<Address> addressList;   //用户地址列表

    public int getAge() {

        Date date=new Date();
        int age=date.getYear()-birthday.getYear();
        return age;
    }
}
