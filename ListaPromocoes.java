package Backend;
import java.util.ArrayList;
import java.util.List;  
import java.time.LocalDate;

public class ListaPromocoes {
    private List<Promocao> promocoes;

    public ListaPromocoes() {
        this.promocoes = new ArrayList<>();
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    public void adicionarPromocao(Promocao promocao){
        promocoes.add(promocao);
    }

    public void removerPromocao(Promocao promocao){
        promocoes.remove(promocao);
    }

    public int calcularNumPromocoes(){
        return promocoes.size();
    }

    public int calcularNumPromocoesAtivas(LocalDate dataAtual){ 
        int numPromocoesAtivas = 0;
        for(Promocao p : promocoes){
            if(p.estaAtiva(dataAtual)){
                numPromocoesAtivas++;
            }
        }
        return numPromocoesAtivas;
    }
    
    public int calcularNumPromocoesValidas(double km, int viagens, String origem, String destino, LocalDate dataAtual){
        int numPromocoesValidas = 0;
        for(Promocao p : promocoes){
            if(p.estaAtiva(dataAtual) && p.validacao(km, viagens, origem, destino)){
                numPromocoesValidas++;
            }
        }
        return numPromocoesValidas;
    }

    public String listarPromocoesAtivas(LocalDate dataAtual){
        String promocoesAtivas = "";
        for(Promocao p : promocoes){
            if(p.estaAtiva(dataAtual)){
                promocoesAtivas += p.toString() + "\n";
            }
        }
        return promocoesAtivas;
    }

    public String listarPromocoesValidas(double km, int viagens, String origem, String destino, LocalDate dataAtual){
        String promocoesValidas = "";
        for(Promocao p : promocoes){
            if(p.estaAtiva(dataAtual) && p.validacao(km, viagens, origem, destino)){
                promocoesValidas += p.toString() + "\n";
            }
        }
        return promocoesValidas;
    }

}
