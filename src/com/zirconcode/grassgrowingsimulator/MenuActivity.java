package com.zirconcode.grassgrowingsimulator;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}
	
	public void playGame(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void clearData(View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMessage(R.string.clear_prompt);
		builder.setPositiveButton(R.string.clear_yes, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // delete lawn file
	        	   File f = new File(getFilesDir(), getString(R.string.lawn_filename));
	        	   boolean success = f.delete();
	        	   // TODO some form of feedback as to what actually happened...
	           }
	       });
		builder.setNegativeButton(R.string.clear_no, null);
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void leaveApp(View view) {
	     finish();
	}
	
}
