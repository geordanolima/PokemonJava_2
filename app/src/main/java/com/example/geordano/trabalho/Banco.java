package com.example.geordano.trabalho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.geordano.trabalho.Itens;

import java.util.ArrayList;

public class Banco extends SQLiteOpenHelper {

        private final String TABELAITENS = "itens";
        
        private final String COLUNA_ID = "_id";
        private final String COLUNA_NOME = "nome";
        private final String COLUNA_QUANT = "quant";
        private final String COLUNA_VALOR = "valor";
        private final String COLUNA_IMG = "img";
        private final String COLUNA_APELIDO = "apelido";
        private final String COLUNA_SENHA = "senha";
        private final String COLUNA_EMAIL = "email";
        private final String TABELATRETOSA = "tretosa";
        private final String TABELAJOGADORES = "jogadores";
        private final String COLUNA_ID_JOG = "jogador";
        private final String COLUNA_ID_ITE = "item";

        public Banco(Context context, String nome, int versao) {
            super(context, nome, null, versao);
        }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABELAITENS + " ("
                +COLUNA_ID + " INTEGER PRIMARY KEY autoincrement,"
                +COLUNA_NOME + " varchar(50) NOT NULL,"
                +COLUNA_QUANT + " INTEGER(20) NOT NULL ,"
                +COLUNA_VALOR + " INTEGER(20) NOT NULL ,"
                +COLUNA_IMG + " INTEGER(50) NOT NULL"
                +");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELAJOGADORES + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY autoincrement,"
                + COLUNA_NOME + " varchar(50) NOT NULL,"
                + COLUNA_APELIDO + " varchar(20) NOT NULL ,"
                + COLUNA_SENHA + " varchar(20) NOT NULL ,"
                + COLUNA_EMAIL + " varchar(50) NOT NULL"
                + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELATRETOSA + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY autoincrement,"
                + COLUNA_ID_JOG + " INTEGER NOT NULL,"
                + COLUNA_ID_ITE + " INTEGER NOT NULL, "
                + "FOREIGN KEY(" + COLUNA_ID_JOG + ") REFERENCES " + TABELAJOGADORES + "( " + COLUNA_ID + " ),"
                + "FOREIGN KEY(" + COLUNA_ID_ITE + ") REFERENCES " + TABELAITENS + "( " + COLUNA_ID + " )"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELAITENS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELAJOGADORES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELATRETOSA);
        onCreate(sqLiteDatabase);
    }


    public Itens lerItem(int id){
        Cursor cursor = this.getWritableDatabase().query(TABELAITENS,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_QUANT, COLUNA_VALOR, COLUNA_IMG},
                 COLUNA_ID + " = " + id, null, null, null, null);
        Itens iten = null;
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            iten = new Itens();
            iten.setId(cursor.getInt(0));
            iten.setNomeItem(cursor.getString(1));
            iten.setValor(cursor.getInt(2));
            iten.setQuantItem(cursor.getInt(3));
            iten.setImagem(Integer.parseInt(cursor.getString(4)));
        }
        cursor.close();
        return iten;
    }
    public Itens leridItem (String nome){
        Cursor cursor = this.getWritableDatabase().query(TABELAITENS,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_QUANT, COLUNA_VALOR, COLUNA_IMG},
                 COLUNA_NOME + " = '" + nome + "'", null, null, null, null);
        Itens iten = null;
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            iten = new Itens();
            iten.setId(cursor.getInt(0));
            iten.setNomeItem(cursor.getString(1));
            iten.setValor(cursor.getInt(2));
            iten.setQuantItem(cursor.getInt(3));
            iten.setImagem(Integer.parseInt(cursor.getString(4)));
        }
        cursor.close();
        return iten;
    }

    public ArrayList<Itens> lerItens(){
        Cursor cursor = this.getWritableDatabase().query(TABELAITENS,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_QUANT, COLUNA_VALOR, COLUNA_IMG},
                null, null, null, null, null);
        Itens iten = null;
        ArrayList<Itens> variavel = new ArrayList<>();
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            while (!cursor.isLast()) {
                iten = new Itens();
                iten.setId(cursor.getInt(0));
                iten.setNomeItem(cursor.getString(1));
                iten.setValor(cursor.getInt(2));
                iten.setQuantItem(cursor.getInt(3));
                iten.setImagem(Integer.parseInt(cursor.getString(4)));
                variavel.add(iten);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return variavel;
    }
    
        public void gravarItens(Itens itens){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",itens.getNomeItem());
        contentValues.put("valor",itens.getValor());
        contentValues.put("quant",itens.getQuantItem());
        contentValues.put("img",itens.getImagem());
        this.getWritableDatabase().insert(TABELAITENS, null, contentValues);
    }

    public void deletarItens(Itens itens){
        String where = "_id ="+ itens.getId();
        this.getWritableDatabase().delete(TABELAITENS, where, null);
        this.close();
    }

    public int getultimoID()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT MAX(_id) FROM "+TABELATRETOSA, null);
        int ultimo = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
        return ultimo;
    }

    public Jogador lerJogador(String nome, String senha) {
        Cursor cursor = this.getWritableDatabase().query(TABELAJOGADORES,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_APELIDO, COLUNA_EMAIL, COLUNA_SENHA},
                "nome = '" + nome + "' and senha = '" + senha + "'", null, null, null, null);

        Jogador jogador = null;
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            jogador = new Jogador();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setApelido(cursor.getString(2));
            jogador.setEmail(cursor.getString(3));
            jogador.setSenha(cursor.getString(4));
        }
        cursor.close();
        return jogador;
    }

    public Jogador lerJogadorNome (String nome) {
        Cursor cursor = this.getWritableDatabase().query(TABELAJOGADORES,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_APELIDO, COLUNA_EMAIL, COLUNA_SENHA},
                "nome = '" + nome + "'", null, null, null, null);

        Jogador jogador = null;
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            jogador = new Jogador();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setApelido(cursor.getString(2));
            jogador.setEmail(cursor.getString(3));
            jogador.setSenha(cursor.getString(4));
        }
        cursor.close();
        return jogador;
    }

    public void gravarJogador(Jogador jogador) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", jogador.getNome());
        contentValues.put("apelido", jogador.getApelido());
        contentValues.put("email", jogador.getEmail());
        contentValues.put("senha", jogador.getSenha());
        this.getWritableDatabase().insert(TABELAJOGADORES, null, contentValues);
    }

    public void gravarTreta(Jogador jog, Itens item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("jogador", jog.getId());
        contentValues.put("item", item.getId());
        this.getWritableDatabase().insert(TABELATRETOSA, null, contentValues);
    }

    public int BuscaTreta (int id, Jogador jog){
            int ret = 0;
            Cursor cursor = this.getWritableDatabase().query(TABELATRETOSA, new String[]{COLUNA_ID, COLUNA_ID_JOG, COLUNA_ID_ITE},
                    "_id = " + id + " and jogador = " + jog.getId(),null, null, null, null);

            if (cursor.getCount() >=1 ) {
                cursor.moveToFirst();
                ret = cursor.getInt(2);
            }
            cursor.close();
            return ret;
    }

    public void DeletaTreta(int idjog, int iditem){
            String ueri = "jogador = " + idjog + " and item = " + iditem;
            this.getWritableDatabase().delete(TABELATRETOSA,ueri,null);
            this.close();
    }

    public void Popula() {
        Itens item = new Itens();
        ArrayList<Itens> itens = new ArrayList<>();
        item.setNomeItem("Melancia");
        item.setValor(50);
        item.setQuantItem(5);
        item.setImagem(R.drawable.melancialeite);
        gravarItens(item);
        itens.add(item);

        item.setNomeItem("pokebola Ver");
        item.setValor(10);
        item.setQuantItem(10);
        item.setImagem(R.drawable.pokebola_red);
        gravarItens(item);
        itens.add(item);

        item.setNomeItem("pokebola Azu");
        item.setValor(15);
        item.setQuantItem(1);
        item.setImagem(R.drawable.pokebola_blue);
        gravarItens(item);
//        itens.add(item);

//        Jogador jogador = new Jogador();
//        jogador.setNome("Jão");
//        jogador.setApelido("Tretoso");
//        jogador.setEmail("jaodastreta@gmeu.com");
//        jogador.setPontos(666);
//        jogador.setSenha("XXX");
//        gravarJogador(jogador);
//
//
//
//        jogador = lerJogador("Jão","XXX");
//        jogador.setItens(itens);
////        atualizarJogador(jogador);
//
////        gravar tretas na tabela de treta
    }
}
