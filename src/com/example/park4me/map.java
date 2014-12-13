package com.example.park4me;


import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements LocationListener,OnMarkerClickListener {
	private GoogleMap mMap;
	private SupportMapFragment mapFrag;
	private LocationManager locationManager;
	private boolean allowNetwork;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
			
		GoogleMapOptions options = new GoogleMapOptions();
		options.zOrderOnTop(true);
		
		mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mMap = mapFrag.getMap();
		mMap.setTrafficEnabled(true);
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setMyLocationEnabled(true);
		configMap(); //quero chamar o configmap pra posicionamento inicial	
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		allowNetwork = true;
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//			Intent it = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			startActivity(it);
			showGPSDisabledAlertToUser();
		}
		else{
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//			Toast.makeText(map.this, "Ativando GPS..", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
		
	}


	

	

	public  void configMap(){//	configmap deveria receber latlng previamente 
		//Coloca os markers no mapa
		adicionaMarkersMapa();
				
		// getIntent() is a method from the started activity
		// gets the previously created intent
		Intent intent = getIntent();
		double defaultLat;
		defaultLat = -22.9112728;
		double defaultLng;
		defaultLng = -43.4484478;
		double first = intent.getDoubleExtra("lat", defaultLat); // will return "FirstKeyValue"
		double second= intent.getDoubleExtra("lng", defaultLng); // will return "SecondKeyValue"
		
		//config camera para visualizar região selecionada
		LatLng latlng = new LatLng(first, second);
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(15).bearing(0).tilt(0).build();
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
				.title(estacionamento.getNome()));}
		
		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
				
			@Override
			public boolean onMarkerClick(Marker marker) {
//					// TODO Auto-generated method stub
					double latitude=0;
					double longitude=0;
					LatLng pos = new LatLng(latitude, longitude);
					pos = (marker.getPosition());
					
//					LatLng pos0 = new LatLng(latitude, longitude);
					Location findme = mMap.getMyLocation();
//                    latitude = findme.getLatitude();
//                    longitude = findme.getLongitude();
//                    LatLng latLng = new LatLng(latitude, longitude);
//                    Log.d(tag, "|"+latLng);									
//					Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("geo:"+pos.latitude+','+pos.longitude)); 
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
							Uri.parse("http://maps.google.com/maps?"+"saddr="+findme.getLatitude()
							+","+findme.getLongitude()+"&daddr="+pos.latitude+','+pos.longitude));
					startActivity(intent);
//					Uri.parse("http://maps.google.com/maps?" 
//						    		+ "saddr=20.344,34.34&daddr=20.5666,45.345"
						    		
						
					
					
					
//					final String url = String.format(Locale.ENGLISH, "geo:%f,%f", marker.getPosition());
////				final String uri = String.format(Locale.ENGLISH, "geo:%f,%f", );
//					final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//					startActivity(intent);
					
//						String url = "waze://?ll=<lat>,<lon>&navigate=yes";
//					    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
//					   startActivity( intent );
//					
					// TODO Auto-generated method stub
//					string uri = String.format(Locale.ENGLISH, "geo:%f,%f", LatLng);
//					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					
//					String uri;
//					Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//					intent.parseUri(uri, flags)
//					Uri.parse("http://maps.google.com/maps?"));
////						    		+ "saddr=20.344,34.34&daddr=20.5666,45.345"
//						    		));
//						startActivity(intent);
					
					return false;
				}
			});	
				
		}
	
	
	public void configLocation(LatLng latLng){
//		CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15).bearing(0).tilt(0).build();
//		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
//		mMap.animateCamera(update);
		MyLocation myLocation = new MyLocation();
		mMap.setLocationSource(myLocation);
		myLocation.setLocation(latLng);
	}
	
	public class MyLocation implements LocationSource{
		private OnLocationChangedListener listener;
		
		@Override
		public void activate(OnLocationChangedListener listener) {
			this.listener = listener;
			Log.i("Script", "activate()");
		}

		@Override
		public void deactivate() {
			Log.i("Script", "deactivate()");
		}
		
		
		public void setLocation(LatLng latLng){
			Location location = new Location(LocationManager.GPS_PROVIDER);
			location.setLatitude(latLng.latitude);
			location.setLongitude(latLng.longitude);
			
			if(listener != null){
				listener.onLocationChanged(location);
			}
		}
	}



	@Override
	public void onLocationChanged(Location location) {
		if(location.getProvider().equals(LocationManager.GPS_PROVIDER)){
			allowNetwork = false;
		}
		
		if(allowNetwork || location.getProvider().equals(LocationManager.GPS_PROVIDER)){
			configLocation(new LatLng(location.getLatitude(), location.getLongitude()));
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
	
	
	private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("O GPS está desativado em seu dispositivo. Gostaria de ativá-lo?")
        .setCancelable(false)
        .setPositiveButton("Ativar GPS", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
                Toast.makeText(map.this, "GPS não ativado!", Toast.LENGTH_SHORT).show();
                ;
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}