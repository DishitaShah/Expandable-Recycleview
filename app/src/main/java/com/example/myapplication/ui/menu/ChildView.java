package com.example.myapplication.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.models.Menu;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Layout(R.layout.child_layout)
public class ChildView {
    private static String TAG ="ChildView";

    @View(R.id.child_name)
    TextView textViewChild;


    @View(R.id.child_image)
    ImageView childImage;

    private Context mContext;
    private String menu;
    Activity activity;
    HashMap<String,String> prices;
    String price;

    public ChildView(Context mContext, String menu, HashMap<String,String> prices,Activity a) {
        this.mContext = mContext;
        this.menu = menu;
        this.activity = a;
        this.prices = prices;
    }

    @Resolve
    private void onResolve(){
        textViewChild.setText(menu);
        textViewChild.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(mContext, menu, Toast.LENGTH_SHORT).show();
            }
        });
        childImage.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Set<String> keys = prices.keySet();
                for (String key : keys) {
                    System.out.println("Key = " + key);
                        if(key.equals(menu))
                    {
                        price = prices.get(key);
                    }
                }
                showqauntity(price,menu);
            }
        });
    }

    public void showqauntity(String price, String name) {
        LinearLayout viewGroup = (LinearLayout) activity.findViewById(R.id.ll_popup);
        LayoutInflater layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        android.view.View layout = layoutInflater.inflate(R.layout.quantity, viewGroup);
        TextView item_qty = layout.findViewById(R.id.item_qty);
        TextView item_name = layout.findViewById(R.id.item_name);
        TextView tv_cancel_order = layout.findViewById(R.id.tv_cancel_order);
        TextView tv_place_order = layout.findViewById(R.id.tv_place_order);
        NumberPicker number_picker = layout.findViewById(R.id.number_picker);
        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(activity);
        popup.setContentView(layout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        popup.setWidth(width  -150);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popup.setBackgroundDrawable(ContextCompat.getDrawable(activity, android.R.color.white));

        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        if (android.os.Build.VERSION.SDK_INT > 22) {
            android.view.View container = (android.view.View) popup.getContentView().getParent().getParent();
            WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.6f;
            wm.updateViewLayout(container, p);
        } else {
            android.view.View container = (android.view.View) popup.getContentView().getParent();
            WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.6f;
            wm.updateViewLayout(container, p);
        }
        item_qty.setText(price);
        item_name.setText(name);
        tv_cancel_order.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                popup.dismiss();
            }
        });
        tv_place_order.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                popup.dismiss();
            }
        });
        number_picker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                item_qty.setText(String.valueOf(Double.parseDouble(price)*value));
            }
        });
    }
}