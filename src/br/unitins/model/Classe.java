package br.unitins.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classe {
    private float minimo;
    private float maximo;
    private float frequencia;
    private float pmi;
    private float frequenciaAcumulada;
    private float frequenciaRelativa;
}
