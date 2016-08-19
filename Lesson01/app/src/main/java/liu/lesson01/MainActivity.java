package liu.lesson01;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;
    private MyRender myRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView=new GLSurfaceView(this);

       // glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//两种渲染模式 每隔一段时间调用onDrawFream()
        //glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//脏模式，只有当用户需要重绘时才调用onDrawFream()
        //默认情况下为主动绘制，浪费cpu，因此建议使用第二种模式
        //使用脏模式时在需要重绘时需要先调用glSurfaceView的requestRender()方法
        myRender=new MyRender();
        glSurfaceView.setRenderer(myRender);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(glSurfaceView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPosition=0;
        float yPosition=0;
     int action=event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_MOVE:
                xPosition=event.getX();
                yPosition=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xPosition=event.getX();
                yPosition=event.getY();
                break;
        }
        glSurfaceView.requestRender();
        myRender.changeBackGround(xPosition,yPosition);

        return super.onTouchEvent(event);
    }
}
