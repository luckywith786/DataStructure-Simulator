package com.project.datastructure;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DisplayMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_main);
	}
	public void STACK(View btnStack)
	{
		Intent replyIntent =new Intent(btnStack.getContext(), Stack_dr.class);
		startActivityForResult(replyIntent, 0);
	}
	public void TREE(View btnTree)
	{
		Intent replyIntent =new Intent(btnTree.getContext(), Tree_dr.class);
		startActivityForResult(replyIntent, 0);
	}
	public void LINK(View btnLink)
	{
		Intent replyIntent =new Intent(btnLink.getContext(), LinkList_dr.class);
		startActivityForResult(replyIntent, 0);
	}
	public void QUEUE(final View btnQueue)
	{
		AlertDialog.Builder inputQueue=new AlertDialog.Builder(this);
		inputQueue.setTitle("QUEUE TYPE ").setMessage("Choose a type ");
		final RadioGroup Qrg=new RadioGroup(this);
		final RadioButton fifo=new RadioButton(this);
		fifo.setText("FIFO    QUEUE");
		final RadioButton Cqu=new RadioButton(this);
		Cqu.setText("CIRCULAR QUEUE");
		Qrg.addView(fifo);Qrg.addView(Cqu);
		inputQueue.setView(Qrg);
		inputQueue.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(fifo.isChecked())
				{
					Intent replyIntent =new Intent(btnQueue.getContext(), FifoQueue_dr.class);
					startActivityForResult(replyIntent, 0);
				}
				else if(Cqu.isChecked())
				{
					Intent replyIntent1 =new Intent(btnQueue.getContext(), CircularQueue_dr.class);
					startActivityForResult(replyIntent1, 0);
				}
			}
		});
		inputQueue.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
				return;
			}
		});
		inputQueue.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
    {
		if(item.getItemId()==R.id.action_settings)
		{
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		return true;
    }

}
