package com.ren.song.entity;

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
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReclSong对象", description="")
public class ReclSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "歌曲唯一id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "歌手id")
    private String singerId;

    @ApiModelProperty(value = "歌曲名")
    private String name;

    @ApiModelProperty(value = "歌曲介绍")
    private String introduction;

    @ApiModelProperty(value = "歌曲封面")
    private String pic;

    @ApiModelProperty(value = "歌词")
    private String lyric;

    @ApiModelProperty(value = "资源地址")
    private String url;

    @ApiModelProperty(value = "逻辑删除字段（0代表未删除， 1代表已删除）")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "发行时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
