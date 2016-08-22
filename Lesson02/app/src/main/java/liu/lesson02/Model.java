package liu.lesson02;

/**
 * Created by john on 2016/8/19.
 */

public class Model {
    private static  float []model;
    public static float[] getModel(String modelName)
    {
        if (modelName.equals("Trangle"))
            model= new float []
            {
                0.0f,2.0f,0.0f,
                -1.0f,-1.0f,0.0f,
                -1.0f,1.0f,0.0f
            };

        else if (modelName.equals("Quene"))
            model= new float[]{
                    -1.0f,1.0f,
                    -1.0f,-1.0f,
                    1.0f,1.0f,
                    -1.0f,-1.0f,
                    1.0f,-1.0f,
                    1.0f,1.0f};
        return model;
    }
}
