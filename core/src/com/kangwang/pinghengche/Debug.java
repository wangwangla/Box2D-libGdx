package com.kangwang.pinghengche;

import com.badlogic.gdx.utils.Array;
import com.kangwang.word.Constant;

public class Debug {
    private Array<Float> array;
    public Debug(){
        array = new Array<>();
    }

    public void step(float value){
        this.array.add(value);
        if (this.array.size> Constant.width){

        }
    }
}
