package com.project.datastructure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.project.datastructure.LinkList_dr.linklist_b;

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
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Tree_dr extends Activity 
{
	int arr[]={-99,-99,-99,-99,-99,-99,-99,-99};
	int count=1,ch,i,j;
	TextView node1;TextView node2;TextView node3;
	TextView node4;TextView node5;TextView node6;
	TextView node7;ImageView right3;ImageView left2;
	ImageView right5;ImageView left4;ImageView right7;
	ImageView left6;
	String str;
	Button btnDelete;Button btnAdd;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		btnDelete=(Button)findViewById(R.id.btnTreeDelete);
		btnDelete.setEnabled(false);
	}
	public void ADD(View btnAdd)
	{
		
		AlertDialog.Builder inputAdd=new AlertDialog.Builder(this);
		inputAdd.setTitle("ADD ").setMessage("Enter a number");
		final EditText value=new EditText(this);
		inputAdd.setView(value);
		inputAdd.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(value.getText().toString().length()!=0)
				{
					try
					{
						ch = Integer.parseInt(value.getText().toString());
						Draw_Add(ch);
					}
					catch(Exception e)
					{
						new tree_b().execute("value");
					}
				}
				else
					new tree_b().execute("value");
					
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
	public void Draw_Add(int x)
	{
		node1=(TextView)findViewById(R.id.TreeNode1);
		node2=(TextView)findViewById(R.id.TreeNode2);
		node3=(TextView)findViewById(R.id.TreeNode3);
		node4=(TextView)findViewById(R.id.TreeNode4);
		node5=(TextView)findViewById(R.id.TreeNode5);
		node6=(TextView)findViewById(R.id.TreeNode6);
		node7=(TextView)findViewById(R.id.TreeNode7);
		right3=(ImageView)findViewById(R.id.TreeStickRight3);
		left2=(ImageView)findViewById(R.id.TreeStickLeft2);
		right5=(ImageView)findViewById(R.id.TreeStickRight5);
		left4=(ImageView)findViewById(R.id.TreeStickLeft4);
		right7=(ImageView)findViewById(R.id.TreeStickRight7);
		left6=(ImageView)findViewById(R.id.TreeStickLeft6);
		if(count==1)
		{
			arr[count]=x;
			node1.setVisibility(View.VISIBLE);
			node1.setText(arr[count]+"");
			count=99;
			btnDelete.setEnabled(true);
		}
		else
		{
			for(i=1;arr[i]!=-99;i= arr[i]>x ? 2*i : 2*i+1);
			if(i==2)
			{
				left2.setVisibility(View.VISIBLE);
				node2.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node2.setText(arr[count]+"");
			}
			else if(i==3)
			{
				right3.setVisibility(View.VISIBLE);
				node3.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node3.setText(arr[count]+"");
			}
			else if(i==4)
			{
				left4.setVisibility(View.VISIBLE);
				node4.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node4.setText(arr[count]+"");
			}
			else if(i==5)
			{
				right5.setVisibility(View.VISIBLE);
				node5.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node5.setText(arr[count]+"");
			}
			else if(i==6)
			{
				left6.setVisibility(View.VISIBLE);
				node6.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node6.setText(arr[count]+"");
			}
			else if(i==7)
			{
				right7.setVisibility(View.VISIBLE);
				node7.setVisibility(View.VISIBLE);
				count=i;
				arr[count]=x;
				node7.setText(arr[count]+"");
			}
		}
				
	}
	private void redraw()
	{
		node1.setVisibility(View.INVISIBLE);node2.setVisibility(View.INVISIBLE);
		node3.setVisibility(View.INVISIBLE);node4.setVisibility(View.INVISIBLE);
		node5.setVisibility(View.INVISIBLE);node6.setVisibility(View.INVISIBLE);
		node7.setVisibility(View.INVISIBLE);left2.setVisibility(View.INVISIBLE);
		left4.setVisibility(View.INVISIBLE);left6.setVisibility(View.INVISIBLE);
		right3.setVisibility(View.INVISIBLE);right5.setVisibility(View.INVISIBLE);
		right7.setVisibility(View.INVISIBLE);
		if(arr[1]!=-99)
		{
			node1.setVisibility(View.VISIBLE);
			node1.setText(arr[1]+"");
		}
		if(arr[2]!=-99)
		{
			left2.setVisibility(View.VISIBLE);
			node2.setVisibility(View.VISIBLE);
			node2.setText(arr[2]+"");
		}
		if(arr[3]!=-99)
		{
			right3.setVisibility(View.VISIBLE);
			node3.setVisibility(View.VISIBLE);
			node3.setText(arr[3]+"");
		}
		if(arr[4]!=-99)
		{
			left4.setVisibility(View.VISIBLE);
			node4.setVisibility(View.VISIBLE);
			node4.setText(arr[4]+"");
		}
		if(arr[5]!=-99)
		{
			right5.setVisibility(View.VISIBLE);
			node5.setVisibility(View.VISIBLE);
			node5.setText(arr[5]+"");
		}
		if(arr[6]!=-99)
		{
			left6.setVisibility(View.VISIBLE);
			node6.setVisibility(View.VISIBLE);
			node6.setText(arr[6]+"");
		}
		if(arr[7]!=-99)
		{
			right7.setVisibility(View.VISIBLE);
			node7.setVisibility(View.VISIBLE);
			node7.setText(arr[7]+"");
		}
	}
	private void Draw_Delete(int x)
	{
		if(arr[1]==x)
		{
			 if(arr[3]!=-99 && arr[6]!=-99)
			 {
				 arr[1]=arr[6];
				 arr[6]=-99;
			 }
			 else if(arr[3]!=-99 && arr[6]==-99 && arr[7]!=-99)
			 {
				 Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
				 arr[1]=arr[3];
				 arr[3]=arr[7];
				 arr[7]=-99;
			 }
			 else if(arr[3]!=-99 && arr[6]==-99)
			 {
				 arr[1]=arr[3];
				 arr[3]=-99;
			 }
			 else if(arr[2]!=-99 && arr[3]==-99 && arr[4]!=-99 && arr[5]!=-99)
			 {
				 
				 arr[1]=arr[2];
				 arr[2]=arr[4];
				 arr[3]=arr[5];
				 arr[4]=-99;arr[5]=-99;
			 }
			 else if(arr[2]!=-99 && arr[3]==-99 && arr[4]!=-99)
			 {
				 arr[1]=arr[2];
				 arr[2]=arr[4];
				 arr[4]=-99;
			 }
			 else if(arr[2]!=-99 && arr[3]==-99)
			 {
				 arr[1]=arr[2];
				 arr[2]=-99;
			 }
			 else
				 arr[1]=-99;
			 redraw();
		}
		else if(arr[2]==x)
		{
			if(arr[5]!=-99)
			{
				arr[2]=arr[5];
				arr[5]=-99;
			}
			else if(arr[4]!=-99)
			{
				arr[2]=arr[4];
				arr[4]=-99;
			}
			else
				arr[2]=-99;
			redraw();
		}
		else if(arr[3]==x)
		{
			if(arr[7]!=-99)
			{
				arr[3]=arr[7];
				arr[7]=-99;
			}
			else if(arr[6]!=-99)
			{
				arr[3]=arr[6];
				arr[6]=-99;
			}
			else
				arr[3]=-99;
			redraw();
		}
		else if(arr[4]==x)
		{
			arr[4]=-99;
			redraw();
		}
		else if(arr[5]==x)
		{
			arr[5]=-99;
			redraw();
		}
		else if(arr[6]==x)
		{
			arr[6]=-99;
			redraw();
		}
		else if(arr[7]==x)
		{
			arr[7]=-99;
			redraw();
		}
	}
	public void DELETE(View btnDel)
	{
		AlertDialog.Builder inputDel=new AlertDialog.Builder(this);
		inputDel.setTitle("DELETE ").setMessage("Enter a number");
		final EditText value=new EditText(this);
		inputDel.setView(value);
		inputDel.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(value.getText().toString().length()!=0)
				{
					try
					{
						ch = Integer.parseInt(value.getText().toString());
						if(search(ch)==1)
							Draw_Delete(ch);
						else
							new tree_b().execute("no");
					}
					catch(Exception e)
					{
						new tree_b().execute("value");
					}
				}
				else
					new tree_b().execute("value");
					
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
	private int search(int x)
	{
		int j;
		for(j=0;j<8;j++)
			if(arr[j]==x)
				return 1;
		return 0;
	}
	public void CLEAR(View btnClear)
	{
		Intent replyIntent =new Intent(this, Tree_dr.class);		
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
					CopyReadAssets("Tree_hi.pdf");
				}
				else if(en.isChecked())
				{
					CopyReadAssets("Tree_en.pdf");
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
	public void DISPLAY(View btnDisplay)
	{
		
		AlertDialog.Builder inputDis=new AlertDialog.Builder(this);
		inputDis.setTitle("DISPLAY ").setMessage("Enter an order");
		final RadioGroup rg=new RadioGroup(this);
		final RadioButton post=new RadioButton(this);
		post.setText("POSTORDER");
		final RadioButton pre=new RadioButton(this);
		pre.setText("PREORDER");
		final RadioButton in=new RadioButton(this);
		in.setText("INORDER");
		rg.addView(post);rg.addView(in);rg.addView(pre);
		inputDis.setView(rg);
		inputDis.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				if(post.isChecked())
				{
					AlertDialog.Builder post_d=new AlertDialog.Builder(Tree_dr.this);
					post_d.setTitle("POSTORDER ").setMessage("PATTERN ");
					TextView value=new TextView(Tree_dr.this);
					value.setGravity(Gravity.CENTER);
					post_d.setView(value);
					value.setText(postorder());
					post_d.setPositiveButton("OK",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{return;
						}
					});
					post_d.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							return;
						}
					});
					post_d.show();
					
				}
				else if(in.isChecked())
				{
					AlertDialog.Builder post_d=new AlertDialog.Builder(Tree_dr.this);
					post_d.setTitle("INORDER ").setMessage("PATTERN ");
					TextView value=new TextView(Tree_dr.this);
					value.setGravity(Gravity.CENTER);
					post_d.setView(value);
					value.setText(inorder());
					post_d.setPositiveButton("OK",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{return;
						}
					});
					post_d.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							return;
						}
					});
					post_d.show();
				}
				else if(pre.isChecked())
				{
					AlertDialog.Builder post_d=new AlertDialog.Builder(Tree_dr.this);
					post_d.setTitle("PREORDER ").setMessage("PATTERN ");
					TextView value=new TextView(Tree_dr.this);
					value.setGravity(Gravity.CENTER);
					post_d.setView(value);
					value.setText(preorder());
					post_d.setPositiveButton("OK",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{return;
						}
					});
					post_d.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							return;
						}
					});
					post_d.show();
				}
				else
					new tree_b().execute("event");
					
			}
		});
		inputDis.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				return;
			}
		});
		inputDis.show();
	}
	private String postorder()
	{
		int index[]={0,4,5,2,6,7,3,1}; 
		int k;String result=" ";
		for(k=1;k<=7;k++)
			if(arr[index[k]]!=-99)
				result+=arr[index[k]]+",  ";
		return result;
		
	}
	private String inorder()
	{
		int index[]={0,4,2,5,1,6,3,7};
		int k;String result=" ";
		for(k=1;k<=7;k++)
			if(arr[index[k]]!=-99)
				result+=arr[index[k]]+",  ";
		return result;
	}
	private String preorder()
	{
		int index[]={0,1,2,4,5,3,6,7};
		int k;String result=" ";
		for(k=1;k<=7;k++)
			if(arr[index[k]]!=-99)
				result+=arr[index[k]]+",  ";
		return result;
	}
	class tree_b extends AsyncTask<String, Void ,String>
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
				Toast.makeText(Tree_dr.this,"ERROR in Value(Int expected)!!", Toast.LENGTH_LONG).show();
			else if(result.compareTo("no")==0)
				Toast.makeText(Tree_dr.this,"Element not FOUND!!", Toast.LENGTH_LONG).show();
			else if(result.compareTo("event")==0)
				Toast.makeText(Tree_dr.this,"CLICK EVENT ERROR !!", Toast.LENGTH_LONG).show();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tree_dr, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
    {
		switch (item.getItemId()) 
		{
			case R.id.itemLink:
				Intent replyIntent =new Intent(this, LinkList_dr.class);
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
