package com.ren.singer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author RZY
 * @since 2022-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReclSinger对象", description="")
public class ReclSinger implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌手唯一id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "歌手名")
    private String name;

    @ApiModelProperty(value = "歌手性别")
    private Integer sex;

    @ApiModelProperty(value = "歌手照片")
    private String pic;

    @ApiModelProperty(value = "歌手生日")
    private Date birth;

    @ApiModelProperty(value = "歌手出生地")
    private String location;

    @ApiModelProperty(value = "歌手介绍")
    private String introduction;

    @ApiModelProperty(value = "逻辑删除字段(0代表已删除， 1代表未删除)")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
