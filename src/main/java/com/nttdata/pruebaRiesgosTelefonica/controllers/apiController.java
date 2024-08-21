package com.nttdata.pruebaRiesgosTelefonica.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping ("/api/")
public class apiController {

    final String url = "https://mindicador.cl/api";

    @GetMapping("{moneda}/{fecha}")
    public String apiCall(@PathVariable("moneda") String moneda, @PathVariable("fecha") String fecha) {

        String salida = "";

        try {

            URL urlObject = new URL(url+"/"+moneda+"/"+fecha);
            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();

            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                salida = salida + inputLine;
            }

            in.close();

            con.disconnect();

            System.out.println("Response Code: " + status);
            System.out.println(salida);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

        return salida;
    }

}
