package br.ufg.inf.vagas.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.vagas.models.Vaga;

/**
 * Created by Domingas on 14/06/2015.
 */
public class Utils {
    List<Vaga> mListAux = new ArrayList<>();
    //Funcao que extrai os dados do arquivo .json e transforma em uma lista de partidas que possam ser consumidas pelo programa java.
    public List<Vaga> getMatchDataFromJson(String JsonStr) throws JSONException {

        if (JsonStr == null) {
            return null;
        }
        Vaga vaga;
        final String OWM_RESULT = "list";
        try {
            JSONObject JsonObject = new JSONObject(JsonStr);


            JSONArray list =  JsonObject.getJSONArray(OWM_RESULT);


            for (int i = 0; i < list.length(); i++) {
                vaga = new Vaga();
                JSONObject jsonObjectVaga  = list.getJSONObject(i);
                vaga.setTitulo(jsonObjectVaga.getString("titulo"));
                vaga.setInfoTitulo(jsonObjectVaga.getString("infoTitulo"));
                vaga.setInfoEdital(jsonObjectVaga.getString("infoEdital"));
                vaga.setDataFim(jsonObjectVaga.getString("datafim"));
                vaga.setLink(jsonObjectVaga.getString("link"));
                mListAux.add(i, vaga);

            }

            return mListAux;


        } catch (Exception e) {
            Log.e(Utils.class.getSimpleName(), e.toString());
        }
        return null;
    }


}
