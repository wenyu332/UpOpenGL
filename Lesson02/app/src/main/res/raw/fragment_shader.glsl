
precision mediump float;
varying vec4 v_Color;
varying vec4 v_Position;
varying vec4 v_Light;
void main()
{
    float dis=length(v_Light-v_Position);
    dis=dis*0.03;
        gl_FragColor=v_Color;
}