package asia.nainglintun.myintthitar.activities.Activities;

import android.app.Activity;
import android.os.Parcelable;

import java.io.Serializable;

public abstract class AfterScan implements Parcelable {
    abstract void afterScanJob(Activity activity, String s);
}