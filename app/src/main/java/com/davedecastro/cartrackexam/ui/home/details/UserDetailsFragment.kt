package com.davedecastro.cartrackexam.ui.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.databinding.FragmentDetailsBinding
import com.davedecastro.cartrackexam.ui.main.MainActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserDetailsFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentDetailsBinding
    var user: User? = null

    private val mainActivity: MainActivity?
        get() = activity as MainActivity?

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.user = user

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_details_address) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mainActivity?.title = getString(R.string.user_detail)
        mainActivity?.enableBackButton = true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val location = LatLng(user!!.address.geo.latitude, user!!.address.geo.longitude)
        map.addMarker(
            MarkerOptions().position(location)
                .title(user!!.address.street + ", " + user!!.address.suite + ", " + user!!.address.city)
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}