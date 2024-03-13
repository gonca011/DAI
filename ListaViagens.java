package Backend;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ListaViagens {
    private List<Viagem> viagens;

    public ListaViagens() {
        this.viagens = new ArrayList<>();
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public void adicionarViagem(Viagem viagem){
        viagens.add(viagem);
    }

    public void removerViagem(Viagem viagem){
        viagens.remove(viagem);
    }
    
    public int calcularNumViagens(){
        return viagens.size();
    }  
    
    public double calcularNumKm(){
        double numKm = 0;
        for(Viagem v : viagens){
            numKm += v.getnKm();
        }
        return numKm;
    }

    public double calcularKmPercurso(String origem, String destino){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public int calcularNumViagensPercurso(String origem, String destino){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numViagens++;
            }
        }
        return numViagens;
    }


    public double kmEntreDatas(LocalDate dataInicio, LocalDate dataFim){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getData().isAfter(dataInicio) && v.getData().isBefore(dataFim)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public double kmEntreDatasPercurso(LocalDate dataInicio, LocalDate dataFim, String origem, String destino){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getData().isAfter(dataInicio) && v.getData().isBefore(dataFim) && v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public int viagensRealizadasEntreDatas(LocalDate dataInicio, LocalDate dataFim){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getData().isAfter(dataInicio) && v.getData().isBefore(dataFim)){
                numViagens++;
            }
        }
        return numViagens;
    }

    public int viagensRealizadasEntreDatasPercurso(LocalDate dataInicio, LocalDate dataFim, String origem, String destino){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getData().isAfter(dataInicio) && v.getData().isBefore(dataFim) && v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numViagens++;
            }
        }
        return numViagens;
    }

    public void listarViagens(){
        for(Viagem v : viagens){
            System.out.println(v.toString());
        }
    }

    public Viagem verViagemMaisRecente(){
        LocalDate dataMaisRecente = LocalDate.of(0, 1, 1);
        Viagem viagemMaisRecente = new Viagem(0, dataMaisRecente, "", "", 0, "", 0, 0);
        for(Viagem v : viagens){
            if(v.getData().isAfter(dataMaisRecente)){
                dataMaisRecente = v.getData();
                viagemMaisRecente = v;
            }
        }
        return viagemMaisRecente;
    }


    


}
