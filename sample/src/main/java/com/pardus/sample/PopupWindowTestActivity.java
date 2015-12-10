package com.pardus.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * Created by Tom on 2015/12/10.
 */
public class PopupWindowTestActivity extends Activity {
    private PopupWindow pwSelectList;
    private Button btnSelect;
    private EditText etSelect;
    private String[] arr = {"语文","数学","物理","化学","历史","英语","政治"};

    public static void start(Context context) {
        Intent starter = new Intent(context, PopupWindowTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        btnSelect = (Button) findViewById(R.id.btSelect);
        etSelect = (EditText) findViewById(R.id.etSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList(arr,etSelect);
            }
        });
    }
    private void showList(final String[] arr, final EditText editText) {

        if (arr == null || arr.length == 0) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.view_popupwindow_select_list, null);
        ListView listView = (ListView) view.findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PopupWindowTestActivity.this, R
                .layout.view_popupwindow_operation_item, R.id.tvOperationItemName, arr);
        listView.setAdapter(arrayAdapter);
        showPopupWindow(view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                editText.setText(arr[position]);
                pwSelectList.dismiss();
            }
        });
    }

    private void showPopupWindow(View view) {
        if (pwSelectList != null) {
            pwSelectList.dismiss();
            pwSelectList.setContentView(view);
        } else {
            pwSelectList = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT);
            pwSelectList.setOutsideTouchable(true);
            pwSelectList.setFocusable(true);
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            pwSelectList.setBackgroundDrawable(dw);
            pwSelectList.setAnimationStyle(R.style.AnimBottom);
            pwSelectList.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            pwSelectList.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        backgroundAlpha(0.7f);
        pwSelectList.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        pwSelectList.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

}
