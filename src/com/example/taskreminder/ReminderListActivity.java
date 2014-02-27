package com.example.taskreminder;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;


public class ReminderListActivity extends ListActivity {

	
	public static final String LOG_TAG = "TaskReminder";
	
	final static int ACTIVITY_CREATE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "ReminderListActivity.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_list);
		
		String[] items = new String[] { "Foo", "Bar", "Fizz", "Bin" };
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.reminder_row, R.id.text1, items);
		setListAdapter(adapter);
		
		registerForContextMenu(getListView());
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.i(LOG_TAG, "ReminderListActivity.onListItemClick");
		super.onListItemClick(l,  v, position, id);
		Intent i = new Intent(this, ReminderEditActivity.class);
		i.putExtra("RowId", id);
		startActivity(i);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.list_menu,      menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		Log.i(LOG_TAG, "ReminderListActivity.onCreateContextMenu");
		super.onCreateContextMenu(menu, v, menuInfo);
		super.getMenuInflater().inflate(R.menu.list_menu_item_longpress, menu);
	}
	

	
	private void createReminder() {
		Intent i = new Intent(this, ReminderEditActivity.class);
		super.startActivityForResult(i, ReminderListActivity.ACTIVITY_CREATE);
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(LOG_TAG, "ReminderListActivity.onActivityResult");
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Log.i(LOG_TAG, "resultCode is RESULT_OK");
		}
		else if (resultCode == RESULT_CANCELED) {
			Log.i(LOG_TAG, "resultCode is RESULT_CANCELED");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(LOG_TAG, "ReminderListActivity.onOptionsItemSelected");
		switch(item.getItemId()) {
		case R.id.menu_insert :
			createReminder();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.i(LOG_TAG, "ReminderListActivity.onContextItemSelected");
		switch(item.getItemId()) {
		case R.id.menu_delete :
			Log.i(LOG_TAG, "Todo: figure out how to delete");
			return true;
		default:
				return super.onContextItemSelected(item);
		}
	}
	
	
	

}
