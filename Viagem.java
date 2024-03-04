package Backend;

import java.util.Date;

public class Viagem {
    private double nKm;
    private Date data;
    private String origem;
    private String destino;
    
    public Viagem(double nKm, Date data, String origem, String destino) {
        this.nKm = nKm;
        this.data = data;
        this.origem = origem;
        this.destino = destino;
    }

    public double getnKm() {
        return nKm;
    }

    public void setnKm(double nKm) {
        this.nKm = nKm;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Viagem{" + "nKm=" + nKm + ", data=" + data + ", origem=" + origem + ", destino=" + destino + '}';
    }

    
}
