package br.unitins.controller;

import br.unitins.Util.Message;
import br.unitins.model.Classe;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named
@ViewScoped
@Getter
@Setter
public class CalculadoraClasseController implements Serializable {
    private Classe classe;
    private List<Classe> classes;

    @PostConstruct
    public void init() {
        setClasse(new Classe());
        setClasses(new ArrayList<>());
    }

    public void addClasse() {
        if (classe.getMaximo() <= classe.getMinimo()) {
            Message.warning("O valor máximo tem que ser maior que o valor mínimo!");
            return;
        }

        if (!classes.isEmpty()) {
            if (classes.stream().max(Comparator.comparing(Classe::getMaximo)).get().getMaximo() >= classe.getMaximo()) {
                Message.warning("O valor máximo da classe deve ser maior que a ultima classe informada!");
                return;
            }

            if (classes.stream().max(Comparator.comparing(Classe::getMaximo)).get().getMaximo() > classe.getMinimo()) {
                Message.warning("O valor mínimo da classe deve ser maior ou igual que o valor máximo da ultima classe informada!");
                return;
            }
        }

        classe.setPmi((classe.getMaximo() + classe.getMinimo()) / 2);

        if (classes.isEmpty()) {
            classe.setFrequenciaAcumulada(classe.getFrequencia());
        } else {
            float freqAc = 0f;
            for (Classe c : classes)
                freqAc += c.getFrequencia();
            classe.setFrequenciaAcumulada(freqAc + classe.getFrequencia());
        }

        classes.add(classe);

        for (Classe c : classes)
            c.setFrequenciaRelativa(c.getFrequencia() / classe.getFrequenciaAcumulada());

        Message.info("Classe adicionada com sucesso!");
        setClasse(new Classe());
    }


    // 	MEDIA ------------------------------------------------
    public void getMediaClasse() {
        float midTotal = 0f;
        float freqClass = 0f;
        for (int i = 0; i < classes.size(); i++) {
            float pontoMed = ((classes.get(i).getMaximo() + classes.get(i).getMaximo()) / 2) * (getClasses().get(i).getFrequencia());
            freqClass += classes.get(i).getFrequencia();
            midTotal += pontoMed;
        }

        Message.info("A média das classes é: " + (midTotal / freqClass));
    }

    //	MEDIANA ------------------------------------------------
    public void getMedianaClasse() {
        float inferiorLimit;// LimitInferiorClassModal
        float summationFrq = 0;// Somat?rio de frequ?ncias dividido por dois
        float frqAcAnt = 0;// Frequ?ncia acumulada anterior
        int indiceClass = 0;// Indice da classe
        float modalDif;// LimitSuperiorModal - LimitInferiorModa
        float frqClass = 0; //Frequencia da Classe escolhida
        Integer[] frqAc = new Integer[classes.size()];

        for (Classe aClass : classes)
            summationFrq += aClass.getFrequencia();

        summationFrq = summationFrq / 2;
        summationFrq = Math.round(summationFrq);

        for (int i = 0; i < classes.size(); i++) {
            if (i == 0)
                frqAc[i] = (int) classes.get(i).getFrequencia();
            else
                frqAc[i] = (int) (classes.get(i).getFrequencia() + frqAc[i - 1]);
        }

        for (int i = 1; summationFrq > frqAc[i - 1]; i++)
            indiceClass = i;

        inferiorLimit = classes.get(indiceClass).getMinimo();

        modalDif = (classes.get(indiceClass).getMaximo()) - (classes.get(indiceClass).getMinimo());

        frqAcAnt = frqAc[indiceClass - 1];

        frqClass = classes.get(indiceClass).getFrequencia();

        float mediana = inferiorLimit + ((summationFrq - frqAcAnt) / (frqClass)) * modalDif;

        Message.info("A mediana das classes é: " + mediana);
    }

    //	MODA ------------------------------------------------
    public void getModaClasse() {
        float inferiorLimit;// LimitInferiorModal
        float deltaA;// FrqModal-FrqModalAnterior
        float deltaP;// FrqModal-FrqModalPosterior
        float modalDif;// LimitSuperiorModal - LimitInferiorModal
        int indiceClass = returnClass(classes) - 1; // FrqClass

        inferiorLimit = classes.get(indiceClass).getMinimo();

        if (indiceClass > 0) {
            deltaA = classes.get(indiceClass).getFrequencia() - classes.get(indiceClass - 1).getFrequencia();
            deltaP = classes.get(indiceClass).getFrequencia() - classes.get(indiceClass + 1).getFrequencia();
        } else {
            deltaA = classes.get(indiceClass).getFrequencia();
            deltaP = classes.get(indiceClass).getFrequencia();
        }

        modalDif = classes.get(indiceClass).getMaximo() - classes.get(indiceClass).getMinimo();

        float moda = inferiorLimit + ((deltaA) / (deltaA + deltaP)) * modalDif;
        Message.info("A Moda desta tabela é: " + moda);
    }

    private int returnClass(List<Classe> classes) {
        float summationFrq = 0; // Somat?rio de frequ?ncias dividido por dois
        int indiceClass = 0; // Frequ?ncia da classe
        Integer[] frqAc = new Integer[classes.size()];

        for (Classe aClass : classes) {
            summationFrq += aClass.getFrequencia();
        }

        summationFrq = summationFrq / 2;
        summationFrq = Math.round(summationFrq);

        for (int i = 0; i < classes.size(); i++) {
            if (i == 0)
                frqAc[i] = (int) classes.get(i).getFrequencia();
            else
                frqAc[i] = (int) (classes.get(i).getFrequencia() + frqAc[i - 1]);
        }

        for (int i = 1; summationFrq > frqAc[i - 1]; i++) {
            indiceClass = i + 1;
        }

        return indiceClass;
    }
}
