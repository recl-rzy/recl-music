package com.ren.songlist.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ren.songlist.constant.RedisKeyPrefix;
import com.ren.songlist.entity.ReclListSong;
import com.ren.songlist.service.ReclListSongService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-12
 */

@Api(tags = "歌单及歌曲关联表")
@RestController
@RequestMapping("/list-song")
@CrossOrigin
public class ReclListSongController {

    @Autowired
    ReclListSongService reclListSongService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "歌单歌曲添加")
    @PostMapping("")
    public Result addListSong(HttpServletRequest req) {
        //获取请求参数
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();
        //参数封装
        ReclListSong listSong = new ReclListSong();
        listSong.setSongId(song_id)
                .setSongListId(song_list_id);
        boolean res = reclListSongService.save(listSong);
        if(res) return Result.ok()
                .message("歌曲添加成功");
        else return Result.error()
                .message("歌曲添加失败，请稍后再试");
    }

    @ApiOperation(value = "删除歌单中的某一歌曲")
    @DeleteMapping("/{songId}")
    public Result delListSongBySongId(@PathVariable String songId) {
        boolean res = reclListSongService.remove(new UpdateWrapper<ReclListSong>()
                .set("song_id", songId));
        if(res) return Result.ok()
                .message("歌曲删除成功");
        else return Result.error()
                .message("歌曲删除失败，请稍后再试");
    }

    @ApiOperation(value = "获取歌单中的歌曲")
    @GetMapping("/{songListId}")
    public Result getSongListById(@PathVariable String songListId) {
        //生成redis的key
        String key = RedisKeyPrefix.LISTSONG_OF_SONGLISTID_CACHE_KEY + songListId;
        List<ReclListSong> listSongs = null;
        //查redis
        listSongs = (List<ReclListSong>) redisTemplate.opsForValue().get(key);
        if(listSongs != null) return Result.ok()
                .data("listSongs", listSongs);

        listSongs = reclListSongService.list(new QueryWrapper<ReclListSong>()
                .eq("song_list_id", songListId));
        redisTemplate.opsForValue().set(key, listSongs, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("listSongs", listSongs);
    }

    @ApiOperation(value = "更新歌单信息")
    @PutMapping("")
    public Result updateListSong(HttpServletRequest req) {
        //获取请求参数
        String id = req.getParameter("id").trim();
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();
        //参数封装
        ReclListSong listSong = new ReclListSong();
        listSong.setId(id)
                .setSongId(song_id)
                .setSongListId(song_list_id);
        boolean res = reclListSongService.updateById(listSong);
        if(res) return Result.ok()
                .message("歌曲添加成功");
        else return Result.error()
                .message("歌曲添加失败，请稍后再试");
    }
}

