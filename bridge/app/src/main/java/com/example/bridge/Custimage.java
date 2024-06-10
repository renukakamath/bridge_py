package com.example.bridge;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custimage extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	    SharedPreferences sh;


	private String[] fname;
	private String[] loandetails;
	private String[] noofdays;
	private String[] amount;
	private String[] monthlyamount;
	private String[] papers;

	 public Custimage(Activity context, String[] name, String[] ldetails, String[] npof , String[] amt,String[] mant, String[] paper) {
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_images, paper);
	        this.context = context;

		    this.fname = name;
		 	this.loandetails = ldetails;
		 this.noofdays = npof;

		 this.amount = amt;
		 this.monthlyamount = mant;
		 this.papers = paper;


	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	                 //override getView() method

	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.cust_images, null, true);
			//cust_list_view is xml file of layout created in step no.2

	        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
	        TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);


			t1.setText("name : "+fname[position]+"\nloandetails : "+loandetails[position]+"\nnoofdays:"+noofdays[position]+"\namount : "+amount[position]+"\nmonthlyamount:"+monthlyamount[position]);
//			t2.setText(caption[position]);
	        sh=PreferenceManager.getDefaultSharedPreferences(getContext());
	        
	       String pth = "http://"+sh.getString("ip", "")+"/"+papers[position];
	       pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();
	        
	        Log.d("-------------", pth);
	        Picasso.with(context)
	                .load(pth)
	                .placeholder(R.drawable.ic_launcher_background)
	                .error(R.drawable.ic_launcher_background).into(im);
	        
	        return  listViewItem;
	    }

		private TextView setText(String string) {
			// TODO Auto-generated method stub
			return null;
		}
}