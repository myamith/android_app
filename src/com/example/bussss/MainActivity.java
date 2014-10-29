package com.example.bussss;

//import com.example.busss.R;

//import com.example.light.App2Activity;
//import com.example.light.R;
//import com.example.light.MainActivity.connect_thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	
	EditText from_place,To_place,start_time;
	CheckBox chk_box;
	Button button;
	String from_s_place,to_s_place,Start_s_time,current_time;
	final Context context = this;
	PreparedStatement updateemp;
	SimpleDateFormat sdf;
	 int flag=0;Connection conn = null;
     Statement stmt = null;
     
     
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 from_place = (EditText)findViewById(R.id.editText1);
		 To_place = (EditText)findViewById(R.id.editText2);
		 chk_box=(CheckBox)findViewById(R.id.checkBox1);
		 start_time = (EditText)findViewById(R.id.editText3);
		 addListenerOnButton();
	}
public void addListenerOnButton() {

		

		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {

			//	server_IP = server_IP_addres.getText().toString();
			//	Thread myThread = new Thread(new connect_thread());
			//	myThread.start();
				from_s_place=from_place.getText().toString();
				to_s_place=To_place.getText().toString();
				Start_s_time=start_time.getText().toString();
				Log.v("Amith",from_place.getText().toString());
				System.out.println(from_place.getText().toString());
				Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		        sdf = new SimpleDateFormat("HH:mm:ss");
		    	current_time= sdf.format(cal.getTime());
		    	
		    	try {

					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@lnxdb-idev3-vm-005.cisco.com:1522:MIGIDEV", "SCMTOOLS",
							"scm#wft00l");

				} catch (SQLException e) {

					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;

				}

				if (conn!= null) {
					System.out.println("You made it, take control your database now!");
				flag=1;
				
				} else {
					System.out.println("Failed to make connection!");
				}

				
		//		Intent intent = new Intent(context, App2Activity.class);
          //      startActivity(intent); 
				
	
				try {
					  updateemp = conn.prepareStatement("insert into user_table values(?,?,?,?)");
					  updateemp.setString(1,from_s_place);
				      updateemp.setString(2,to_s_place);
				      updateemp.setString(3,Start_s_time);
				      updateemp.setString(4,current_time);
				      updateemp.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					  e.printStackTrace();
				}
				
				Intent intent = new Intent(context, DisplayMessageActivity.class);
				startActivity(intent);
				try{
				 stmt.close();
				 updateemp.close();
	                conn.close();
	  		        } catch (Exception e) {  }

				
				
				
	
}	});
	
}
		
		
		/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	} */
}
