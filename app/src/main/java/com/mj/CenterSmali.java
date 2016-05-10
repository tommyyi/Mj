package com.mj;

import android.app.Activity;

/**
 * Created by Administrator on 2016/5/10.
 */
public class CenterSmali
{
    public static void init(Activity activity)
    {
        Center.init(activity);
    }

    public static void pay(String billingIndex)
    {
        Center.pay(billingIndex);
    }
}
