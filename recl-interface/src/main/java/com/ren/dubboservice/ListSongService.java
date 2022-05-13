package com.ren.dubboservice;

import java.util.List;

/**
 * @InterfaceName: SongService
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 17:12
 * @Version: v1.0
 */

public interface ListSongService {
    //获取歌单中的所有歌曲id
    List<String> getSongIds(String songListId);
}
