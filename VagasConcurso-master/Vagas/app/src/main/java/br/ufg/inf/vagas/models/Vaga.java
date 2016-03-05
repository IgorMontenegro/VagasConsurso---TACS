package br.ufg.inf.vagas.models;

/**
 * Created by Domingas on 14/06/2015.
 */
public class Vaga {
    public String titulo;
    public String infoTitulo;
    public String infoEdital;
    public String dataFim;
    public String link;

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInfoTitulo() {
        return infoTitulo;
    }

    public void setInfoTitulo(String infoTitulo) {
        this.infoTitulo = infoTitulo;
    }

    public String getInfoEdital() {
        return infoEdital;
    }

    public void setInfoEdital(String infoEdital) {
        this.infoEdital = infoEdital;
    }
}
