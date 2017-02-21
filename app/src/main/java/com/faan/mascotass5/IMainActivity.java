package com.faan.mascotass5;

import java.util.ArrayList;
/**
 * Created by FAAN on 20/02/2017.
 */

public interface IMainActivity {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador (ArrayList <Mascota> Mascotas);
    public void inicializarAdaptador(MascotaAdaptador adaptador);
}
