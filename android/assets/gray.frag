#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;


void main() {
    vec4 tempColor = v_color* texture2D(u_texture,v_textCoords);
    if(tempColor.a>0.01){
        tempColor.r = 0.765;
        tempColor.g = 0.733;
        tempColor.b = 0.733;
        tempColor.a = v_color.a;
    }else{

    }
    gl_FragColor = tempColor;
}