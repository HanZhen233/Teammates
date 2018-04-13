package com.example.teammates.search_part;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by linji on 2018/4/13.
 */

public interface OnItemSelectedListener {
    void onItemSelected(AdapterView<?> parent, View view, int pos, long id);
    void onNothingSelected(AdapterView<?> parent);
}
