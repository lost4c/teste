package com.example.park4me;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
import com.google.android.gms.maps.model.MarkerOptions;

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
		mMap.setTrafficEnabled(true);
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
		
//carregando latlng
		// getIntent() is a method from the started activity
		Intent intent = getIntent(); // gets the previously created intent
		double defaultLat;
		defaultLat = -22.9112728;
		double defaultLng;
		defaultLng = -43.4484478;
		double first = intent.getDoubleExtra("lat", defaultLat); // will return "FirstKeyValue"
		double second= intent.getDoubleExtra("lng", defaultLng); // will return "SecondKeyValue"
		
//configuraçao camera do maps
		LatLng latlng = new LatLng(first, second);
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(15).bearing(0).tilt(0).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
		//Coloca os markers no mapa
		adicionaMarkersMapa();
		
		//map.moveCamera(update);
		mMap.animateCamera(update, 2000, new CancelableCallback(){
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
	
	/*
	 * Loubake
	 */
	private List<Estacionamento> getEstacionamentos() {
		List<Estacionamento> estacionamentos = new ArrayList<Estacionamento>();
		estacionamentos.add(new Estacionamento("Estacionamento 1", -22.970298, -43.188207));
		estacionamentos.add(new Estacionamento("Estacionamento 2", -22.969725, -43.187236));
		estacionamentos.add(new Estacionamento("Estacionamento 3", -22.968850, -43.182048));
		return estacionamentos;
		
		/*
		 * Aqui vai vou recuperar a lista de estacionamentos da Intent
		 * Provavelmente, este método vai morrer, já que é possível pegar a lista da Intent em qualquer outro lugar
		 */
	}
	
	private void adicionaMarkersMapa() {
		List<Estacionamento> estacionamentos = getEstacionamentos();
		for (Estacionamento estacionamento : estacionamentos) {
			mMap.addMarker(new MarkerOptions()
				.position(new LatLng(estacionamento.getLatitude(), estacionamento.getLongitude()))
				.title(estacionamento.getNome()));
		}
	}
}