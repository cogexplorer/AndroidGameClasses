package An.proj5;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawTriangle extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Draw三角形");
        setContentView(new DrawView(this));
    }
    
    public class DrawView extends View {
        public DrawView(Context context) {
            super(context);
        }
        
        @Override public void onDraw(Canvas canvas){
           super.onDraw(canvas);
           
           // Gray background
           Paint paint = new Paint();
           paint.setAntiAlias(true);
           canvas.drawColor(Color.GRAY);
           
           // Draw a Black triangle
           paint.setColor(Color.BLACK);
           canvas.drawLine(10,10,10,50,paint);
           canvas.drawLine(10, 50, 50, 50, paint);
           canvas.drawLine(50,50, 10,10,paint);
        } // OnDraw
    } // DrawView
}
