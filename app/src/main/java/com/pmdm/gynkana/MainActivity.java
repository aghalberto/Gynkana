package com.pmdm.gynkana;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.AdvancedMarker;
import com.google.android.gms.maps.model.AdvancedMarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;
import android.Manifest.permission;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements
            ActivityCompat.OnRequestPermissionsResultCallback,
            GoogleMap.OnMyLocationButtonClickListener,
            GoogleMap.OnMyLocationClickListener,
            OnMapReadyCallback,
            GoogleMap.OnMarkerClickListener,
            GoogleMap.OnInfoWindowClickListener {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in {@link
     * #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean permissionDenied = false;

    /**
     * Declaración de los marcadores
     * Aqualand: 36.6213635,-4.5108872
     * Librería Pérgamo: 36.6242558,-4.5003692
     * Centro Cultural Picasso: 36.628526,-4.5007662,1744
     * CocodrilePark: 36.6279124,-4.5036038,1744
     * Palacio de Congresos: 36.6279124,-4.5036038,1744
     * Palacio San Miguel: 36.629019,-4.5024521,2467
     * Molino de Inca: 36.6281957,-4.5097368,1233
     * Playa de la Carihuela: 36.6140352,-4.5117066,2934
     * Casa Kiki: 36.6141112,-4.5442451,13958
     * Parque de la Batería: 36.6138898,-4.5077588,1037
     */

    private LatLng AQUALAND = new LatLng(36.6213635, -4.5108872);
    private LatLng PERGAMO = new LatLng(36.6242558 , -4.5003692);
    private LatLng PICASSO =  new LatLng(36.628526,-4.5007662);
    private LatLng COCODRILE = new LatLng(36.6279124,-4.5036038);
    private LatLng CONGRESOS = new LatLng(36.6279124,-4.5036038);
    private LatLng SANMIGUEL = new LatLng(36.629019,-4.5024521);
    private LatLng INCA = new LatLng(36.629019,-4.5024521);
    private LatLng CARIHUELA = new LatLng(36.6140352,-4.5117066);
    private LatLng KIKI = new LatLng(36.6141112,-4.5442451);
    private LatLng BATERIA = new LatLng(36.6138898,-4.5077588);

    /**
     * En lugar de usar localizaciones vamos a crear marcadores
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    private Marker markerAqualand;
    private Marker markerPergamo;
    private Marker markerPicasso;
    private Marker markerCocodrile;
    private Marker markerCongreso;
    private Marker markerSanmiguel;
    private Marker markerInca;
    private Marker markerCarihuela;
    private Marker markerKiki;
    private Marker markerBateria;
    //Fragmentos
    //private SupportFragmentManager supportFragmentManager;
    private SupportMapFragment mapFragment;
    //EditText password = (EditText) findViewById(R.id.password);
    private Switch switch_position;




    /**
     * onCreate. Se ejecuta al inciiar la app.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //SupportMapFragment
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Listener del switch
        Switch switch_position = (Switch)  findViewById(R.id.switch_position);
        switch_position.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) Toast.makeText(mapFragment.getActivity(), R.string.activado, Toast.LENGTH_SHORT).show();
                else Toast.makeText(mapFragment.getActivity(), R.string.desactivado, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        markerAqualand = crearMarcador(googleMap, AQUALAND, R.string.aqualand, R.string.aqualand_snippet, R.drawable.gema, "AQUALAND");
        markerBateria = crearMarcador(googleMap, BATERIA, R.string.bateria, R.string.bateria_snippet, "BATERIA");
        markerCarihuela = crearMarcador(googleMap, CARIHUELA, R.string.carihuela, R.string.carihuela_snippet, "CARIHUELA");
        markerCocodrile = crearMarcador(googleMap, COCODRILE, R.string.cocodrile, R.string.cocodrile_snippet, "COCODRILE");
        markerCongreso = crearMarcador(googleMap, CONGRESOS, R.string.congresos, R.string.congresos_snippet, "CONGRESOS");
        markerInca = crearMarcador(googleMap, INCA, R.string.inca, R.string.inca_snippet, "INCA");
        markerKiki  = crearMarcador(googleMap, KIKI, R.string.kiki, R.string.kiki_snippet, "KIKI");
        markerPergamo = crearMarcador(googleMap, PERGAMO, R.string.pergamo, R.string.pergamo_snippet, "PERGAMO");
        markerPicasso = crearMarcador(googleMap, PICASSO, R.string.picasso, R.string.picasso_snippet, "PICASSO");
        markerSanmiguel = crearMarcador(googleMap, SANMIGUEL, R.string.sanmiguel, R.string.sanmiguel_snippet, "SANMIGUEL");

        //Estilo personalizado
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        //Implementación para click en el marcador
        googleMap.setOnMarkerClickListener(this);
        //Escuchador click
        googleMap.setOnInfoWindowClickListener(this);

        //Botón "Mi localización"
        enableMyLocation(googleMap, true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);




    } //onMapReady

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    @SuppressLint("MissingPermission")
    private void enableMyLocation(GoogleMap map, boolean enable) {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(enable);
            return;
        }

        // 2. Otherwise, request location permissions from the user.
        PermissionUtils.requestLocationPermissions(this, LOCATION_PERMISSION_REQUEST_CODE, true);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        //Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)               .show();
    }
    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)                .show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    /**
     * Crea un marcador con la imagen por defecto
     * @param map Objeto GoogleMap
     * @param pos Posición
     * @param titleID Título
     * @param snippetID Instrucciones
     * @param tag El Recurso con la imagen para la vista
     * @return
     */
    public Marker crearMarcador(GoogleMap map, LatLng pos, int titleID, int snippetID, String tag){
        Marker marker = map.addMarker(new AdvancedMarkerOptions()
                        .position(pos)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gema))
                        .title(getResources().getString(titleID))
                        .snippet(getResources().getString(snippetID))
        );
        marker.setTag(tag);
        return marker;
    }

    /**
     * Crea un marcador, requiere imagen
     * @param map Objeto GoogleMap
     * @param pos Posición
     * @param titleID Título
     * @param snippetID Instrucciones
     * @param recursoImagen la imagen que se va a pasar
     * @param tag Tag
     * @return
     */
    public Marker crearMarcador(GoogleMap map, LatLng pos, int titleID, int snippetID, int recursoImagen, String tag){
        Marker marker = map.addMarker(new AdvancedMarkerOptions()
                .position(pos)
                .title(getResources().getString(titleID))
                .snippet(getResources().getString(snippetID))
                .icon(BitmapDescriptorFactory.fromResource(recursoImagen))

        );
        assert marker != null;
        marker.setTag(tag);
        return marker;
    }

    /**
     * Cuando el usuario hace click en un marcador
     * @param marker el Marcador
     * @return
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        //Mostramos la ventana de información
        marker.showInfoWindow();
        return false;
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
    }

    /**
     * Al hacer click en la ventana de info
     * @param marker El Marcador pulsado
     */
    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        String tag = (String) marker.getTag();

        PasswordFragment passwordFragment = new PasswordFragment();
        passwordFragment.setTagMarker(tag);
        passwordFragment.show(getSupportFragmentManager(), tag);

    }






}