package com.yzchn.platform.model.vo;

import com.yzchn.platform.model.entity.TMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName TMenuNodeVO.java
 * @Description TODO 分配角色树级菜单
 * @createTime 2021年06月23日 14:59:00
 */
@Data
public class TMenuNodeVO extends TMenu implements Serializable {

    private static final long serialVersionUID = -4744788116503860825L;

    private List<TMenuNodeVO> children;
}
