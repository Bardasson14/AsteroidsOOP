package com.mygdx.screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mygdx.asteroids.AsteroidsGame;
import com.mygdx.objects.FileException;

public class Ranking {

    public static void salvaJogo(int pt, String nome, AsteroidsGame game, Menu menu) throws IOException {
    
        FileWriter fw = new FileWriter("ranking.txt");
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("ranking.txt"));
        
        String linha = nome + " " + pt + "\n";
        buffWrite.append(linha);
        
        game.setScreen(new Menu(game));
    }

    public Ranking(AsteroidsGame game, AsteroidsGame menu){


    }
}