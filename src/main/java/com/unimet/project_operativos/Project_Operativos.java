package com.unimet.project_operativos;

import com.unimet.interfaz.Simulacion;

public class Project_Operativos {

    public static void main(String[] args) {
        // Esto inicia tu ventana
        Simulacion ventana = new Simulacion();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null); // Para que salga en el centro
    }
}