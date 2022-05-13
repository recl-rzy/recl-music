package com.ren.songlist.service.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.dubboservice.ListSongService;
import com.ren.songlist.entity.ReclListSong;
import com.ren.songlist.mapper.ReclListSongMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ListSongService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 17:42
 * @Version: v1.0
 */

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    ReclListSongMapper reclListSongMapper;

    @Override
    public List<String> getSongIds(String songListId) {
        List<ReclListSong> songs = reclListSongMapper.selectList(new QueryWrapper<ReclListSong>()
                .eq("song_list_id", songListId)
                .select("song_id"));
        return songs.stream().map(ReclListSong::getSongId).collect(Collectors.toList());
    }
}
