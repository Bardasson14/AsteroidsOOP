package com.mygdx.screens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.asteroids.AsteroidsGame;

public class History extends Game implements Screen{

    AsteroidsGame history;

    public static int checkSaveCondition(int score, AsteroidsGame game) throws IOException{
        int salva = JOptionPane.showConfirmDialog(null, "Deseja salvar o jogo?");
        if (salva!=JOptionPane.YES_OPTION)
            return 0;
        File  f= new File("save_files/ranking.txt");
        if (!f.exists())
            throw new IOException();
        if (f.length()!=0){
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String [] details = br.readLine().split(" ");
            int highScore = Integer.parseInt(details[1]);
            if (score < highScore && (f.length()!=0)) {
                JOptionPane.showMessageDialog(null, "Sua pontuação não superou o High Score. Tente novamente!");
                return 0;
            }
        }
        return 1;
    
    }

    public static void salvaJogo(int score, AsteroidsGame game, Menu menu) throws IOException {
        int cond = checkSaveCondition(score, game);
        if (cond==1) {
            String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome?");
            File temp = new File("save_files/ranking.txt");
            System.out.println(temp.exists());
            if (!temp.exists())
                throw new IOException();

            String actualDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        
            FileWriter fw = new FileWriter("save_files/ranking.txt", false);
            
            String linha = nome + " " + score + " " + actualDate + "\n";
            
            fw.write(linha);
            fw.flush();
            fw.close();
            game.setScreen(new Menu(game));
        }
    }

    public History(AsteroidsGame game, AsteroidsGame history, File h)throws IOException{
        if (!h.exists())
            throw new IOException();
        this.history = history;
        FileReader fr = new FileReader(h);
        

    }   

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.batch.begin();

        //this.batch.

    }

    @Override
    public void resize(int arg0, int arg1) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void create() {

    }
}