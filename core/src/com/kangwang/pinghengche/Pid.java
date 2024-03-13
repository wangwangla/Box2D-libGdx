package com.kangwang.pinghengche;

public class Pid {
    private int integral;
    float p;
    float i;
    float d;
    float pError;
    float cError;
    float output;

    public Pid(float p,float i,float d){

        this.p = p;
        this.i = i;
        this.d = d;

        this.cError = 0;
        this.pError = 0;
        this.integral = 0;

        this.output = 0;

    }

    public void setcError(float cError) {
        this.cError = cError;
    }

    public void step(float deltaTime){
        this.integral += this.cError * deltaTime;
        float derivative = (this.cError - this.pError) / deltaTime;
        this.output = this.p * this.cError + this.i * integral + this.d * derivative;
        this.pError = this.cError;
    }

    public float getOutput(){
        return output;
    }

}
