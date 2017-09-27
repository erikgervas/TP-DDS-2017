package DB.Serializador;

import Modelo.Indicadores.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdaptadorJson {

    private RuntimeTypeAdapterFactory<Expresiones> adapter(){
        RuntimeTypeAdapterFactory<Expresiones> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Expresiones.class, "type")
                .registerSubtype(Numero.class, "Numero")
                .registerSubtype(Cuenta_Indicadores.class, "Cuenta")
                .registerSubtype(Operacion.class, "Operacion")
                .registerSubtype(Suma.class, "Suma")
                .registerSubtype(Resta.class, "Resta")
                .registerSubtype(Division.class, "Division")
                .registerSubtype(Multiplicacion.class, "Multiplicacion")
                .registerSubtype(Indicador.class, "Indicador");
                
        return runtimeTypeAdapterFactory;
    }

    public Gson getAdaptador(){
        return new GsonBuilder().registerTypeAdapterFactory(this.adapter()).create();
    }

}