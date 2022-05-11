package com.ren.songlist.entity;

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
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReclSongList对象", description="")
public class ReclSongList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌单唯一id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "歌单标题")
    private String title;

    @ApiModelProperty(value = "歌单封面")
    private String pic;

    @ApiModelProperty(value = "歌单相关介绍")
    private String introduction;

    @ApiModelProperty(value = "歌单类型")
    private String style;

    @ApiModelProperty(value = "逻辑删除字段（0代表未删除， 1代表已删除）")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
