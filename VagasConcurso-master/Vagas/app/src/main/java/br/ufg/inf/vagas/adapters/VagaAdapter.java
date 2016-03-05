package br.ufg.inf.vagas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.vagas.R;
import br.ufg.inf.vagas.models.Vaga;

/**
 * Created by Domingas on 14/06/2015.
 */



public class VagaAdapter extends RecyclerView.Adapter<VagaAdapter.ViewHolder> {
    private Context mContext;
    private List<Vaga> mList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;


    public VagaAdapter(Context c, List<Vaga> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_vaga, viewGroup, false);
        ViewHolder mvh = new ViewHolder(v);

  return mvh;
    }




    @Override
    public void onBindViewHolder(ViewHolder ViewHolder, int position) {
        ViewHolder.textViewTitulo.setText(mList.get(position).getTitulo());

        ViewHolder.textViewInfoTitulo.setText(mList.get(position).getInfoTitulo());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void addListItem(Vaga v, int position) {
        mList.add(v);
    }


    public void removeListItem(int position) {
        mList.remove(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitulo;
        public TextView textViewInfoTitulo;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitulo = (TextView) itemView.findViewById(R.id.titulo);
            textViewInfoTitulo = (TextView) itemView.findViewById(R.id.infoTitulo);

        }

    }
}
