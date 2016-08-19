package liu.lesson01;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by john on 2016/8/19.
 */

public class MyRender implements GLSurfaceView.Renderer {
    private float red;
    private float green;
    private static  final String TAG="MyRender";
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        Log.i(TAG,"onSurfaceCreated");
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        Log.i(TAG,"onSurfaceChanged");
        GLES20.glViewport(0,0,width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        Log.i(TAG,"onDrawFrame");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(red,green,0.5f,0.5f);
    }
    public void changeBackGround(float x,float y)
    {
        if (x>255)
        {
            x=255/x;
        }
        else
        {
            x=x/255;
        }
        if (y>255)
        {
            y=255/y;
        }
        else
        {
            y=y/255;
        }
        this.red=x;
        this.green=y;
    }

}
