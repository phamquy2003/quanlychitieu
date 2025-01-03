package tlu.cse.android.ht63.quanlychitieu.RoomDTB;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.BTL_Quanlychitieu.Dao.ChiDao;
import com.google.BTL_Quanlychitieu.Dao.LoaiChiDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;

@Database(entities = {Loaichi.class, Chi.class}, version = 1)

public abstract class AppDTB_Chi extends RoomDatabase {

    public  abstract LoaiChiDao loaiChiDao();

    public  abstract ChiDao chiDao();

    public static AppDTB_Chi INSTANCE;

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public  static AppDTB_Chi getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDTB_Chi.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDTB_Chi.class, "dlieuchi_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)  //đổ dl khi bđ chạy
//                        .addMigrations(mygration3_4)
                        .build();
            }
        }
        return  INSTANCE;
    }

    public static class  PopulateData extends AsyncTask<Void, Void,Void> {
        private LoaiChiDao loaiChiDao;
        public PopulateData(AppDTB_Chi db) {
            loaiChiDao = db.loaiChiDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String[] cacloaichi = new String[]{"An uong", "Quan ao", "Vui choi"};
            for (String it : cacloaichi){
                Loaichi lt = new Loaichi();
                lt.Tenloaichi = it;
                loaiChiDao.insert(lt);
            }
            return null;
        }
    }
}