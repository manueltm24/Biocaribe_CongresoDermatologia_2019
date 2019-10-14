package com.example.biocaribe_congresodermatologia_2019.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.LaboratorioViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.LineaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.PreguntaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.RespuestaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Laboratorio;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;
import com.example.biocaribe_congresodermatologia_2019.R;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button iniciar;
    private Button configurar;

    LaboratorioViewmodel laboratorioViewmodel;
    LineaViewmodel lineaViewmodel;
    PreguntaViewmodel preguntaViewmodel;
    RespuestaViewmodel respuestaViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PreguntaActivity.class));
            }
        });
        configurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConfiguracionActivity.class));

            }
        });

        laboratorioViewmodel = ViewModelProviders.of(this).get(LaboratorioViewmodel.class);
        lineaViewmodel = ViewModelProviders.of(this).get(LineaViewmodel.class);
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel.class);
        respuestaViewmodel = ViewModelProviders.of(this).get(RespuestaViewmodel.class);


        //INSERT
        insertLaborarios();
        insertLineas();

        try {
            if(preguntaViewmodel.getAll().size()<=0) {
                insertCantabriaLabsPreguntas_Respuestas();
                insertUriagePreguntas_Respuestas();
                insertNeostrataPreguntas_Respuestas();

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //IMPRIME EL SIZE DE LAS ENTIDADES EN LA  BD
        try {
            System.out.println("LABORATORIOS:" + laboratorioViewmodel.getAll().size());
            System.out.println("LINEAS:" + lineaViewmodel.getAll().size());
            System.out.println("PREGUNTAS:" + preguntaViewmodel.getAll().size());
            System.out.println("RESPUESTAS:" + respuestaViewmodel.getAll().size());


            for (Respuesta repuesta: respuestaViewmodel.getAllByPregunta(1L)) {
                System.out.println(repuesta.getRespuesta());

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void insertLaborarios(){
        try {
            //VERIFICA SI LOS DATOS DE LOS LABORARIOS ESTAN EN LA BD
            if(laboratorioViewmodel.getAll().size()<=0){

                Laboratorio cantabriaLabs = new Laboratorio("Cantabria Labs");
                laboratorioViewmodel.insert(cantabriaLabs);

                Laboratorio uriage = new Laboratorio("Uriage");
                laboratorioViewmodel.insert(uriage);

                Laboratorio neostrata = new Laboratorio("Neostrata");
                laboratorioViewmodel.insert(neostrata);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertLineas(){

        try {
            //VERIFICA SI LOS DATOS DE LAS LINEAS ESTAN EN LA BD
            if(lineaViewmodel.getAll().size()<=0){
                Linea heliocare = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Cantabria Labs").getId(),"Heliocare");
                lineaViewmodel.insert(heliocare);
                Linea biretix = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Cantabria Labs").getId(),"Biretix");
                lineaViewmodel.insert(biretix);
                Linea neoretin = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Cantabria Labs").getId(),"Neoretin");
                lineaViewmodel.insert(neoretin);
                Linea endocare = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Cantabria Labs").getId(),"Endocare");
                lineaViewmodel.insert(endocare);

                Linea uriage = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Uriage").getId(),"Uriage");
                lineaViewmodel.insert(uriage);


                Linea neostrata = new Linea(laboratorioViewmodel.getLaboratorioByNombre("Neostrata").getId(),"Neostrata");
                lineaViewmodel.insert(neostrata);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertCantabriaLabsPreguntas_Respuestas(){

        try {
            //VERIFICA SI LOS DATOS DE LAS LINEAS ESTAN EN LA BD
            Pregunta pregunta_1 = new Pregunta("Protección muy alta para las pieles mas sensibles y sometidas a exposición solar intensa, contrarrestando los daños derivados del sol",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_1 = preguntaViewmodel.insert(pregunta_1);

            respuestaViewmodel.insert(new Respuesta("Heliocare Ultra Gel SPF 90",idPregunta_1,true));
            respuestaViewmodel.insert(new Respuesta("Heliocare Advanced Gel SPF 50",idPregunta_1,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Gel Oil-Free",idPregunta_1,false));


            Pregunta pregunta_2 = new Pregunta("¿Cúal es el protector solar que previene el fotoenvejecimiento, las imperfecciones aportando un aspecto de maquillado y un tono homogéneo y uniforme?",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_2 = preguntaViewmodel.insert(pregunta_2);

            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Gel Oil-Free",idPregunta_2,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare Heliocare Color Compacto Oil-Free SPF 50",idPregunta_2,true));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Color Gel Oil-Free SPF 50",idPregunta_2,false));


            Pregunta pregunta_3 = new Pregunta("Aporta nutrición y confort a las pieles normales a secas, lucha eficazmente contra la pérdida de firmeza y relajamiento cutáneo",lineaViewmodel.getLineaByNombre("Endocare").getId());
            long idPregunta_3 = preguntaViewmodel.insert(pregunta_3);

            respuestaViewmodel.insert(new Respuesta("Endocare Tensage Cream",idPregunta_3,true));
            respuestaViewmodel.insert(new Respuesta("Endocare Basic GelCream",idPregunta_3,false));
            respuestaViewmodel.insert(new Respuesta("Endocare Cellpro GelCream",idPregunta_3,false));


            Pregunta pregunta_4 = new Pregunta("La Linea Endocare Tesage posee un producto que rejuvenece y embellece la zona del contorno de ojos, rellenan las arrugas y reducen las bolsas",lineaViewmodel.getLineaByNombre("Endocare").getId());
            long idPregunta_4 = preguntaViewmodel.insert(pregunta_4);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_4,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_4,false));

//            Pregunta pregunta_5 = new Pregunta("Crema formulada especialmente para proteger la piel y ayudar a su regeneración tras la aplicación de terapias agresivas que utilizan radiaciones ionizantes (radioterapia)",lineaViewmodel.getLineaByNombre("Radiocare").getId());
//            long idPregunta_5 = preguntaViewmodel.insert(pregunta_5);
//
//            respuestaViewmodel.insert(new Respuesta("",idPregunta_5,false));
//            respuestaViewmodel.insert(new Respuesta("Radiocare crema",idPregunta_5,true));

            Pregunta pregunta_6 = new Pregunta("Se utiliza para todo tipo de pieles, en especial para aquellas que buscan reforzar su protección a la vez que potencian su bronceado, piel sensible, con lesiones pre cancerosas y sobre todo refuerza la vía tópica",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_6 = preguntaViewmodel.insert(pregunta_6);

            respuestaViewmodel.insert(new Respuesta("Heliocare Advanced Gel SPF 50",idPregunta_6,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Gel-Oil Free",idPregunta_6,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Cápsulas",idPregunta_6,true));

            Pregunta pregunta_7 = new Pregunta("Es una fotoinmunoprotección muy alta para pieles sensibles, reactivas e intolerantes. Previene el fotoenvejecimiento, las manchas y las hiperpigmentaciones",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_7 = preguntaViewmodel.insert(pregunta_7);

            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Mineral",idPregunta_7,true));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Airgel",idPregunta_7,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Gel-Oil Free",idPregunta_7,false));

            Pregunta pregunta_8 = new Pregunta("Ayuda a la exfoliación y renovación epidérmica, las arrugas y el acné se suavizan y la piel se vuelve más tersa y luminosa. Impide la formación de la melanina. Las manchas se reducen y el tono de la piel se vuelve más uniforme",lineaViewmodel.getLineaByNombre("Neoretin").getId());
            long idPregunta_8 = preguntaViewmodel.insert(pregunta_8);

            respuestaViewmodel.insert(new Respuesta("Neoretin Discrom Control GelCream ",idPregunta_8,false));
            respuestaViewmodel.insert(new Respuesta("Neoretin Discrom Control Serum",idPregunta_8,true));

            Pregunta pregunta_9 = new Pregunta("Tienen la peculiaridad que reafirman la piel, la hidratan, iluminan y sobre todo efecto aclarante",lineaViewmodel.getLineaByNombre("Endocare").getId());
            long idPregunta_9 = preguntaViewmodel.insert(pregunta_9);

            respuestaViewmodel.insert(new Respuesta("Endocare Tensage Ampollas",idPregunta_9,true));
            respuestaViewmodel.insert(new Respuesta("Endocare C Pure",idPregunta_9,false));

            Pregunta pregunta_10 = new Pregunta("Es un fotoinmunoprotector con cobertura muy amplia; protege frente a UVB, UVA, IR-A y visible, con una potente acción antioxidante y con novedosos activos que ayudan a reparar el ADN",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_10 = preguntaViewmodel.insert(pregunta_10);

            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Airgel",idPregunta_10,true));
            respuestaViewmodel.insert(new Respuesta("Heliocare 360 Gel-Oil Free",idPregunta_10,false));

            Pregunta pregunta_11 = new Pregunta("Producto antigrasa, antiedad, purificadora y revitalizante que regula la producción de la grasa en el rostro. Perfecto para matizar la zona T",lineaViewmodel.getLineaByNombre("Biretix").getId());
            long idPregunta_11 = preguntaViewmodel.insert(pregunta_11);

            respuestaViewmodel.insert(new Respuesta("Biretix Mask",idPregunta_11,true));
            respuestaViewmodel.insert(new Respuesta("Biretix Cleanser",idPregunta_11,false));
            respuestaViewmodel.insert(new Respuesta("Biretix Duo",idPregunta_11,false));

            Pregunta pregunta_12 = new Pregunta("Fotoprotección para todo tipo de pieles, especialmente con imperfecciones; manchas, rojeces, cicatrices recientes, y como fotoprotección post-peeling y post-láser, o para pieles sometidas a tratamientos fotosensibilazantes",lineaViewmodel.getLineaByNombre("Heliocare").getId());
            long idPregunta_12 = preguntaViewmodel.insert(pregunta_12);

            respuestaViewmodel.insert(new Respuesta("Heliocare Ultra Gel SPF 90",idPregunta_12,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare Color Gelcream Brown SPF 50",idPregunta_12,false));
            respuestaViewmodel.insert(new Respuesta("Heliocare Color Cushion Compact",idPregunta_12,true));

            Pregunta pregunta_13 = new Pregunta("¿La línea cosmética antiacné de Cantabria Labs es Biretix?",lineaViewmodel.getLineaByNombre("Biretix").getId());
            long idPregunta_13 = preguntaViewmodel.insert(pregunta_13);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_13,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_13,false));

            Pregunta pregunta_14 = new Pregunta("¿La línea Biretix se encarga de tratar el acné comedogénico e inflamatorio?",lineaViewmodel.getLineaByNombre("Biretix").getId());
            long idPregunta_14 = preguntaViewmodel.insert(pregunta_14);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_14,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_14,false));

            Pregunta pregunta_15 = new Pregunta("Producto recomendado para el tratamiento del Acné Moderado, con excelente eficiencia",lineaViewmodel.getLineaByNombre("Biretix").getId());
            long idPregunta_15 = preguntaViewmodel.insert(pregunta_15);

            respuestaViewmodel.insert(new Respuesta("Biretix Cleanser",idPregunta_15,false));
            respuestaViewmodel.insert(new Respuesta("Biretix Duo",idPregunta_15,false));
            respuestaViewmodel.insert(new Respuesta("Biretix gel",idPregunta_15,true));


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void insertNeostrataPreguntas_Respuestas(){

        try {
            //VERIFICA SI LOS DATOS DE LAS LINEAS ESTAN EN LA BD
            Pregunta pregunta_1 = new Pregunta("Patente de Neostrata y cuarta generación de alfa hidroxiácidos",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_1 = preguntaViewmodel.insert(pregunta_1);

            respuestaViewmodel.insert(new Respuesta("Neoglucosamina",idPregunta_1,true));
            respuestaViewmodel.insert(new Respuesta("Sabiwhite",idPregunta_1,false));
            respuestaViewmodel.insert(new Respuesta("Gluconolactona ",idPregunta_1,false));


            Pregunta pregunta_2 = new Pregunta("Molécula altamente hidratante y potente antioxidante que aumenta el colágeno y ayuda en los procesos de cicatrización, ideal para pacientes con eczema y rosácea",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_2 = preguntaViewmodel.insert(pregunta_2);

            respuestaViewmodel.insert(new Respuesta("Ácido Kojico",idPregunta_2,false));
            respuestaViewmodel.insert(new Respuesta("Neoglucosamina",idPregunta_2,false));
            respuestaViewmodel.insert(new Respuesta("Ácido lactobiónico ",idPregunta_2,true));

            Pregunta pregunta_3 = new Pregunta("Producto de Neostrata indicado para pacientes con foliculitis e hiperqueratosis",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_3 = preguntaViewmodel.insert(pregunta_3);

            respuestaViewmodel.insert(new Respuesta("Espuma limpiadora de glicólico",idPregunta_3,false));
            respuestaViewmodel.insert(new Respuesta("Loción ultrasuavizante",idPregunta_3,true));
            respuestaViewmodel.insert(new Respuesta("Restore limpiador facial",idPregunta_3,false));

            Pregunta pregunta_4 = new Pregunta("Los peelings de Neostrata pueden aplicarse máximo  cuántos minutos",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_4 = preguntaViewmodel.insert(pregunta_4);

            respuestaViewmodel.insert(new Respuesta("10 minutos",idPregunta_4,false));
            respuestaViewmodel.insert(new Respuesta("5 minutos",idPregunta_4,true));
            respuestaViewmodel.insert(new Respuesta("20 minutos  ",idPregunta_4,false));

            Pregunta pregunta_5 = new Pregunta("¿Cuál de los siguientes peelings de Neostrata está indicado para el tratamiento del acné?",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_5 = preguntaViewmodel.insert(pregunta_5);

            respuestaViewmodel.insert(new Respuesta("Ácido glicólico 20%",idPregunta_5,false));
            respuestaViewmodel.insert(new Respuesta("Ácido glicólico 35% ",idPregunta_5,false));
            respuestaViewmodel.insert(new Respuesta("Peeling Clarificante",idPregunta_5,true));
            respuestaViewmodel.insert(new Respuesta("Peeling iluminador",idPregunta_5,false));

            Pregunta pregunta_6 = new Pregunta("¿Cuál de las siguientes líneas de Neostrata va dirigida al tratamiento anti edad?",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_6 = preguntaViewmodel.insert(pregunta_6);

            respuestaViewmodel.insert(new Respuesta("Skin Active (Revitalizadora)",idPregunta_6,true));
            respuestaViewmodel.insert(new Respuesta("Tratamientos específicos",idPregunta_6,false));
            respuestaViewmodel.insert(new Respuesta("Restore (Restauradora)  ",idPregunta_6,false));

            Pregunta pregunta_7 = new Pregunta("¿Cuántos productos tiene la Gama Iluminadora (Enlighten) de Neostrata?",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_7 = preguntaViewmodel.insert(pregunta_7);

            respuestaViewmodel.insert(new Respuesta("2",idPregunta_7,false));
            respuestaViewmodel.insert(new Respuesta("3",idPregunta_7,true));
            respuestaViewmodel.insert(new Respuesta("5",idPregunta_7,false));

            Pregunta pregunta_8 = new Pregunta("La Loción Biónica de Neostrata esta indicada para tratar",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_8 = preguntaViewmodel.insert(pregunta_8);

            respuestaViewmodel.insert(new Respuesta("Manchas",idPregunta_8,false));
            respuestaViewmodel.insert(new Respuesta("Acné",idPregunta_8,false));
            respuestaViewmodel.insert(new Respuesta("Resequedad, enrojecimiento",idPregunta_8,true));

            Pregunta pregunta_9 = new Pregunta("Para un paciente con hiperpigmentaciones que gama de Neostrata usaría",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_9 = preguntaViewmodel.insert(pregunta_9);

            respuestaViewmodel.insert(new Respuesta("Gama Iluminadora",idPregunta_9,true));
            respuestaViewmodel.insert(new Respuesta("Revitalizadora",idPregunta_9,false));
            respuestaViewmodel.insert(new Respuesta("Restauradora",idPregunta_9,false));

            Pregunta pregunta_10 = new Pregunta("Es el producto ideal para tratar pacientes con flacidez de cuello y escote",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_10 = preguntaViewmodel.insert(pregunta_10);

            respuestaViewmodel.insert(new Respuesta("Loción Ultrasuavizante",idPregunta_10,false));
            respuestaViewmodel.insert(new Respuesta("Skin Active Triple reafirmante de cuello",idPregunta_10,true));
            respuestaViewmodel.insert(new Respuesta("Iluminador de la piel SPF25",idPregunta_10,false));

            Pregunta pregunta_11 = new Pregunta("Los fundadores de Neostrata los  Dres. Van Scott y el Dr.YU, ¿fueron los que descubrieron  los alfa hidroxiácidos?",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_11 = preguntaViewmodel.insert(pregunta_11);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_11,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_11,false));

            Pregunta pregunta_12 = new Pregunta("La Gama Refine (Refinadora) de Neostrata está indicada para pieles grasas y/o con acné",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_12 = preguntaViewmodel.insert(pregunta_12);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_12,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_12,false));

            Pregunta pregunta_13 = new Pregunta("Los peelings de Neostrata no son tamponados",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_13 = preguntaViewmodel.insert(pregunta_13);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_13,false));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_13,true));

            Pregunta pregunta_14 = new Pregunta("Todos los productos de Neostrata se usan durante el día",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_14 = preguntaViewmodel.insert(pregunta_14);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_14,false));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_14,true));

            Pregunta pregunta_15 = new Pregunta("De la Gama Tratamientos Específicos de Neostrata la Nail Condition (Acondicionador de uñas) está indicado para unas débiles y quebradizas",lineaViewmodel.getLineaByNombre("Neostrata").getId());
            long idPregunta_15 = preguntaViewmodel.insert(pregunta_15);

            respuestaViewmodel.insert(new Respuesta("Verdadero",idPregunta_15,true));
            respuestaViewmodel.insert(new Respuesta("Falso",idPregunta_15,false));



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void insertUriagePreguntas_Respuestas(){

        try {
            //VERIFICA SI LOS DATOS DE LAS LINEAS ESTAN EN LA BD
            Pregunta pregunta_1 = new Pregunta("¿Cuál de estos protocolos indicaría a un paciente con piel deshidratada? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_1 = preguntaViewmodel.insert(pregunta_1);

            respuestaViewmodel.insert(new Respuesta("Uriage Agua Termal, Uriage Crema Lavante, Uriage Contorno de ojos de Agua, Uriage Crema ligera, Uriage Serum de Agua",idPregunta_1,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Micelar, Uriage Agua Termal, Uriage Hyseac Gel, Uriage Contorno de Ojos de Agua, Uriage Serum de Agua",idPregunta_1,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Termal, Uriage Crema Lavante, Uriage Bariesun Mat Spf50, Uriage Xémose Leche Emoliente ",idPregunta_1,false));


            Pregunta pregunta_2 = new Pregunta("¿Cuáles de estos protocolos son Ideales para pieles Acnéicas? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_2 = preguntaViewmodel.insert(pregunta_2);

            respuestaViewmodel.insert(new Respuesta("Uriage Hyseac Gel, Uriage Bariesun Mat Spf 50, Uriage Hyseac K18, Uriage Hyseac 3 Regul. ",idPregunta_2,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Micelar Piel Grasa, Uriage Hyseac Gel,Uriage Fluido Solar SPF 50. ",idPregunta_2,false));
            respuestaViewmodel.insert(new Respuesta("Toda las anteriores",idPregunta_2,true));

            Pregunta pregunta_3 = new Pregunta("¿Cuál de estos productos es indicado para callocidades?",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_3 = preguntaViewmodel.insert(pregunta_3);

            respuestaViewmodel.insert(new Respuesta("Uriage Bariederm Fissure",idPregunta_3,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Pruriced Gel",idPregunta_3,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Keratosane 30",idPregunta_3,true));

            Pregunta pregunta_4 = new Pregunta("¿Cuáles de estos protocolos es ideal para pacientes con cuperosis? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_4 = preguntaViewmodel.insert(pregunta_4);

            respuestaViewmodel.insert(new Respuesta("Uriage Roseliane Fluido Dermo Limpiador, Uriage Cc Cream spf 30, Uriage Bariesun Mineral Spf 50, Uriage Roseliane Crema",idPregunta_4,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Micelar Piel Intolerante, Uriage Agua Termal, Uriage Roseliane Cc Cream spf 30, Uriage Roseliane Crema",idPregunta_4,false));
            respuestaViewmodel.insert(new Respuesta("Todas las Anteriores",idPregunta_4,true));

            Pregunta pregunta_5 = new Pregunta("¿Cuál es el protocolo por excelencia en casos de Dermatitis, Escamación y Picazón? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_5 = preguntaViewmodel.insert(pregunta_5);

            respuestaViewmodel.insert(new Respuesta("Uriage Ds Gel, Uriage Ds Emulsión, Uriage Ds Loción",idPregunta_5,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Bariederm Cica Gel con Cu Zn, Uriage Bariederm Cica Crema Con CU ZN, Uriage Bariederm Cica Spray con CU ZN",idPregunta_5,false));
            respuestaViewmodel.insert(new Respuesta("Ninguna de Las Anteriores",idPregunta_5,false));

            Pregunta pregunta_6 = new Pregunta("¿Cúales de estos Productos Recomienda en Casos de Melasma, Cloasma y Léntigos?",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_6 = preguntaViewmodel.insert(pregunta_6);

            respuestaViewmodel.insert(new Respuesta("Uriage Depiderm SPF 50, Uriage Depiderm Soin Cible",idPregunta_6,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Termal, Uriage Crema Peeling Multi Acción",idPregunta_6,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Xémose Syndet, Uriage Bariesun Brume SPF 50",idPregunta_6,false));

            Pregunta pregunta_7 = new Pregunta("¿Cómo se llama Nuestra Gama de Fotoprotectores Uriage?",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_7 = preguntaViewmodel.insert(pregunta_7);

            respuestaViewmodel.insert(new Respuesta("Uriage Bariesun",idPregunta_7,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Protection SPF 50",idPregunta_7,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Agua Termal",idPregunta_7,false));

            Pregunta pregunta_8 = new Pregunta("¿Cuál de estos productos es ideal para pacientes con Queilitis, Herpes, Labios Agrietados? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_8 = preguntaViewmodel.insert(pregunta_8);

            respuestaViewmodel.insert(new Respuesta("Uriage Stick Levres",idPregunta_8,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Bariederm Cica Levres",idPregunta_8,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Hyseac Barra Dermatológica",idPregunta_8,false));

            Pregunta pregunta_9 = new Pregunta("¿Con cuáles de estas caracteristicas cumple el Agua Termal de Uriage?",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_9 = preguntaViewmodel.insert(pregunta_9);

            respuestaViewmodel.insert(new Respuesta("Es Isotónica",idPregunta_9,false));
            respuestaViewmodel.insert(new Respuesta("Enriquecida con Sales Minerales y Oligoelementos, pesa 11G/L sobre Residuo seco",idPregunta_9,false));
            respuestaViewmodel.insert(new Respuesta("Todas Las Anteriores son correctas",idPregunta_9,true));

            Pregunta pregunta_10 = new Pregunta("¿Cúal de estos productos es Ideal para Pacientes con Hiperhidrosis y piel sensible? ",lineaViewmodel.getLineaByNombre("Uriage").getId());
            long idPregunta_10 = preguntaViewmodel.insert(pregunta_10);

            respuestaViewmodel.insert(new Respuesta("Uriage Desodorante Puissance",idPregunta_10,true));
            respuestaViewmodel.insert(new Respuesta("Uriage Desodorante Triactive",idPregunta_10,false));
            respuestaViewmodel.insert(new Respuesta("Uriage Barra Dermatológica",idPregunta_10,false));





        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initializeUI() {
        iniciar = findViewById(R.id.btn_inciar);
        configurar = findViewById(R.id.btn_configurar);closeContextMenu();
    }

}
