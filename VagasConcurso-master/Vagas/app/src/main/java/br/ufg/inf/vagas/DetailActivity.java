package br.ufg.inf.vagas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvInfoEdital;
    private TextView tvDataFim;
    private TextView tvLink;
    private Toolbar mToolbar;
    String titulo = "";
    String infoEdital = "";
    String dataFim="";
    String link="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mToolbar = (Toolbar) findViewById(R.id.tbMainDetail);
        mToolbar.setTitle("Concurso");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();

        Bundle params = intent.getExtras();

        if (params != null) {
            titulo = params.getString("Titulo");
            infoEdital = params.getString("infoEdital");
            dataFim = params.getString("dataFim");
            link = params.getString("link");

        }
        tvTitulo = (TextView) findViewById(R.id.tvTituloDetail);
        tvInfoEdital = (TextView) findViewById(R.id.tvInfoEditalDetail);
        tvDataFim= (TextView) findViewById(R.id.tvDataFimDetail);
        tvLink = (TextView) findViewById(R.id.tvLinkDetail);
        tvTitulo.setText(titulo);
        tvInfoEdital.setText(infoEdital);
        tvDataFim.setText(dataFim);
        tvLink.setText(link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();

            return true;
        }

        return true;
    }
}
