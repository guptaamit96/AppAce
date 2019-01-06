package inmortal.amit.appace;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Constant {

    public static SharedPreferences preferences;
    String defaultSelectedURl="defaultSelectedURl";
    String defaultSelectedPosition="defaultSelectedPosition";
    public static Context mContext;
    public static Constant mInstance;

    public static Constant getConstant(Context context) {
        mContext = context;
        if(preferences==null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        if(mInstance==null){
            mInstance = new Constant();
        }
        return mInstance;
    }

    public String getDefaultSelectedURl() {
        return preferences.getString(defaultSelectedURl,"");
    }

    public void setDefaultSelectedURl(String defaultSelectedURl) {
        preferences.edit().putString(this.defaultSelectedURl,defaultSelectedURl).apply();
    }



    public Integer getDefaultSelectedPosition() {
        return preferences.getInt(defaultSelectedPosition,0);
    }

    public void setDefaultSelectedPosition(Integer defaultSelectedPosition) {
        preferences.edit().putInt(this.defaultSelectedPosition,defaultSelectedPosition).apply();
    }
}
