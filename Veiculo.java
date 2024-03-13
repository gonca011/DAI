package Backend;

public class Veiculo {
    private int id;
    private String localizacao;
    private float distancia;
    private int atraso;
    private Viagem viagem;

    public Veiculo(int id, float distancia, int atraso, Viagem viagem, String localizacao) {
        this.id = id;
        this.distancia = distancia;
        this.atraso = atraso;
        this.viagem = viagem;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public float getDistancia() {
        return distancia;
    }

    public int getAtraso() {
        return atraso;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistancia(float Distancia) {
        this.distancia = Distancia;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", distancia=" + distancia +
                ", atraso=" + atraso +
                ", viagem=" + viagem +
                ", localizacao=" + localizacao;
    }
}
