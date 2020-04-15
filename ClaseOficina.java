/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

/**
 *
 * @author Nad
 */
public class ClaseOficina {//
    public Integer area=0;
    public Integer[][] submatriz;
    public Integer[][] camino;
    public int columnas;
    public int filas;
    public int limity=0,limitx=0;
    public Integer Mrectangulo=0;
    
    public void llenador(Integer filasI, Integer[][] ilegal, Integer[] inicios, Integer inicios1,Integer filas, Integer columnas){
        int fin;
        System.out.print("inicio->"+inicios[inicios1]+"  ");
        if(inicios[inicios1+1]==null){
            fin=ilegal.length;
        }
        else{
            fin=inicios[inicios1+1];
        }
        
        
        
        submatriz= new Integer[filas][columnas];
        camino= new Integer[filas*columnas][2]; 
        for(int i=0;i<submatriz.length;i++){
            for(int j=0;j<submatriz[0].length;j++){
               for(int i2=inicios[inicios1];i2<fin;i2++){
                      
                       if(ilegal[i2][0]!=null&&ilegal[i2][1]!=null){
                           if((ilegal[i2][0]==i)&&(ilegal[i2][1]==j)){
                                   submatriz[i][j]=1;
                            }
                           
                           if(submatriz[i][j]!=null){
                                area++;
                            } 
                       }
                       
               }
                
                
            }
        }
        /*
        System.out.print("\n"+"submatriz"+"\n");
        for(int i=0;i<submatriz.length;i++){
            for(int j=0;j<submatriz[0].length;j++){
                   
                   if(j==submatriz[0].length-1){
                       System.out.print(submatriz[i][j]+"\n");
                   }
                   else{
                       System.out.print(submatriz[i][j]);
                   }
            }
        }
        */ 
        
        
        int colum= filas*columnas;
        camino= new Integer[colum][2];
        
    }
    
    public int Interador(){
        Boolean salir= false;
        Integer inicioi, inicioj;
        for(int i=0;i<submatriz.length;i++){
            for(int j=0;j<submatriz[0].length;j++){
                if(submatriz[i][j]!=null){
                   limity=i;
                   limitx=j; 
                   inicioi=i;
                   inicioj=j;
                   
                   while(limity<submatriz.length){
                         this.Recursivo(inicioi,inicioj,i);
                   }
                   System.out.print("LIMIT--->"+limity+"\n");
                   System.out.print( "mayor rectangulo: "+this.Mrectangulo);
                   salir=true;
                   break;
                }
            }
            if(salir==true){
                break;
            }
        }
        
       /*
        while(limity<submatriz.length){
            System.out.print("INICIO--->"+inicioi+""+inicioj);
            this.Recursivo(inicioi,inicioj);
            
        }
        System.out.print( "mayor rectangulo: "+this.Mrectangulo);
    */
        return this.Mrectangulo;
    }
    
    public void Recursivo(Integer i, Integer j, Integer limiteSuperior) {
        
        System.out.print("Posicion :"+i+"-"+j+" limites:"+limity+" "+limitx);
        Boolean volver=false;
        if((submatriz[i][j]!=null)&&(submatriz[i][j]==1)){
            IngresarCamino(i,j);
            Integer[][] matriz=matrizCamino();
            imprimirCamino(matriz);
            if((this.esCuadrilatero(matriz)==true)&&(this.esRectangulo(matriz)==true)){
                if(Mrectangulo==0){
                    Mrectangulo=this.contar();
                }
                else{
                    if(this.contar()>Mrectangulo){
                        Mrectangulo=this.contar();
                    }
                
                }
            }
            //this.vaciar();
        }
        if((i==limity)&&(j==submatriz[0].length-1)){//condicional para añadir una posicion al limite
            
            limity++;
            this.vaciar();
            volver=true;
            
        }
        
       
        
        if((limity>i)&&(i<submatriz.length-1)&&(volver==false)){
            
            this.Recursivo(i+1,j,limiteSuperior);
                //repetir hacia abajo 
            
        }
        if((i==limiteSuperior)&&(j<submatriz[0].length-1)){
            
            this.Recursivo(i,j+1,limiteSuperior);
                //repetir a la izquierda
        }
        
    }
    
