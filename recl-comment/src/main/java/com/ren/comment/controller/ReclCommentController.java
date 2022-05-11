package com.ren.comment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ren.comment.entity.ReclComment;
import com.ren.comment.service.ReclCommentService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-11
 */

@Api(tags = "评论接口")
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class ReclCommentController {

    @Autowired
    ReclCommentService reclCommentService;

    @ApiOperation(value = "删除评论")
    @DeleteMapping("/{id}")
    public Result delComment(@PathVariable String id) {
        boolean remove = reclCommentService.removeById(id);
        if(remove) return Result.ok()
                .message("评论删除成功");
        else return Result.error()
                .message("评论删除失败");
    }

    @ApiOperation(value = "获得指定歌曲 ID 的评论列表")
    @GetMapping("/song/{songId}")
    public Result getCommentBySongId(@PathVariable String songId) {
        QueryWrapper<ReclComment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id", songId);
        List<ReclComment> comments = reclCommentService.list(wrapper);
        return Result.ok()
                .data("comments", comments);
    }

    @ApiOperation(value = "获得指定歌单 ID 的评论列表")
    @GetMapping("/song-list/{songListId}")
    public Result getCommentBySongListId(@PathVariable String songListId) {
        QueryWrapper<ReclComment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id", songListId);
        List<ReclComment> comments = reclCommentService.list(wrapper);
        return Result.ok()
                .data("comments", comments);
    }

    @ApiOperation(value = "评论点赞")
    @PostMapping("/like/{id}")
    public Result updateCommentLike(@PathVariable String id, @RequestParam int up) {
        UpdateWrapper<ReclComment> wrapper = new UpdateWrapper<>();
        wrapper.set("up", up);
        boolean update = reclCommentService.update(new ReclComment().setId(id), wrapper);
        if(update) return Result.ok()
                .message("点赞成功");
        else return Result.error()
                .message("点赞失败");
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("")
    public Result addComment(HttpServletRequest req) {
        String user_id = req.getParameter("userId");
        Integer type = Integer.valueOf(req.getParameter("type"));
        String song_list_id = req.getParameter("songListId");
        String song_id = req.getParameter("songId");
        String content = req.getParameter("content").trim();
        ReclComment comment = new ReclComment();
        comment.setContent(content)
                .setType(type)
                .setUserId(user_id);
        if(type == 1) comment.setSongListId(song_list_id);
        if(type == 0) comment.setSongId(song_id);
        boolean save = reclCommentService.save(comment);
        if(save)  return Result.ok()
                .message("感谢你的评论");
        else return Result.error()
                .message("服务出现错误，请稍后再试!");
    }
}

