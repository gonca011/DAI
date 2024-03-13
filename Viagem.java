package Backend;

import java.time.LocalDate;

public class Viagem {
    private double nKm;
    private LocalDate data;
    private double preco;
    private String paragem;
    private String origem;
    private String destino;
    private int pontosPorKm;
    private int pontosPorViagem;
    
    public Viagem(double nKm, LocalDate data, String origem, String destino, double preco, String paragem, int pontosPorKm, int pontosPorViagem) {
        this.nKm = nKm;
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.paragem = paragem;
        this.pontosPorKm = pontosPorKm;
        this.pontosPorViagem = pontosPorViagem;
    }

    public double getnKm() {
        return nKm;
    }

    public void setPreco (float preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getParagem() {
        return paragem;
    }

    public void setParagem(String paragem) {
        this.paragem = paragem;
    }

    public void setnKm(double nKm) {
        this.nKm = nKm;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

    public int getPontosPorKm() {
        return pontosPorKm;
    }

    public void setPontosPorKm(int pontosPorKm) {
        this.pontosPorKm = pontosPorKm;
    }

    public int getPontosPorViagem() {
        return pontosPorViagem;
    }

    public void setPontosPorViagem(int pontosPorViagem) {
        this.pontosPorViagem = pontosPorViagem;
    }

    @Override
    public String toString() {
        return "Viagem{" + "nKm=" + nKm + ", data=" + data + ", origem=" + origem + ", destino=" + destino + ", paragens=" + paragem + ", preco=" + preco;
    }

    
}
