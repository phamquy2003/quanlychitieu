package tlu.cse.android.ht63.quanlychitieu.Other;

import android.content.Context;

public class DataLocalManager {
    private static final String MUC_CHI_TIEU = "MUC_CHI_TIEU";
    private static final String USER = "USER";
    private static DataLocalManager instance;
    private MysharedPreferences mysharedPreferences;

    public static void init(Context context)
    {
        instance = new DataLocalManager();
        instance.mysharedPreferences = new MysharedPreferences(context);
    }

    public static void update_mucchitieu(int value)
    {
        instance.mysharedPreferences.pushintvalue(MUC_CHI_TIEU, value);
    }

    public static int get_mucchitieu()
    {
        return instance.mysharedPreferences.getIntvalue(MUC_CHI_TIEU);
    }

    public static void update_user(String value)
    {
        instance.mysharedPreferences.pushStringvalue(USER, value);
    }
    public static void remove_user()
    {
        instance.mysharedPreferences.remove(USER);
    }

    public static String get_user()
    {
        return instance.mysharedPreferences.getStringvalue(USER);
    }
}
