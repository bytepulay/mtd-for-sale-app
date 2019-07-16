package asia.nainglintun.myintthitar.Activities;

import android.app.Activity;
import android.os.Parcelable;

public abstract class AfterScan implements Parcelable {
    abstract void afterScanJob(Activity activity, String s);
}