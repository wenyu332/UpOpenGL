package liu.lesson02;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.R.attr.angle;

/**
 * Created by john on 2016/8/19.
 */

public class MyRender implements GLSurfaceView.Renderer {
    private float []model;//加载数据
    private float []color;
    private float []light;
    private ByteBuffer floatBuffer;//将数据缓存到本地缓存区
    private FloatBuffer fb;
    private FloatBuffer fbColor;
    private float[]viewMatrix=new float[16];
    private float[]modelMatrix=new float[16];
    private float[]projectMatrix=new float[16];
    private float[]mvpMatrix=new float[16];
    private String vertext;
    private String fragment;

    private int trangleProgram;
    private int colorFloat;
    private int positionHandle;
    private int colorHandle;
    private int mvpMatrixHandle;
    private int lightHandle;
    private Context context;
    private float angle;
    private String loadString(Context context,int resId){
        InputStream inputStream = context.getResources().openRawResource(resId);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public MyRender(Context context)
    {
        this.context=context;
        model=new float[]{
                0.0f,1.0f,0.0f,
                -1.0f,-1.0f,0.0f,
                1.0f,-1.0f,0.0f

               /* 1.0f,-1.0f,0.0f,
                1.0f,1.0f,0.0f,
                -1.0f,1.0f,0.0f*/
        };
        color=new float[]{
                1.0f,0.0f,0.0f,1.0f,
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f

                /*1.0f,0.0f,0.0f,1.0f,
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f*/
        };
        light=new float[]{0.0f,0.0f,1.0f};
        floatBuffer= ByteBuffer.allocateDirect(model.length*4);
        floatBuffer.order(ByteOrder.nativeOrder()) ;
        fb=floatBuffer.asFloatBuffer();
        fb.put(model);
        fb.position(0);

        ByteBuffer bcolor=ByteBuffer.allocateDirect(color.length*4).order(ByteOrder.nativeOrder());
        fbColor=bcolor.asFloatBuffer();
        fbColor.put(color);
        fbColor.position(0);
       /* vertext=loadString(context,R.raw.vertex_shader);
        Log.i("hhh",vertext);
        fragment=loadString(context,R.raw.fragment_shader);*/
    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.1f,0.1f,0.1f,0.5f);
        vertext=loadString(this.context,R.raw.vertex_shader);
        fragment=loadString(this.context,R.raw.fragment_shader);
        Log.i("hhh",vertext);
       // fragment=loadString(context,R.raw.fragment_shader);
      final int  vertextShader=GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertextShader,vertext);
       // final int  fragmentShader=GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glCompileShader(vertextShader);
        //GLES20.glShaderSource(vertextShader,vertext);
        final int  fragmentShader=GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader,fragment);
        //GLES20.glCompileShader(vertextShader);
        GLES20.glCompileShader(fragmentShader);
        trangleProgram=GLES20.glCreateProgram();
        GLES20.glAttachShader(trangleProgram,vertextShader);
        GLES20.glAttachShader(trangleProgram,fragmentShader);
        GLES20.glLinkProgram(trangleProgram);
        GLES20.glUseProgram(trangleProgram);

        positionHandle=GLES20.glGetAttribLocation(trangleProgram,"a_Position");
        mvpMatrixHandle=GLES20.glGetUniformLocation(trangleProgram,"u_Matrix");
        colorHandle=GLES20.glGetAttribLocation(trangleProgram,"a_Color");
        lightHandle=GLES20.glGetAttribLocation(trangleProgram,"a_Light");

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0,0,width,height);

        Matrix.perspectiveM( projectMatrix,0,45, (float) width
                / (float) height, 1f, 10f);
        Matrix.setIdentityM(modelMatrix,0);
        Matrix.scaleM(modelMatrix, 0,0.5f,0.5f,0.5f);
      /*  Matrix.setIdentityM(modelMatrix,0);
       // Matrix.translateM(modelMatrix, 0, 0f, 0f, -2.5f);
        Matrix.scaleM(modelMatrix, 0,0.5f,0.5f,0.5f);
        Matrix.multiplyMM(mvpMatrix,0,projectMatrix,0,modelMatrix,0);
        GLES20.glUseProgram(trangleProgram);
        GLES20.glVertexAttribPointer(positionHandle,3,GLES20.GL_FLOAT,false,0,fb);
        GLES20.glVertexAttribPointer(colorHandle,4,GLES20.GL_FLOAT,false,0,fbColor);
        GLES20.glUniformMatrix4fv(mvpMatrixHandle,1,false,modelMatrix,0);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glEnableVertexAttribArray(mvpMatrixHandle);
        GLES20.glEnableVertexAttribArray(colorHandle);*/
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
       // Matrix.multiplyMM(mvpMatrix,0,projectMatrix,0,modelMatrix,0);
        //Matrix.setIdentityM(modelMatrix,0);
         Matrix.rotateM(modelMatrix, 0,-angle, 0f, 0.5f, 0.0f);
        //Matrix.scaleM(modelMatrix, 0,0.5f,0.5f,0.5f);
        Matrix.multiplyMM(mvpMatrix,0,projectMatrix,0,this.modelMatrix,0);
        GLES20.glUseProgram(trangleProgram);
        GLES20.glVertexAttribPointer(positionHandle,3,GLES20.GL_FLOAT,false,0,fb);
        GLES20.glVertexAttribPointer(colorHandle,4,GLES20.GL_FLOAT,false,0,fbColor);
        GLES20.glUniformMatrix4fv(mvpMatrixHandle,1,false,modelMatrix,0);
        GLES20.glVertexAttrib3f(lightHandle,0,0,0.5f);

            Log.i("angle", angle + "");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glEnableVertexAttribArray(mvpMatrixHandle);
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glEnableVertexAttribArray(lightHandle);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);
        angle++;
        if (angle>=30)
            angle=0;
    }
    public float[] changeRotate(float x,float y)
    {
        Matrix.setIdentityM(this.modelMatrix,0);
        Matrix.rotateM(modelMatrix,0,30,0,0.5f,0);

        Log.i("modelMatrix", "x==" +x+ "y=="+y);

        return modelMatrix;
    }
 }
