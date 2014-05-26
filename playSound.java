package An.Proj2;

import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

import android.media.SoundPool;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class playSound extends Activity implements OnTouchListener
{
    DrawView drawView = null;
    SoundPool soundPool = null;
    HashMap<Integer,Integer> soundPoolMap;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("三视透明卡");
        
        // Create View and touchlistener
        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.setOnTouchListener(this);
        //setContentView(R.layout.main);
        
        // create the sound pool;
        soundPool = new SoundPool(4,AudioManager.STREAM_MUSIC,0);
        soundPoolMap = new HashMap<Integer,Integer>();
        
        // Load the sound files
        soundPoolMap.put(0,soundPool.load(this,R.raw.clip1,1));
        soundPoolMap.put(1,soundPool.load(this,R.raw.clip2,1));
        soundPoolMap.put(2,soundPool.load(this,R.raw.clip3,1));
        soundPoolMap.put(3,soundPool.load(this,R.raw.clip4,1));
        soundPoolMap.put(4,soundPool.load(this,R.raw.clip5,1));
        }
    
        public boolean onTouch(View v, MotionEvent event){
            playsound(3);
            return true;
        }
        
        public class DrawView extends View{
            public DrawView(Context context){
                super(context);
            }
        }
        
        public void playsound(int soundId){
           soundPool.play(soundId,1f,1f,1,0,1f);
        }
}

