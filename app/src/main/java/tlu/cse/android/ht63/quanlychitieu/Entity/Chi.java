package tlu.cse.android.ht63.quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablechi") // quy tắc của roomdatabase đặt ra
public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int idchi;
    public int idloaichi;
    public String ten;
    public float sotien;
    public String ghichu;
    public String date;
    public String time;
    public String user;
}
