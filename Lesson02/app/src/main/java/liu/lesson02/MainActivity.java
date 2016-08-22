package liu.lesson02;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private MyRender myRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView=new GLSurfaceView(this);
        glSurfaceView.setEGLConfigChooser(8,8,8,8,16,8);

        glSurfaceView.setEGLContextClientVersion(2);

        myRender=new MyRender(this);
        glSurfaceView.setRenderer(myRender);
        //glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();
                float xPosition=0;
                float yPosition=0;
                switch (action)
                {
                    case MotionEvent.ACTION_MOVE:
                        xPosition=motionEvent.getX()-xPosition;
                        yPosition=motionEvent.getY()-yPosition;

                        break;
                    case MotionEvent.ACTION_DOWN:
                        xPosition=motionEvent.getX();
                        yPosition=motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        xPosition=motionEvent.getX()-xPosition;
                        yPosition=motionEvent.getY()-yPosition;

                        break;
                }
                glSurfaceView.requestRender();
                myRender.changeRotate(xPosition,yPosition);
                return false;
            }
        });
        setContentView(glSurfaceView);
    }

}
