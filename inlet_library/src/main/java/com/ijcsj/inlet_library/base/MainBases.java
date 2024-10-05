package com.ijcsj.inlet_library.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.inlet_library.BR;

public class MainBases extends BaseObservable {
    private boolean isWarn;
    @Bindable
    public boolean isWarn() {
        return isWarn;
    }

    public void setWarn(boolean warn) {
        isWarn = warn;
        notifyPropertyChanged( BR.warn);
    }
}
