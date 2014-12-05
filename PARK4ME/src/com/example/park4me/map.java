package com.example.park4me;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class map extends FragmentActivity {
	private GoogleMap mMap;
	private SupportMapFragment mapFrag;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
//		marcador loubake
//		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//		mMap.addMarker(new MarkerOptions()
//        .position(new LatLng(10, 10))
//        .title("Hello world"))
//        ;
		
		GoogleMapOptions options = new GoogleMapOptions();
		options.zOrderOnTop(true);
		
		mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		
		mMap = mapFrag.getMap();
//quero chamar o configmap pra posicionamento inicial		
		configMap();
	}

	@Override
	public void onResume(){
		super.onResume();
		
		/*new Thread(){
			public void run(){
				while(mapFrag.getMap() == null){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				runOnUiThread(new Runnable(){
					public void run(){
						configMap();
					}
				});
			}
		}.start();*/
	}	
	
//	configmap deveria receber latlng previamente
	public  void configMap(){
		mMap = mapFrag.getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
//  recebendo latlng previamento essa linha abaixo sai
		LatLng latlng = new LatLng(-23.564224, -46.653156);
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(18).bearing(0).tilt(90).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
		//map.moveCamera(update);
		mMap.animateCamera(update, 3000, new CancelableCallback(){
			@Override
			public void onCancel() {
				Log.i("Script", "CancelableCallback.onCancel()");
			}

			@Override
			public void onFinish() {
				Log.i("Script", "CancelableCallback.onFinish()");
			}
		});
	}
	
	
	
	
//	public void  posicao (){
//		mMap = mapFrag.getmap();
//		LatLng latlng = new LatLng(-22.9873758, -43.1237373);
//		CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(10).build();
//		CameraUpdate updade = new CameraUpdateFactory.newCameraPosition
//		;
//		
//	};
	
	public void voltar(View view){
		finish();
	}
}