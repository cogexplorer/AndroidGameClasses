package An.Proj3;

import java.io.*;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.res.AssetManager;

import android.graphics.*;
import android.view.*;
import android.view.View.OnTouchListener;


public class TouchTest extends Activity implements OnTouchListener
{
    DrawView drawView;
    
    Point touch = new Point(0,0);
    String inputAction ="";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.setOnTouchListener(this);
    }
    
    public void onResume() {
        super.onResume();
        drawView.resume();
    }

    public void pause() {
        super.onPause();
        drawView.pause();
    }
    public boolean onTouch(View v,MotionEvent event)
    {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                inputAction ="DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                inputAction = "MOVE";
                break;
            case MotionEvent.ACTION_UP:
                inputAction = "UP";
                break;
        }
        
        touch.x = (int)event.getX();
        touch.y = (int)event.getY();
            
        return true;
    }
            
    public class DrawView extends SurfaceView implements Runnable
    {
        Thread gameloop = null;
        SurfaceHolder surface = null;
        
        volatile boolean running = false;
        AssetManager assets = null;
        
        public DrawView(Context context) {
            super(context);
            
            surface = getHolder();
            assets = context.getAssets();
        }
        
        public void resume(){
            running = true;
            gameloop = new Thread(this);
            gameloop.start();
        }
        
        public void pause(){
            running = false;
            while (true) {
                try {
                    gameloop.join();
                }
                catch (InterruptedException e) {}
            }
        }
          
    

    @Override public void run() {
        while (running) {
            if (!surface.getSurface().isValid()) continue;
            
            // Open the canvas for drawing
            Canvas canvas = surface.lockCanvas();
            canvas.drawColor(Color.BLACK);
            
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(24);
            
            canvas.drawText("测试触摸",10,20,paint);
            canvas.drawText("动作:"+inputAction, 10, 50, paint);
            canvas.drawText("位置:"+touch.x+","+touch.y, 10, 80, paint);
            
            if (touch.x!=0 && touch.y!=0)
                canvas.drawCircle(touch.x,touch.y,50,paint);
            
            // close the canvas
            surface.unlockCanvasAndPost(canvas);
            
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    }
}
