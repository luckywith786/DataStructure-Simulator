package com.project.datastructure;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LinkList_dr extends Activity {

	TextView node1;TextView node2;TextView node3;
	TextView node4;TextView node5;TextView node6;
	Button btnLinkDel;Button btnLinkAdd;
	ImageView left1;ImageView left2;ImageView left3;
	ImageView left4;ImageView left5;
	ImageView right1;ImageView right2;ImageView right3;
	ImageView right4;ImageView right5;
	String str[]={"-99","-99","-99","-99","-99","-99","-99","-99","-99"};
	String ch,del_val;
	int del_pos,count=0,rev=0,x1,dec;
	Object o1[]={1,"y","l"};
	//ArrayList<Object> str1=new ArrayList<Object>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_list_dr);
		btnLinkDel=(Button)findViewById(R.id.btnLinkDel);
		btnLinkAdd=(Button)findViewById(R.id.btnLinkAdd);
		node1=(TextView)findViewById(R.id.LinkNode1);node2=(TextView)findViewById(R.id.LinkNode2);
		node3=(TextView)findViewById(R.id.LinkNode3);node4=(TextView)findViewById(R.id.LinkNode4);
		node5=(TextView)findViewById(R.id.LinkNode5);node6=(TextView)findViewById(R.id.LinkNode6);
		right1=(ImageView)findViewById(R.id.imgarrow1);right2=(ImageView)findViewById(R.id.imgarrow2);
		right3=(ImageView)findViewById(R.id.imgarrow3);right4=(ImageView)findViewById(R.id.imgarrow4);
		right5=(ImageView)findViewById(R.id.imgarrow5);
		left1=(ImageView)findViewById(R.id.imgarrowLeft1);left2=(ImageView)findViewById(R.id.imgarrowLeft2);
		left3=(ImageView)findViewById(R.id.imgarrowLeft3);left4=(ImageView)findViewById(R.id.imgarrowLeft4);
		left5=(ImageView)findViewById(R.id.imgarrowLeft5);
		btnLinkDel.setEnabled(false);
	}
	public void ADD(View btnAdd)
	{
		AlertDialog.Builder inputAdd=new AlertDialog.Builder(this);
		inputAdd.setTitle("ADD ").setMessage("Enter an Element ");
		final EditText value=new EditText(this);
		inputAdd.setView(value);
		inputAdd.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(value.getText().toString().length()!=0)
				{
					str[++count] =value.getText().toString();
					redraw();
				}
				else
					new linklist_b().execute("value");
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
	
	public void DELETE(View btnDel)
	{
		AlertDialog.Builder inputDel=new AlertDialog.Builder(this);
		inputDel.setTitle("DELETE ").setMessage("CLICK AN ORDER  ");
		final RadioGroup rg=new RadioGroup(this);
		final RadioButton val=new RadioButton(this);
		val.setText("VALUE WISE");
		final RadioButton pos=new RadioButton(this);
		pos.setText("POSITION WISE");
		rg.addView(pos);rg.addView(val);
		inputDel.setView(rg);
		inputDel.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(val.isChecked())
				{
					AlertDialog.Builder val_d=new AlertDialog.Builder(LinkList_dr.this);
					val_d.setTitle("DELETE ").setMessage("ENTER A VALUE ");
					final EditText value=new EditText(LinkList_dr.this);
					value.setGravity(Gravity.CENTER);
					val_d.setView(value);
					val_d.setPositiveButton("OK",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							if(value.getText().toString().length()!=0)
							{
									del_val = value.getText().toString();
									if(search(del_val)==1)
									{
										
										btnLinkAdd.setEnabled(true);
										Toast.makeText(LinkList_dr.this,"POSITION "+x1, Toast.LENGTH_SHORT).show();
										str[x1]="-99";
										shift(x1);
										redraw();
									}
									else
										new linklist_b().execute("no");
							}
							else
								new linklist_b().execute("value");
						}
					});
					val_d.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							
							return;
						}
					});
					val_d.show();
				}
				else if(pos.isChecked())
				{
					AlertDialog.Builder pos_d=new AlertDialog.Builder(LinkList_dr.this);
					pos_d.setTitle("DELETE ").setMessage("ENTER POSITION ");
					final EditText position=new EditText(LinkList_dr.this);
					position.setGravity(Gravity.CENTER);
					pos_d.setView(position);;
					pos_d.setPositiveButton("OK",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							if(position.getText().toString().length()!=0)
							{
								try
								{
									del_pos = Integer.parseInt(position.getText().toString());
									if(del_pos>0 && del_pos<=count)
									{
										btnLinkAdd.setEnabled(true);
										str[del_pos]="-99";
										shift(del_pos);
										redraw();
									}
									else
										new linklist_b().execute("position");
								}
								catch(Exception e)
								{
									new linklist_b().execute("value");
								}
							}
							else
								new linklist_b().execute("value");
							}
					});
					pos_d.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							return;
						}
					});
					pos_d.show();
				}
				else
				{
					new linklist_b().execute("event");
				}
			}
		});
		inputDel.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				return;
			}
		});
		inputDel.show();
	}
	private void shift(int start)
	{
		int j;
		count--;
		for(j=start;j<=7;j++)
			str[j]=str[j+1];
	}
	private int search(String x)
	{
		int j;
		for(j=1;j<=6;j++)
			if(x.compareTo(str[j])==0)
			{
				x1=j;
				return 1;
			}
		return 0;
	}
	private void redraw()
	{
		node1.setVisibility(View.INVISIBLE);node2.setVisibility(View.INVISIBLE);
		node3.setVisibility(View.INVISIBLE);node4.setVisibility(View.INVISIBLE);
		node5.setVisibility(View.INVISIBLE);node6.setVisibility(View.INVISIBLE);
		right1.setVisibility(View.INVISIBLE);right2.setVisibility(View.INVISIBLE);
		right3.setVisibility(View.INVISIBLE);right4.setVisibility(View.INVISIBLE);
		right5.setVisibility(View.INVISIBLE);left1.setVisibility(View.INVISIBLE);
		left2.setVisibility(View.INVISIBLE);left3.setVisibility(View.INVISIBLE);
		left4.setVisibility(View.INVISIBLE);left5.setVisibility(View.INVISIBLE);
		if(str[1].compareTo("-99")!=0)
		{
			node1.setVisibility(View.VISIBLE);	
			node1.setText(str[1]);
			btnLinkDel.setEnabled(true);
		}
		if(str[2].compareTo("-99")!=0)
		{
			if(rev==0)
				right1.setVisibility(View.VISIBLE);
			else
			{
				right1.setVisibility(View.INVISIBLE);
				left5.bringToFront();
				left5.setVisibility(View.VISIBLE);
			}
			node2.setVisibility(View.VISIBLE);
			node2.setText(str[2]);
			
		}
		if(str[3].compareTo("-99")!=0)
		{
			if(rev==0)
				right2.setVisibility(View.VISIBLE);
			else
			{
				right2.setVisibility(View.INVISIBLE);
				left4.bringToFront();
				left4.setVisibility(View.VISIBLE);
			}
				
			node3.setVisibility(View.VISIBLE);
			node3.setText(str[3]);
		}
		if(str[4].compareTo("-99")!=0)
		{
			if(rev==0)
				right3.setVisibility(View.VISIBLE);
			else
			{
				right3.setVisibility(View.INVISIBLE);
				left3.bringToFront();
				left3.setVisibility(View.VISIBLE);
			}
			node4.setVisibility(View.VISIBLE);
			node4.setText(str[4]);
		}
		if(str[5].compareTo("-99")!=0)
		{
			if(rev==0)
				right4.setVisibility(View.VISIBLE);
			else
			{
				right4.setVisibility(View.INVISIBLE);
				left2.bringToFront();
				left2.setVisibility(View.VISIBLE);
			}
			node5.setVisibility(View.VISIBLE);
			node5.setText(str[5]);
		}
		if(str[6].compareTo("-99")!=0)
		{
			if(rev==0)
				right5.setVisibility(View.VISIBLE);
			else
			{
				right5.setVisibility(View.INVISIBLE);
				left1.bringToFront();
				left1.setVisibility(View.VISIBLE);
			}
			node6.setVisibility(View.VISIBLE);
			node6.setText(str[6]);
			btnLinkAdd.setEnabled(false);
		}			
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
					CopyReadAssets("LinkList_hi.pdf");
				}
				else if(en.isChecked())
				{
					CopyReadAssets("LinkList_en.pdf");
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
	public void CLEAR(View btnCLEAR)
	{
		Intent replyIntent =new Intent(this, LinkList_dr.class);		
		startActivityForResult(replyIntent, 0);
	}
	public void REVERSE(View btnRev)
	{
		rev++;
		if(rev==2)
			rev=0;
		redraw();
	}
	
	class linklist_b extends AsyncTask<String, Void ,String>
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
			if(result.compareTo("value")==0)
				Toast.makeText(LinkList_dr.this,"ERROR in Value !!", Toast.LENGTH_SHORT).show();
			else if(result.compareTo("no")==0)
				Toast.makeText(LinkList_dr.this,"Element not FOUND !!", Toast.LENGTH_LONG).show();
			else if(result.compareTo("event")==0)
				Toast.makeText(LinkList_dr.this,"CLICK EVENT ERROR !!", Toast.LENGTH_LONG).show();
			else if(result.compareTo("position")==0)
				Toast.makeText(LinkList_dr.this,"POSITION NOT VALID !!", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.link_list_dr, menu);
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
			
			case R.id.itemFifo:
				Intent replyIntent1 =new Intent(this, FifoQueue_dr.class);
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
