package com.ren.comment.entity;

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
@ApiModel(value="ReclComment对象", description="")
public class ReclComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "评论用户id")
    private String userId;

    @ApiModelProperty(value = "评论歌曲id")
    private String songId;

    @ApiModelProperty(value = "评论歌单id")
    private String songListId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "点赞量")
    private Integer up;

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
