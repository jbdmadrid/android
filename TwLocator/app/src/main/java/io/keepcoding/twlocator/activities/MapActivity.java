package io.keepcoding.twlocator.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.keepcoding.twlocator.R;
import io.keepcoding.twlocator.util.map.MapUtil;

public class MapActivity extends AppCompatActivity {

    MapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            map = mapFragment.getMap();
            if (map == null) {
                Toast.makeText(MapActivity.this, "Map died!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        MapUtil.centerMap(this.map, 40.446054, -3.693956, 12);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_center_map) {
            MapUtil.centerMap(this.map, 40.446054, -3.693956, 12);


            MarkerOptions marker = new MarkerOptions().position(new LatLng(40.446054, -3.693956)).title("Hello Maps ");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

            MarkerOptions marker2 = new MarkerOptions().position(new LatLng(40.446050, -3.693950)).title("Hello Maps dos");
           // marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.note));

            map.addMarker(marker);
            map.addMarker(marker2);

            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setZoomGesturesEnabled(false);
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);

            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
