package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String numeroConta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void adicionarConta() throws RemoteException;
}
