package com.yzchn.platform.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName CommunityPageListVO.java
 * @Description TODO
 * @createTime 2021年06月28日 14:14:00
 */
@Data
public class CommunityPageListVO implements Serializable {

    private static final long serialVersionUID = -7662498169370352379L;
    /**
     * 小区名称
     */
    private String name;
    /**
     * 小区类型
     */
    private Integer communityType;
    /**
     * 物业名称
     */
    private String propertyName;
    /**
     * 小区地址
     */
    private String address;
    /**
     * 签约状态
     */
    private Integer contractStatus;
    /**
     * 销售员
     */
    private String salesman;
}
