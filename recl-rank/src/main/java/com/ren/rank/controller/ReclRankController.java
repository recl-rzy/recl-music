package com.ren.rank.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.rank.entity.ReclRank;
import com.ren.rank.service.ReclRankService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-12
 */

@Api(tags = "评分接口")
@RestController
@RequestMapping("/rank")
@CrossOrigin
public class ReclRankController {

    @Autowired
    ReclRankService reclRankService;

    @ApiOperation(value = "提交评分")
    @PostMapping("")
    public Result addRank(HttpServletRequest req) {
        //获取请求参数
        String songListId = req.getParameter("songListId").trim();
        String userId = req.getParameter("userId").trim();
        String score = req.getParameter("score").trim();
        //参数封装
        ReclRank rank = new ReclRank();
        rank.setUserId(userId)
                .setScore(Integer.valueOf(score))
                .setSongListId(songListId);
        boolean res = reclRankService.save(rank);
        if(res) return Result.ok()
                .message("感谢你的评分");
        else return Result.error()
                .message("操作失败，请稍后再试!");
    }

    @ApiOperation(value = "获取歌单评分")
    @GetMapping("/song-list/{songListId}")
    public Result getRankBySongListId(@PathVariable String songListId) {
        ReclRank rank = reclRankService.getOne(new QueryWrapper<ReclRank>()
                .eq("song_list_id", songListId)
                .select("score"));
        int score = (rank == null ? 0 : rank.getScore());
        return Result.ok()
                .data("score", score);
    }

    @ApiOperation(value = "获取用户评分")
    @GetMapping("/user/{userId}/{songListId}")
    public Result getRankByUserId(@PathVariable String userId, @PathVariable String songListId) {
        ReclRank rank = reclRankService.getOne(new QueryWrapper<ReclRank>()
                .eq("user_id", userId)
                .eq("song_list_id", songListId)
                .select("score"));
        int score = (rank == null ? 0 : rank.getScore());
        return Result.ok()
                .data("score", score);
    }
}

