package com.ren.dubboservice.impl;

import com.ren.dubboservice.ListSongService;
import com.ren.utils.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListSongService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 18:11
 * @Version: v1.0
 */


public class ListSongServiceImpl implements ListSongService {
    @Override
    public List<String> getSongIds(String songListId) {
        return new ArrayList<>();
    }
}
