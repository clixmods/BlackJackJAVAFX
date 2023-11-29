package com.example.blackjackjavafx.Repository;

import java.util.List;

public interface I_Repository<T> {

    public void inserer(T element);

    public void mettreAJour(T element);

    public void supprimer(int id);

    public T recupere(int id);

    public List<T> recupereTout();
}
