package servlets;

import  com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LatLngServlet")
public class LatLngServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullAddress = (String) request.getParameter("fullAddress");
        if (fullAddress == null || fullAddress.length() == 0) {
            return;
        }

        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
                .setAddress(fullAddress)
                .setLanguage("en")
                .getGeocoderRequest();

        try {

            GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
            if (geocoderResponse.getStatus() == GeocoderStatus.OK) {
                LatLng latLng = geocoderResponse.getResults().get(0).getGeometry().getLocation();
                request.setAttribute("latitude", latLng.getLat().toString());
                request.setAttribute("longitude", latLng.getLng().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
