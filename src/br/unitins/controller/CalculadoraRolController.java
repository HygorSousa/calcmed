package br.unitins.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class CalculadoraRolController implements Serializable {
    private Float valor;
    private List<Float> valores;
    private Float mediaArit;
    private Float mediana;

    public void addValor() {
        if (valor != null) {
            getValores().add(valor);
        }
        getValores().sort(Float::compareTo);
    }

    public void getMedArit() {
        Float valueTotal = 0f;
        for (Float num : valores) {
            valueTotal += num;
        }
        setMediaArit(valueTotal / valores.size());
    }

    public void getModa() {
        Map<Float, Integer> freqNum = new HashMap<>();
        int maiorFreq = 0;
        for (Float numList : valores) {
            Integer qtd = freqNum.get(numList);

            if (qtd == null) {
                qtd = 1;
            } else {
                qtd += 1;
            }

            freqNum.put(numList, qtd);

            if (maiorFreq < qtd) {
                maiorFreq = qtd;
            }
        }
        System.out.println("A(s) moda é(são): ");

        for (Float numChav : freqNum.keySet()) {
            int qtd = freqNum.get(numChav);
            if (maiorFreq == qtd) {
                System.out.println(numChav + " ");
            }
        }
    }

    public void getCalcMediana() {
        valores.sort(Float::compareTo);

        for (Float float1 : valores) {
            System.out.print(float1 + " ");
        }

        Float result = 0.0f;
        if (valores.size() % 2 == 0) {
            int posicaoNum = Math.round((valores.size() + 1) >> 1) - 1;
            float pri = valores.get(posicaoNum);
            float sec = valores.get(posicaoNum + 1);
            result = (pri + sec) / 2;
        }
        else {
            int posicaoNum = ((valores.size() + 1) / 2) - 1;
            result = valores.get(posicaoNum);
        }

        setMediana(result);
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public List<Float> getValores() {
        if (valores == null)
            valores = new ArrayList<>();
        return valores;
    }

    public void setValores(List<Float> valores) {
        this.valores = valores;
    }

    public Float getMediaArit() {
        return mediaArit;
    }

    public void setMediaArit(Float mediaArit) {
        this.mediaArit = mediaArit;
    }

    public Float getMediana() {
        return mediana;
    }

    public void setMediana(Float mediana) {
        this.mediana = mediana;
    }
}
