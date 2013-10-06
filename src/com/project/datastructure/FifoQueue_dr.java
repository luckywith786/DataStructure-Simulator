package com.project.datastructure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FifoQueue_dr extends Activity 
{
	int s=90,count=0,countfrnt=0,s1=90;
	String queue[]={"-99","-99","-99","-99","-99","-99","-99","-99","-99","-99"};
	ImageView rear1;ImageView front;
	TextView txtrear;TextView txtfront;
	TextView node1;TextView node2;TextView node3;TextView node4;
	TextView node5;TextView node6;TextView node7;TextView node8;TextView node9;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifo_queue_dr);
		rear1=(ImageView)findViewById(R.id.Qrear1);front=(ImageView)findViewById(R.id.Qfront);
		txtrear=(TextView)findViewById(R.id.txtRear);txtfront=(TextView)findViewById(R.id.txtFront);
		node1=(TextView)findViewById(R.id.fifoNode1);node2=(TextView)findViewById(R.id.fifoNode2);
		node3=(TextView)findViewById(R.id.fifoNode3);node4=(TextView)findViewById(R.id.fifoNode4);
		node5=(TextView)findViewById(R.id.fifoNode5);node6=(TextView)findViewById(R.id.fifoNode6);
		node7=(TextView)findViewById(R.id.fifoNode7);node8=(TextView)findViewById(R.id.fifoNode8);
		node9=(TextView)findViewById(R.id.fifoNode9);
		rear1.setVisibility(View.INVISIBLE);front.setVisibility(View.INVISIBLE);
	}
	public void DEQUEUE(View btnDel)
	{
		if(countfrnt < 9 && count > 0 && countfrnt < count)
		{
			front.setVisibility(View.VISIBLE);
			countfrnt++;
			Toast.makeText(FifoQueue_dr.this,"DELETED : "+queue[countfrnt], Toast.LENGTH_SHORT).show();
			queue[countfrnt]="-99";
			redrawDel();
			RelativeLayout.LayoutParams arrowlp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams txtlp1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
			arrowlp.setMargins(17+s1,285, 0, 100);
			txtlp1.setMargins(s1, 328, 0, 100);
			front.setLayoutParams(arrowlp);
			txtfront.setLayoutParams(txtlp1);
			s1=s1+66;
			
		}
		else
			new fifoQueue_b().execute("empty");
	}
	public void ENQUEUE(View btnAdd)
	{
		
		AlertDialog.Builder inputAdd=new AlertDialog.Builder(this);
		inputAdd.setTitle("ENQUEUE ").setMessage("Enter an Element ");
		final EditText value=new EditText(this);
		inputAdd.setView(value);
		inputAdd.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(value.getText().toString().length()!=0)
				{
					if(count<9)
					{
						count++;
						rear1.setVisibility(View.VISIBLE);
						queue[count]=value.getText().toString();
						redraw();
						RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
						RelativeLayout.LayoutParams lp1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
						lp.setMargins(17+s, 139, 0, 100);
						lp1.setMargins(s, 120, 0, 100);
						rear1.setLayoutParams(lp);
						txtrear.setLayoutParams(lp1);
						s=s+66;
					}
					else
						new fifoQueue_b().execute("full");
				}
				else
					new fifoQueue_b().execute("value");
			}
		});
		inputAdd.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				return;
			}
		});
		inputAdd.show();
		
	}
	public void CLEAR(View btnClear)
	{
		Intent replyIntent =new Intent(btnClear.getContext(), FifoQueue_dr.class);
		startActivityForResult(replyIntent, 0);
	}
	public void DOCS(View btnDocs)
	{
		AlertDialog.Builder link_doc=new AlertDialog.Builder(this);
		link_doc.setTitle("Documentation").setMessage("Choose a type ");
		final RadioGroup Qrg=new RadioGroup(this);
		final RadioButton hi=new RadioButton(this);
		hi.setText("Hindi");
		final RadioButton en=new RadioButton(this);
		en.setText("English");
		Qrg.addView(hi);Qrg.addView(en);
		link_doc.setView(Qrg);
		link_doc.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(hi.isChecked())
				{
					CopyReadAssets("FifoQueue_hi.pdf");
				}
				else if(en.isChecked())
				{
					CopyReadAssets("FifoQueue_en.pdf");
				}
			}
		});
		link_doc.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
				return;
			}
		});
		link_doc.show();
	} 
	private void CopyReadAssets(String x)
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), x);
        try
        {
            in = assetManager.open(x);
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } 
        catch (Exception e){}
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + getFilesDir() + "/"+x),"application/pdf");
        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }
	public void Cqueue(View btnCqueue)
	{
		Intent replyIntent =new Intent(btnCqueue.getContext(), CircularQueue_dr.class);
		startActivityForResult(replyIntent, 0);
	}
	private void redraw()
	{
		if(queue[1].compareTo("-99")!=0)
			node1.setText(queue[1]);
		if(queue[2].compareTo("-99")!=0)
			node2.setText(queue[2]);
		if(queue[3].compareTo("-99")!=0)
			node3.setText(queue[3]);
		if(queue[4].compareTo("-99")!=0)
			node4.setText(queue[4]);
		if(queue[5].compareTo("-99")!=0)
			node5.setText(queue[5]);
		if(queue[6].compareTo("-99")!=0)
			node6.setText(queue[6]);
		if(queue[7].compareTo("-99")!=0)
			node7.setText(queue[7]);
		if(queue[8].compareTo("-99")!=0)
			node8.setText(queue[8]);
		if(queue[9].compareTo("-99")!=0)
			node9.setText(queue[9]);
		txtrear.setText("REAR :"+count);
	}
	private void redrawDel()
	{
		node1.setText(" ");node2.setText(" ");node3.setText(" ");
		node4.setText(" ");node5.setText(" ");node6.setText(" ");
		node7.setText(" ");node8.setText(" ");node9.setText(" ");
		if(queue[1].compareTo("-99")!=0)
			node1.setText(queue[1]);
		if(queue[2].compareTo("-99")!=0)
			node2.setText(queue[2]);
		if(queue[3].compareTo("-99")!=0)
			node3.setText(queue[3]);
		if(queue[4].compareTo("-99")!=0)
			node4.setText(queue[4]);
		if(queue[5].compareTo("-99")!=0)
			node5.setText(queue[5]);
		if(queue[6].compareTo("-99")!=0)
			node6.setText(queue[6]);
		if(queue[7].compareTo("-99")!=0)
			node7.setText(queue[7]);
		if(queue[8].compareTo("-99")!=0)
			node8.setText(queue[8]);
		if(queue[9].compareTo("-99")!=0)
			node9.setText(queue[9]);                                               
		txtfront.setText("FRONT :"+countfrnt);
		
	}
	class fifoQueue_b extends AsyncTask<String, Void ,String>
	{

		@Override
		protected String doInBackground(String... arg0) 
		{
			return arg0[0];
		}

		@Override
		protected void onPostExecute(String result) 
		{
			
			super.onPostExecute(result);
			if(result.compareTo("full")==0)
				Toast.makeText(FifoQueue_dr.this,"QUEUE is FULL !!", Toast.LENGTH_SHORT).show();
			else if(result.compareTo("value")==0)
				Toast.makeText(FifoQueue_dr.this,"ERROR in value !!", Toast.LENGTH_LONG).show();
			else if(result.compareTo("empty")==0)
				Toast.makeText(FifoQueue_dr.this,"QUEUE EMPTY !!", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifo_dr, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
    {
		switch (item.getItemId()) 
		{
			case R.id.itemTree:
				Intent replyIntent =new Intent(this, Tree_dr.class);
				startActivityForResult(replyIntent, 0);
			break;
			
			case R.id.itemLink:
				Intent replyIntent1 =new Intent(this, LinkList_dr.class);
				startActivityForResult(replyIntent1, 0);	
				break;
			case R.id.itemQueue:
				Intent replyIntent2 =new Intent(this, CircularQueue_dr.class);
				startActivityForResult(replyIntent2, 0);
				break;
			case R.id.itemStack:
				Intent replyIntent3 =new Intent(this, Stack_dr.class);
				startActivityForResult(replyIntent3, 0);
				break;

			default:
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			break;
		}
		return true;
    }

}
