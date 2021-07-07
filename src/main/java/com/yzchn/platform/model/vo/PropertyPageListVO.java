package com.yzchn.platform.model.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName PropertyPageListVO.java
 * @Description TODO 合作物业列表查询返回
 * @createTime 2021年06月29日 09:29:00
 */
@Data
public class PropertyPageListVO {
    /**
     * 物业名称
     */
    private String proName;
    /**
     * 员工工号
     */
    private String employeeNo;
    /**
     * 账号类型
     */
    private Integer type;
    /**
     * 所属人
     */
    private String name;
    /**
     * 物业联系电话
     */
    private String tel;
}
