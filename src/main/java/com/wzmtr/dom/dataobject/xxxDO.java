package com.wzmtr.dom.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 10:45
 */
@TableName("xxx")
@Data
public class xxxDO {
    @TableId("ID")
    private String id;
}
