package com.ren.collect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="ReclCollect对象", description="")
public class ReclCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏记录id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "收藏用户id")
    private String userId;

    @ApiModelProperty(value = "收藏歌曲id")
    private String songId;

    @ApiModelProperty(value = "收藏歌单id")
    private String songListId;

    @ApiModelProperty(value = "收藏类型(0代表歌曲收藏, 1代表歌单)")
    private Integer type;

    @ApiModelProperty(value = "逻辑删除字段（0代表未删除， 1代表已删除）")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
