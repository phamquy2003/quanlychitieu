package tlu.cse.android.ht63.quanlychitieu.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableloaichi")
public class Loaichi {
    @PrimaryKey(autoGenerate = true)
    public int idloaichi;
    public String Tenloaichi;
    public int isDelete;
    public String user;
    public Loaichi() {
        this.isDelete = 0;
    }
}
