package com.betterschool.co.utilityClass;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;


public class datePickerClass {
    public static int years = 0;
    public static int month = 0;
    public static int day = 0;
    public static DateConverter converter1 = new DateConverter();
    public static String exactDate;
    public static void datePicker(Context context, StringPayload stringPayload){

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"vazir.ttf");
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(PersianDatePickerDialog.THIS_YEAR, 1, 1);
        PersianDatePickerDialog picker = new PersianDatePickerDialog(context)
                .setPositiveButtonString("انتخاب همین تاریخ")
                .setNegativeButton("انصراف")
                .setTodayButton("بیا به تاریخ امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1390)
                .setTypeFace(typeface)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(initDate)
                .setActionTextColor(Color.RED)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)

                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        PersianCalendar initDate2 = new PersianCalendar();
                        initDate2.setPersianDate(PersianDatePickerDialog.THIS_YEAR, 1, 1);
                        years = persianCalendar.getPersianYear();
                        month = persianCalendar.getPersianMonth();
                        day = persianCalendar.getPersianDay();
                        converter1.persianToGregorian(years,month,day);
                        exactDate = converter1.toString();
                        stringPayload.stringPicker(exactDate,years + "-" + month + "-" + day);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        picker.setInitDate(initDate);
        picker.show();
    }
}
