package com.kangwang.pinghengche;

import com.badlogic.gdx.utils.Array;

public class Filter {
    private Array<Float> array ;
    public Filter(){
        array = new Array<>();
    }

    public float getVlue(){
        float num = 0;
        for (Float aFloat : array) {
            num += aFloat;
        }
        return num / array.size;
    }
}
