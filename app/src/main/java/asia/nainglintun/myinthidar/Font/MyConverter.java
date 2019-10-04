package asia.nainglintun.myinthidar.Font;

import android.util.Log;

import com.kyawhtut.FontUtil;

import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.Rabbit;

public class MyConverter {

    public static String FixUZaw(String s) {
        boolean isInputUnicode = !new FontUtil().isZawgyi(s);
        boolean isPhoneUnicode = MDetect.INSTANCE.isUnicode();

        Log.d("MyFONT_Input", String.valueOf(isInputUnicode));
        Log.d("MyFONT_Phone", String.valueOf(isPhoneUnicode));

        if (isInputUnicode && !isPhoneUnicode)
            return Rabbit.uni2zg(s);
        else if (!isInputUnicode && isPhoneUnicode)
            return Rabbit.zg2uni(s);

        return s;

    }
}
