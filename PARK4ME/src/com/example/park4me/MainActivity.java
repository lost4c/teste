package com.example.park4me;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] bairros = new String[]
				{"Botafogo", "Copacabana", "Flamengo", "Ipanema", "Leblon", "Laranjeiras", "Região Central", "Sair"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , bairros);
		
		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Intent intent;
		
		switch(position){
			case 0:
				intent = new Intent(this, map.class);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(this, map.class);
				startActivity(intent);
				break;
			case 2:
				break;
			case 3:
			break;
			case 4:
			break;
			case 5:
			break;
			case 6:
			break;
			default:
				finish();
		}
	}
}
//public class MainActivity extends Activity {  
//       
//       @Override  
//       protected void onCreate(Bundle savedInstanceState) {  
//           super.onCreate(savedInstanceState);  
//            setContentView(R.layout.activity_main);  
//           
//       }  



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
