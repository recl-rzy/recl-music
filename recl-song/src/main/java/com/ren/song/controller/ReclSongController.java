package com.ren.song.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.dubboservice.ListSongService;
import com.ren.song.constant.RedisKeyPrefix;
import com.ren.song.entity.ReclSong;
import com.ren.song.service.ReclSongService;
import com.ren.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RZY
 * @since 2022-05-10
 */

@Api(tags = "recl歌曲接口")
@RestController
@RequestMapping("/song")
@CrossOrigin
public class ReclSongController {

    @Autowired
    ReclSongService reclSongService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Reference(parameters = {"unicast", "false"}, mock = "com.ren.dubboservice.impl.ListSongServiceImpl")
    ListSongService listSongService;

    @ApiOperation(value = "歌曲删除")
    @DeleteMapping("/{id}")
    public Result delSong(@PathVariable String id) {
        boolean res = reclSongService.removeById(id);
        if(res) {
            //删除缓存中的记录
            redisTemplate.delete(RedisKeyPrefix.SONG_CACHE + id);
            return Result.ok()
                    .message("删除成功");
        }
        else return Result.error()
                .message("删除失败");
    }

    @ApiOperation(value = "获取所有歌曲")
    @GetMapping("/songs")
    public Result getAllSongs() {
        //生成redis的key
        String key = RedisKeyPrefix.ALL_SONGS_CACHE;
        List<ReclSong> songs = null;
        //先查redis缓存
        songs = (List<ReclSong>) redisTemplate.opsForValue().get(key);
        if(songs != null) return Result.ok()
                .data("songs", songs);

        //redis中不存在则查询数据库，再放入redis
        songs = reclSongService.list(null);
        redisTemplate.opsForValue().set(key, songs, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songs", songs);
    }

    @ApiOperation(value = "获取指定歌曲")
    @GetMapping("/{id}")
    public Result getSongById(@PathVariable String id) {
        //生成redis的key
        String key = RedisKeyPrefix.SONG_CACHE + id;
        ReclSong song = null;
        //查询redis缓存
        song = (ReclSong) redisTemplate.opsForValue().get(key);
        if(song != null) return Result.ok()
                .data("song", song);
        //redis中不存在则查询数据库，再放入redis
        song = reclSongService.getById(id);
        redisTemplate.opsForValue().set(key, song, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("song", song);
    }

    @ApiOperation(value = "通过歌手id获取歌曲")
    @GetMapping("/singer/{singerId}")
    public Result getSongBySingerId(@PathVariable String singerId) {
        //生成redis的key
        String key = RedisKeyPrefix.SONGS_OF_SINGERID_CACHE + singerId;
        List<ReclSong> songs = null;
        //查询redis缓存
        songs = (List<ReclSong>) redisTemplate.opsForValue().get(key);
        if(songs != null) return Result.ok()
                .data("songs", songs);

        //查询mysql，放入redis缓存
        QueryWrapper<ReclSong> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id", singerId);
        songs = reclSongService.list(wrapper);
        redisTemplate.opsForValue().set(key, songs,60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songs", songs);
    }

    @ApiOperation(value = "通过歌手名获取歌曲")
    @GetMapping("/singer-name")
    public Result getSongBySingerName(@RequestParam String name) {
        //生成redis的key
        String key = RedisKeyPrefix.SONGS_OF_SINGERNAME_CACHE + name;
        List<ReclSong> songs = null;
        //redis中查询key
        songs = (List<ReclSong>) redisTemplate.opsForValue().get(key);
        if(songs != null) return Result.ok()
                .data("songs", songs);

        //不存在则查询mysql，再放入redis
        QueryWrapper<ReclSong> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        songs = reclSongService.list(wrapper);
        redisTemplate.opsForValue().set(key, songs, 60, TimeUnit.MINUTES);
        return Result.ok()
                .data("songs", songs);
    }

    @ApiOperation(value = "获取歌单中的所有歌曲")
    @GetMapping("/list-song/{songListId}")
    public Result getSongsFromListSongId(@PathVariable String songListId) {
        //生成redis的key
        String key = RedisKeyPrefix.SONGS_OF_SONGLISTID_CACHE + songListId;
        List<ReclSong> songs = null;
        //查询redis
        songs = (List<ReclSong>) redisTemplate.opsForValue().get(key);
        if(songs != null) return Result.ok()
                .data("songs", songs);
        //查mysql，放入redis
        List<String> songIds = listSongService.getSongIds(songListId);
        if(songIds.isEmpty()) return Result.ok()
                .data("songs", new ArrayList<ReclSong>());
        songs = (List<ReclSong>) reclSongService.listByIds(songIds);
        redisTemplate.opsForValue().set(key, songs, 60 ,TimeUnit.MINUTES);
        return Result.ok()
                .data("songs", songs);
    }

}

