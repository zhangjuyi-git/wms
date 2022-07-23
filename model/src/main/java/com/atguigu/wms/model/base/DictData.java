package com.atguigu.wms.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:juyi
 * @Date:2022/7/22 8:58
 */
@Data
public class DictData {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "上级id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "编码")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_id", fill=FieldFill.UPDATE)
    private Long updateId;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_name", fill=FieldFill.UPDATE)
    private String updateName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();

}
