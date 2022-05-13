package com.ren.songlist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ren.dubboservice.ListSongService;
import com.ren.songlist.entity.ReclListSong;
import com.ren.songlist.mapper.ReclListSongMapper;
import com.ren.songlist.service.ReclListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RZY
 * @since 2022-05-12
 */

@Service
public class ReclListSongServiceImpl extends ServiceImpl<ReclListSongMapper, ReclListSong> implements ReclListSongService {

}
