package com.example.taskreminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;


public class ReminderEditActivity extends Activity {

	
	private static final int DATE_PICKER_DIALOG = 0;
	private static final int TIME_PICKER_DIALOG = 1;
	
	
	private Calendar mCalendar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(ReminderListActivity.LOG_TAG, "ReminderEditActivity.onCreate");
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.reminder_edit);
		
		this.mCalendar = Calendar.getInstance();
		
		Intent intent = super.getIntent();
		
		if(intent != null) {
			Bundle extras = intent.getExtras();
			if(extras != null) {
				long rowId = extras.getLong("RowId"); // MAGIC STRING - Must Fix
				Formatter formatter = new Formatter();
				formatter = formatter.format("ReminderEditActivity.onCreate: RowId is %d", rowId);
				String logMessage = formatter.toString();
				Log.i(ReminderListActivity.LOG_TAG, logMessage);
			}
		}
		
		registerButtonListenersAndSetDefaultText();
	}
	
	private Button getDateButton() {
		return (Button)this.findViewById(R.id.reminder_date);
	}
	
	private Button getTimeButton() {
		return (Button)this.findViewById(R.id.reminder_time);
	}

	
	
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Log.i(ReminderListActivity.LOG_TAG, "ReminderEditActivity.onCreateDialog");
		switch(id) {
			case DATE_PICKER_DIALOG :
				return showDatePicker();
			case TIME_PICKER_DIALOG :
				return showTimePicker();				
		}
		return super.onCreateDialog(id);
	}

	private TimePickerDialog showTimePicker() {
		TimePickerDialog timePicker = new TimePickerDialog(
															ReminderEditActivity.this, 
															new TimePickerDialog.OnTimeSetListener() {
																
																@Override
																public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
																	mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
																	mCalendar.set(Calendar.MINUTE, minute);
																	updateTimeButtonText();
																	
																}
															}, 
															mCalendar.get(Calendar.HOUR), 
															mCalendar.get(Calendar.MINUTE), 
															false
														);
		return timePicker;
		
	}

	private DatePickerDialog showDatePicker() {
		DatePickerDialog datePicker = new DatePickerDialog(ReminderEditActivity.this,
				                                           new DatePickerDialog.OnDateSetListener() {

															@Override
															public void onDateSet(
																	DatePicker view,
																	int year,
																	int monthOfYear,
																	int dayOfMonth) {
																mCalendar.set(Calendar.YEAR,  year);
																mCalendar.set(Calendar.MONTH,  monthOfYear);
																mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
																ReminderEditActivity.this.updateDateButtonText();
																
															}
															},
															mCalendar.get(Calendar.YEAR),
															mCalendar.get(Calendar.MONTH),
															mCalendar.get(Calendar.DAY_OF_MONTH)
															);
		return datePicker;
	}

	private void registerButtonListenersAndSetDefaultText() {
		getDateButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(DATE_PICKER_DIALOG);
			}
			
		});
		
		getTimeButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_PICKER_DIALOG);				
			}
		
			
		});
		
		updateDateButtonText();
		updateTimeButtonText();
	}

	private void updateTimeButtonText() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
		String timeForButton = dateFormat.format(mCalendar.getTime());
		getTimeButton().setText(timeForButton);
		
	}

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "kk:mm";
	
	private void updateDateButtonText() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateForButton = dateFormat.format(mCalendar.getTime());
		getDateButton().setText(dateForButton);
	}
}
