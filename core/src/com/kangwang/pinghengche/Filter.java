package com.kangwang.pinghengche;

import com.badlogic.gdx.utils.Array;

public class Filter {
    private Array<Float> values ;
    public Filter(){
        values = new Array<>();
    }

    public void push(float value) {
        this.values.add(value);
        if(this.values.size > 30) {
            this.values.removeIndex(0);
        }
    }

    public float getValue(){
        float num = 0;
        for (Float aFloat : values) {
            num += aFloat;
        }
        return num / values.size;
    }
}
