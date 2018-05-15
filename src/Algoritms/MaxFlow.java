package Algoritms;// Java-программа для реализации алгоритма Форда Фулкерсона

import javafx.stage.FileChooser;

import sample.Main;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class MaxFlow {
    Main main = new Main();

    private int netINPUT = 0;
    private int netOUTPUT = 5;
    private int V = 0; // Число вершин в графе
    int[][] array;
    int[] nump;
    private String text = "";
    private int outputFalcerson;

    /**
     * Setter
     * public void setV(int v) {
     * V = v;
     * }
     * public void setNetINPUT(String netINPUT) {
     * this.netINPUT = Integer.parseInt(netINPUT); //из стринга в инт
     * }
     * public void setNetOUTPUT(String netOUTPUT) {
     * this.netOUTPUT = Integer.parseInt(netOUTPUT);
     * }
     */

    public int GETfordFalcerson(int getValue, String getText, String netInput, String netOutput) {
        V = getValue;
        recornationMassiv(getText);
        netINPUT = Integer.parseInt(netInput);
        netOUTPUT = Integer.parseInt(netOutput);
        outputFalcerson = fordFulkerson(array, netINPUT, netOUTPUT);
        return outputFalcerson;
    }

    /**
     * Возвращает true, если есть путь от источника s к потоку
     * t в остаточном графе. Также заполняет родительский элемент [], чтобы сохранить
     * путь
     */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {

        // Создаем массив и отмечаем его как посещенный
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Создаем очередь, вставляем вершину источника и отмечаем
        // исходную вершину как посещенная
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll(); //Заполненость списка.

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }

    // Возвращает максимальный поток от s до t в заданном графе
    public int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Создаем остаточный граф и заполняем остаточный граф
        // с заданными значениями в исходном графе как  в остаточном графе
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];


        int parent[] = new int[V];

        int max_flow = 0;


        while (bfs(rGraph, s, t, parent)) {

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }


            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }


        return max_flow;
    }


    public static void main(String[] args) throws java.lang.Exception {

        int graph[][] = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        MaxFlow m = new MaxFlow();

        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph, 0, 5));


    }

    /**
     * Алгоритм считывание матрицы с диалогового окна
     */
    public void recornationMassiv(String txt) {

        array = new int[V][V];

        nump = Arrays.stream(txt.split("( |\n)")).mapToInt(Integer::parseInt).toArray(); //преоброзование строки в массив с условиями
        int wap = 0;
        for (int i = 0; i < array.length; i++) {  //преоброзование одномерного массива в двумерный
            for (int j = 0; j < array.length; j++) {
                array[i][j] = nump[wap++];

            }
        }
        text = txt;
    }




    /**
     * Getter
     */
    public void getArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Открытие файла
     */
    public String openFiletxt() {
        text = ""; //очищаем окно перед повторным открытием файла

        FileChooser fileChooser = new FileChooser(); //Создание объекта fileChoser
        fileChooser.setTitle("Open Resource File"); //title
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")); //добавление формата

        File selectedFile = fileChooser.showOpenDialog(main.primarySTAGE); //открытие файла
        try {
            InputStream output = new FileInputStream(selectedFile);  //чтение информации из файла
            for (int i = 0; i < selectedFile.length(); i++)
                text += (char) output.read();
        }  catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Закрытие файла");
        }
        return text;
    }

    /**Сохранение файла */
    public void saveFiletxt() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(main.primarySTAGE);

        if(file != null){
            SaveFile(text, file);

        }
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


}


/**
 * 0 16 13 0 0 0
 * 0 0 10 12 0 0
 * 0 4 0 0 14 0
 * 0 0 9 0 0 20
 * 0 0 0 7 0 4
 * 0 0 0 0 0 0
 */