package cn.miao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Integer id ;    //主键 ID
    private String postCode;  //邮编
    private String contact ;   //联系人
    private String addressDesc;  //地址
    private String tel;         // 联系电话
    private String createdBy;   //  创建者
    private Date  creationDate;   //创建时间
    private Integer modifyBy;    //更新者
    private Date modifyDate;     //更新时间
    private Integer  userId;     //用户ID

}
