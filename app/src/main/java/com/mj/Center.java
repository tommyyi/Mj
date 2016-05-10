package com.mj;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.mj.jar.pay.BillingListener;
import com.mj.jar.pay.MjBillingCode;
import com.mj.jar.pay.MjPaySDK;
import com.snowcity.statistic.Hello;

/**
 * Created by Administrator on 2016/5/10.
 */
public class Center
{
    private static MjPaySDK mjpaysdk;

    public static void init(Activity activity)
    {
        BillingListener billinglistener=new BillingListener()
        {
            @Override
            public void onInitResult(int i)
            {
                Log.w("onInitResult",i+"");
            }

            @Override
            public void onBillingResult(int i, Bundle bundle)
            {
                if (i == MjBillingCode.BILL_OK)
                {
                    Log.w("onBillingResult", "success");
                }
                else
                {
                    Log.w("onBillingResult","fail");
                }
            }
        };
        String appid= null;
        try
        {
            appid = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_META_DATA).applicationInfo.metaData.getString("mjappid").replace("mjappid:","");
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        String distro="YOUDIAN";
        String fm= null;
        try
        {
            fm = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_META_DATA).applicationInfo.metaData.getString("mjfm");
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        mjpaysdk = new MjPaySDK(activity, billinglistener, appid, distro, fm);
        mjpaysdk.SetDebugMode(true);

        Hello.knock(activity);
    }

    public static void pay(String billingIndex)
    {
        String orderNo=System.currentTimeMillis()+"";
        String payCode=getPayCode(billingIndex);
        String price=getPrice(billingIndex);
        mjpaysdk.pay(orderNo,payCode,price);
    }

    private static String getPrice(String billingIndex)
    {
        String price="200";

        if(billingIndex.equals("006"))
            price="200";
        if(billingIndex.equals("001"))
            price="600";
        if(billingIndex.equals("002"))
            price="800";
        if(billingIndex.equals("003"))
            price="1400";
        if(billingIndex.equals("004"))
            price="2000";
        if(billingIndex.equals("005"))
            price="2000";

        return price;
    }

    private static String getPayCode(String billingIndex)
    {
        String payCode="000106000";

        if(billingIndex.equals("006"))
            payCode="000106000";
        if(billingIndex.equals("001"))
            payCode="000106001";
        if(billingIndex.equals("002"))
            payCode="000106002";
        if(billingIndex.equals("003"))
            payCode="000106003";
        if(billingIndex.equals("004"))
            payCode="000106004";
        if(billingIndex.equals("005"))
            payCode="000106004";

        return payCode;
    }
}
