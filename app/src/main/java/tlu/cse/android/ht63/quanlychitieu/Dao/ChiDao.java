package tlu.cse.android.ht63.quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại chi
public interface ChiDao {
    @Query("Select * from tablechi where tablechi.user like :struser order by tablechi.date ASC")
    LiveData<List<Chi>> findAll(String struser);

    @Query("Select * from tablechi where tablechi.date like :str AND tablechi.user like :struser order by tablechi.date ASC")
    LiveData<List<Chi>> findAll(String str, String struser);

    @Query("Select sum(sotien) from tablechi where tablechi.user like :struser")
    LiveData<Float> sumTongChi(String struser);

    @Query("Select sum(sotien) from tablechi where tablechi.date like :strlike and tablechi.user like :struser")
    LiveData<Float> sumTongChi(String strlike, String struser);

    @Query("Select b.idloaichi,b.Tenloaichi,sum(a.sotien) as tong from tablechi a INNER JOIN tableloaichi b on a.idloaichi = b.idloaichi where date like :strlike and a.user like :struser"+
            " GROUP BY  b.Tenloaichi")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(String strlike, String struser);

    @Query("Select a.date,sum(a.sotien) as tong from tablechi a Where a.date like :strlike and a.user like :struser GROUP BY  a.date")
    LiveData<List<ThongKeTheoNgay>> sumByNgay(String strlike, String struser);

    @Insert
    void insert(Chi chi);

    @Update
    void update (Chi chi);

    @Delete
    void delete(Chi chi);
}