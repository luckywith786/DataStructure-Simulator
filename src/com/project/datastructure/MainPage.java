package com.project.datastructure;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainPage extends Activity implements Runnable
{
	ImageView i1;
	ImageView i2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		new Thread(this).start();
	}
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(4000);
			Intent replyIntent =new Intent(this, DisplayMain.class);
    		startActivityForResult(replyIntent, 0);
		}
		catch(Exception e){return;}
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
    {
		if(item.getItemId()==R.id.itemDisplay)
		{
			Intent replyIntent =new Intent(this, DisplayMain.class);
			startActivityForResult(replyIntent, 0);
		}
		return true;
    }
}