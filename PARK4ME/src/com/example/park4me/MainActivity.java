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
				{"Botafogo", "Copacabana/Leme", "Flamengo/Laranjeiras", "Ipanema", "Leblon", "Região Central", "Sair"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , bairros);
		
		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Intent intent;
		
		switch(position){
			case 0:
				//botafogo
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.954339);
				intent.putExtra("lng", -43.1909142);
				startActivity(intent);
				break;
			case 1:
				//copacabana/leme
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.9732708);
				intent.putExtra("lng", -43.1857553);
				startActivity(intent);
				break;
			case 2:
				//flamengo
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.9356357);
				intent.putExtra("lng", -43.1813744);
				startActivity(intent);
				break;
			case 3:
				//ipanema
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.9849889);
				intent.putExtra("lng", -43.2044715);
				startActivity(intent);
				break;
			case 4:
				//leblon
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.9840451);
				intent.putExtra("lng", -43.2247083);
				startActivity(intent);
				break;
			case 5:
				//regiao central
				intent = new Intent(this, map.class);
				intent.putExtra("lat", -22.9053526);
				intent.putExtra("lng", -43.1781185);
				startActivity(intent);
				break;
//			case 6:
//				
//				intent = new Intent(this, map.class);
//				intent.putExtra("lat", -22.9873758);
//				intent.putExtra("lng", -43.1237373);
//				startActivity(intent);
//				break;
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
