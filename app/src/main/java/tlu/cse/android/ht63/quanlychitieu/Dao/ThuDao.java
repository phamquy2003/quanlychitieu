package tlu.cse.android.ht63.quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;
import com.google.BTL_Quanlychitieu.Entity.Thu;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface ThuDao {
    @Query("Select * from tablethu where tablethu.user like :struser order by tablethu.date ASC")
    LiveData<List<Thu>> findAll(String struser);

    @Query("Select * from tablethu where tablethu.date LIKE :strlike and tablethu.user like :struser order by tablethu.date ASC")
    LiveData<List<Thu>> findAll(String strlike, String struser);

    @Query("Select sum(sotien) from tablethu where tablethu.user like :struser")
    LiveData<Float> sumTongThu(String struser);

    @Query("Select sum(sotien) from tablethu where tablethu.date LIKE :strlike and tablethu.user like :struser")
    LiveData<Float> sumTongThu(String strlike, String struser);

    @Query("Select b.idloaithu,b.Tenloaithu,sum(a.sotien) as tong from tablethu a INNER JOIN tableloaithu b on a.idloaithu = b.idloaithu"+
            " where date like :strlike and a.user like :struser GROUP BY  b.idloaithu")
    LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(String strlike, String struser);

    @Query("Select a.date,sum(a.sotien) as tong from tablethu a Where a.date like :strlike and a.user like :struser GROUP BY  a.date")
    LiveData<List<ThongKeTheoNgay>> sumByNgay(String strlike, String struser);

    @Insert
    void insert(Thu thu);

    @Update
    void update (Thu thu);

    @Delete
    void delete(Thu thu);

}