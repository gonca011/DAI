package Backend;

public class Pontos {
    private int pontosPorKm;
    private int pontosPorViagem;

    public Pontos(int pontosPorKm, int pontosPorViagem) {
        this.pontosPorKm = pontosPorKm;
        this.pontosPorViagem = pontosPorViagem;
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
        return "Pontos{" + "pontosPorKm=" + pontosPorKm + ", pontosPorViagem=" + pontosPorViagem + '}';
    }

}
