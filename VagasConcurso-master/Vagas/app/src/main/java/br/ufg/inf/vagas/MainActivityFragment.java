package br.ufg.inf.vagas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.vagas.adapters.VagaAdapter;
import br.ufg.inf.vagas.models.Vaga;
import br.ufg.inf.vagas.web.FetchJson;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private android.support.v7.widget.RecyclerView mRecyclerView;
    private List<Vaga> mList = new ArrayList<>();
    VagaAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        if(!isNetworkAvailable()){
            showError(getResources().getString(R.string.warning_no_connection));
        }else{
            updateList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        Bundle params = new Bundle();
                        params.putString("Titulo", mList.get(position).getTitulo());
                        params.putString("infoEdital", mList.get(position).getInfoEdital());
                        params.putString("dataFim",mList.get(position).getDataFim());
                        params.putString("link",mList.get(position).getLink());
                        intent.putExtras(params);
                        startActivity(intent);
                    }
                })
        );


        return rootView;
    }


    public void showError(String error){
        Toast.makeText(getActivity(), error,Toast.LENGTH_LONG).show();
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void updateList(){
        FetchJson fetchJson = new FetchJson(getActivity(),mRecyclerView,mList);
        fetchJson.execute();
    }}

