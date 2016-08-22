uniform mat4 u_Matrix;
attribute vec4 a_Position;
attribute vec4 a_Color;
attribute vec4 a_Light;
varying vec4 v_Color;
varying vec4 v_Position;
varying vec4 v_Light;
void main()
{
     v_Color = a_Color;

     gl_Position =u_Matrix* a_Position;
     v_Position=gl_Position;
}