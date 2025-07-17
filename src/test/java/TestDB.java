import com.spolify.db.PlaylistDB;
import com.spolify.model.Playlist;

import java.util.List;

public class TestDB {
    public static void main(String[] args) {
        PlaylistDB db = new PlaylistDB();
        db.addPlaylist("已点赞的歌曲");
        db.addPlaylist("日语精选");

        List<Playlist> lists = db.getAllPlaylists();
        System.out.println(lists.size());
        for(Playlist p :lists){
            System.out.println(p.getCode()+" | "+p.getName());
        }

    }
}
