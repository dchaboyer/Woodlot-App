package com.example.drew.wccc;
import android.widget.Button;
/**
 * Created by Drew on 3/4/2017.
 */

public class IndexedButton {
    protected Button button;
    protected int index;

    public IndexedButton(Button button, int index) {
        this.button = button;
        this.index = index;
    }

    public Button getButton() {
        return button;
    }

    public int getIndex() {
        return index;
    }
}
