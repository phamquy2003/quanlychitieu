gitpackage tlu.cse.android.ht63.quanlychitieu.RoomDTB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.BTL_Quanlychitieu.Dao.LoaiThuDao;
import com.google.BTL_Quanlychitieu.Dao.ThuDao;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;

@Database(entities = {Loaithu.class, Thu.class}, version = 1)
public abstract class App_DTB_Thu extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();

    public static App_DTB_Thu INSTANCE; // singleton

    public  static App_DTB_Thu getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (App_DTB_Thu.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                App_DTB_Thu.class, "dlieuthu_db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return  INSTANCE;
    }
}
