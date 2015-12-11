package com.battistello.andrea.framework.impl;

import java.util.List;

import android.view.View.OnTouchListener;

import com.battistello.andrea.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
