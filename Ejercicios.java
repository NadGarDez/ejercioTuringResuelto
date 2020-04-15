/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicios;
import java.util.Scanner;
import ejercicios.ClaseOficina;

/**
 *
 * @author Nad
 */
public class Ejercicios {

    public static Integer[][] ilegal;
    public static Integer numOficinas=0;
    public static Integer[] iniciosOficinas;
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lector= new Scanner(System.in);
        System.out.print("bienvenido, inserte por favor dos numeros, el numero de filas y el de columnas"+'\n');
        System.out.print("filas"+'\n');
        int filas= lector.nextInt();
        System.out.print("Columnas"+'\n');
        int columnas= lector.nextInt();
        System.out.print("Su matriz sera de del siguiente tamaño"+'\n'+" filas:"+filas+" columnas:"+columnas+'\n');
        Integer matriz[][]= new Integer[filas][columnas];
        
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                    matriz[i][j]=lector.nextInt();
            }
        }
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                   if(j==columnas-1){
                       System.out.print(matriz[i][j]+"\n");
                   }
                   else{
                       System.out.print(matriz[i][j]);
                   }
            }
        }
        int ancho=filas*columnas;
        ilegal=new Integer[ancho][2];
        iniciosOficinas= new Integer[ancho];
        //System.out.print(ilegal[0][0]);
        interador(ancho, matriz, filas, columnas);
        /*
         for(int i=0;i<ilegal.length;i++){
            System.out.print(ilegal[i][0]+" "+ilegal[i][1]+'\n');
        }
        
        for(int i=0;i<iniciosOficinas.length;i++){
            System.out.print("INICIO :"+iniciosOficinas[i]+'\n');
        }
        */
        ClaseOficina[] Oficinas= new ClaseOficina[numOficinas];
        int rectangulo=0 , mayorRectangulo= 0;
        
        for(int i=0; i<numOficinas;i++){
           ClaseOficina ofi=new ClaseOficina();
            
            ofi.llenador(ancho,ilegal,iniciosOficinas,i,filas,columnas);
            ofi.imprimir();
            rectangulo = ofi.Interador();
            if(rectangulo>mayorRectangulo){
                mayorRectangulo = rectangulo;
            }
            ofi = null;
        }
                
        System.out.print("\n"+"El mayor rectangulo de todas las oficinas es :"+mayorRectangulo);
        
       
        
        
        //seccion para encontrar el rectangulo mas grande
    }
    
    public static void interador(int ancho, Integer[][] matriz, int filas, int columnas){
        
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                Analizar(null,null,i,j,matriz,ancho,filas,columnas);
            }
        }
    
       System.out.print(numOficinas);
    
    }    
    
    
    public static int  Analizar (Integer iAnterior, Integer jAnterior, Integer iActual, Integer jActual, Integer[][] matriz,int ancho,int filas,int columnas){
        int sigi,sigj;
        if(isIlegal(iActual,jActual,matriz, ancho)==false){
            System.out.print("posicion legal->["+iActual+"]["+jActual+"]"+'\n');
            anadirIlegal(iActual,jActual, ancho, iAnterior,jAnterior);
            sigi=iActual+1;
            sigj=jActual;
            if((sigi>=0)&&(sigi<filas)&&(sigj>=0)&&(sigj<columnas)){
                //System.out.print('\n'+"abajo"+'\n');
                if((iAnterior==null)&&(jAnterior==null)){
                    Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                }
                else{
                    if((sigi!=iAnterior)||(sigj!=jAnterior)){
                    
                        Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                    }
                
                }
               
               
            }
            sigi=iActual-1;
            sigj=jActual;
            if((sigi>=0)&&(sigi<filas)&&(sigj>=0)&&(sigj<columnas)){
                System.out.print('\n'+"arriba"+'\n');
                if((iAnterior==null)&&(jAnterior==null)){
                    Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                }
                else{
                    if((sigi!=iAnterior)||(sigj!=jAnterior)){
                    
                        Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                    }
                
                }
            }   
            sigi=iActual;
            sigj=jActual+1;
            if((sigi>=0)&&(sigi<filas)&&(sigj>=0)&&(sigj<columnas)){
                System.out.print('\n'+"derecha"+'\n');
                if((iAnterior==null)&&(jAnterior==null)){
                    Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                }
                else{
                    if((sigi!=iAnterior)||(sigj!=jAnterior)){
                    
                        Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                    }
                
                }
            }
            sigi=iActual;
            sigj=jActual-1;
            if((sigi>=0)&&(sigi<filas)&&(sigj>=0)&&(sigj<columnas)){
               System.out.print('\n'+"izquierda"+'\n');
                
                if((iAnterior==null)&&(jAnterior==null)){
                    Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                }
                else{
                    if((sigi!=iAnterior)||(sigj!=jAnterior)){
                    
                        Analizar(iActual,jActual,sigi,sigj,matriz,ancho,filas,columnas);
                    }
                
                }
            }
            if((iAnterior==null)&&(jAnterior==null)){
              //  System.out.print('\n'+"conto");
                numOficinas++;
            }
            
        }
        else{
            System.out.print("posicion ilegal->["+iActual+"]["+jActual+"]"+'\n');
        }
        
        
        return 0;
    }
    public static Boolean isIlegal(int i, int j, Integer[][] matriz, int ancho){
        Boolean retorno=false;
        if(matriz[i][j]==1){
            for(int contador=0;contador<ancho;contador++){
                if((ilegal[contador][0]!=null)&&(ilegal[contador][1]!=null)){
                   if((i==ilegal[contador][0])&&(j==ilegal[contador][1])){
                       retorno = true;
                       
                       break;
                   }
                }
            }
        }
        else{
            retorno=true;
        }

        return retorno;
    }
    
    public static void anadirIlegal(int i, int j, int ancho, Integer iAnterior, Integer jAnterior){
        for(int contador=0;contador<ancho;contador++){
            if((ilegal[contador][0]==null)&&(ilegal[contador][1]==null)){
                ilegal[contador][0]=i;
                ilegal[contador][1]=j;
                if((iAnterior==null)&&(jAnterior==null)){
                    posicionInicio(contador);
                }
                break;
            }
        
        }
    }
    
    public static void posicionInicio(int i){
        for(int contador=0;contador<iniciosOficinas.length;contador++){
            if(iniciosOficinas[contador]==null){
                iniciosOficinas[contador]=i;
                break;
            }
        }
    }
    
    public static void Rectangulo1(Integer[][] matriz, int filas, int columnas){
        
    }
    
    public static void Rectangulo2(){
        
    
    }
    
    
    
}
