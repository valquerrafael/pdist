package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numeroConta) throws RemoteException {
        Conta conta = contas.stream().filter(c -> c.getNumero().equals(numeroConta)).findFirst().orElse(null);
        if (conta != null)
            return conta.getSaldo();
        else
            throw new RemoteException();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta() throws RemoteException {
        contas.add(new Conta(Integer.toString(quantidadeContas()+1), 0.0));
    }

}
