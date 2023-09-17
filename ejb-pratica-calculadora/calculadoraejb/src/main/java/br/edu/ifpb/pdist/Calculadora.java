package br.edu.ifpb.pdist;

import jakarta.ejb.Stateless;

@Stateless(name = "calculadoraService")
public class Calculadora implements CalculadoraIF {
    @Override
    public int somar(int num1, int num2) {
        return num1+num2;
    }
}