    public void IngresarCamino(int i, int j){
        for(int contador=0;contador<camino.length;contador++){
            if((camino[contador][0]==null)&&(camino[contador][1]==null)){
                camino[contador][0]=i;
                camino[contador][1]=j;
                break;
            }
        
        }
    
    }
    public Integer[][] matrizCamino(){
        Integer[][] m= new Integer[submatriz.length][submatriz[0].length];
        
        for(int i=0;i<submatriz.length;i++){
            for(int j=0;j<submatriz[0].length;j++){
                for(int i2=0;i2<camino.length;i2++){
                      if(camino[i2][0]!=null&&camino[i2][1]!=null){
                           if((camino[i2][0]==i)&&(camino[i2][1]==j)){
                                   m[i][j]=1;
                            }
                           
                           
                       }
                
                }
                
            }
        }
        
        
        return m;
    }
    
    public Boolean esCuadrilatero(Integer[][] m){
       Boolean condicion=true;
       Integer columnaR=0,columnaC=0;
       Integer iInicio=null,jInicio=null;
            for(int i=0;i<m.length;i++){
                for(int j=0;j<m[0].length;j++){
                    if(m[i][j]!=null){
                        if((columnaR==0)&&(m[i][j]==1)){
                            columnaR++;
                            iInicio=i;
                            jInicio=j;
                        }
                        if((i==iInicio)&&(j>jInicio)&&(j<m[0].length)&&(m[i][j]==1)){
                            columnaR++;
                        }

                        if((i>iInicio)&&(m[i][j]==1)){
                            columnaC++;
                        }
                    }
                    
                    if((j==m[0].length-1)&&(columnaR!=columnaC)&&(columnaC>0)){
                        condicion=false;
                        break;
                    }
                    
                    if((j==m[0].length-1)&&(columnaR==columnaC)){
                        columnaC=0;
                    }
                }
                if(condicion==false){
                    break;
                }
            }
        if(condicion==true){
            System.out.print('\n');
            System.out.print("Es cuadrilatero");
            System.out.print('\n');
        }
        else{
            System.out.print('\n');
            System.out.print("No es cuadrilatero");
            System.out.print('\n');
        }
        return condicion;
    }
    
    public Boolean esRectangulo(Integer[][] matriz){
        Boolean condicion=true;
       
        int filasValidas=0, columnasValidas=0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                 
                if(matriz[i][j]!=null){
                    /*
                    if((iAnalizado==false)&&(matriz[i][j]==1)){
                        filasValidas++;
                        iAnalizado=true;
                    }
                    if((matriz[i][j]==1)&&(columnasValidas==0)){
                        columnasValidas++;
                        iUnico=i;
                        jUnico=j;
                    }
                    if((i==iUnico)&&(j>jUnico)&&(matriz[i][j]==1)){
                        columnasValidas++;
                    }
                    if(j==matriz[0].length-1){
                        iAnalizado=false;
                    }
                            */
                    //System.out.print(matriz[i-1][j]);
                    if((i==0)||(matriz[i-1][j]==null)){
                        filasValidas++;
                    }
                    if((j==0)||(matriz[i][j-1]==null)){
                        columnasValidas++;
                    }
                    
                }
            }
        }
        
        if(filasValidas==columnasValidas){
            condicion=false;
        }
        if(condicion==true){
            System.out.print('\n');
            System.out.print("Es rectangulo"+filasValidas+"-"+columnasValidas);
            System.out.print('\n');
        }
        else{
            System.out.print('\n');
            System.out.print("No es rectangulo"+filasValidas+"-"+columnasValidas);
            System.out.print('\n');
        }
        
        
        return condicion;
    }
    
    public Integer contar(){
        int numero=0;
        for(int i=0;i<camino.length;i++){
            if((camino[i][0]!=null)&&(camino[i][1]!=null)){
                numero++;
            }
        }
        
        return numero;
    }
    
    public void vaciar(){
        for(int i=0;i<camino.length;i++){
            if((camino[i][0]!=null)&&(camino[i][1]!=null)){
                camino[i][0]=null;
                camino[i][1]=null;
            }
        }
    }
    
    public void imprimir(){
        System.out.print('\n');
        
        for(int i=0;i<submatriz.length;i++){
            for(int j=0;j<submatriz[0].length;j++){
                System.out.print(submatriz[i][j]);
                if(j==submatriz[0].length-1){
                    System.out.print('\n');
                }
            }
        }
    }
    public void imprimirCamino(Integer matriz[][]){
        System.out.print('\n');
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                System.out.print(matriz[i][j]);
                if(j==matriz[0].length-1){
                    System.out.print('\n');
                }
            }
        }
    }
    
    protected void finalize() throws Throwable{
    
        super.finalize();
    
    }
    
}
