package An.Proj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Hello,World");
        setContentView(tv);
    }
}