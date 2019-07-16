package asia.nainglintun.myintthitar.activities.Font;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

public class UniZawgyiTextView extends AppCompatTextView {

    public UniZawgyiTextView(Context context) {
        super(context);
    }

    public UniZawgyiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UniZawgyiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String text) {
        super.setText(MyConverter.FixUZaw(text));
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Log.d("MYFONT","Reach here");
        super.setText(MyConverter.FixUZaw(text.toString()), type);
    }
}
