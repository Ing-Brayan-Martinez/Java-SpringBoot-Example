/*
 * Copyright 2017 brayan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.infraestructure.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class Util {
    
    /**
     * Metodo para verificar si es un numero.
     * @param str
     * @return 
     */
    public final static Boolean isNumeric(Object str){
	try {
            Integer.parseInt(str.toString());
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }
    
    
     /**
     * Metodo para verificar si es un texto.
     * @param str
     * @return 
     */
    public final static boolean isString(Object str) {
        if (str.equals(str.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtener la fecha actual en formato de texto
     * @return 
     */
    public final static String getFechaActual(){
    	
        //Definir calendario
        final Calendar calendario  = new GregorianCalendar(TimeZone.getTimeZone("-4.30"));
        final java.util.Date fecha = new java.util.Date(calendario.getTimeInMillis());

        //Definir formato de salida.
        final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        
        return formatoFecha.format(fecha);
    }
    
    /**
     * Obtener la fecha y la hora actual en formato de texto personalizado.
     * @return 
     */
    public final static String getFechaHoraActual(){
    	
        //Definir calendario
        final Calendar calendario  = new GregorianCalendar(TimeZone.getTimeZone("-4.30"));
        final java.util.Date fecha = new java.util.Date(calendario.getTimeInMillis());

        //Obtener time
        final int hora      = calendario.get(Calendar.HOUR);
        final int minuto    = calendario.get(Calendar.MINUTE);
        final int segundo   = calendario.get(Calendar.SECOND);
        final int meridiano = calendario.get(Calendar.AM_PM);
        
        String m;
        
        if (meridiano == 1) {
           m = "PM"; 
        } else {
           m = "AM"; 
        }
        
        //Definir formato de salida.
        final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        
        return formatoFecha.format(fecha)+" a las "+hora+":"+minuto+":"+segundo+" "+m;

    }
    
    
    /**
     * Obtener la fecha actual en formato de objeto
     * @return 
     */
    public final static java.sql.Date geDate() {
        
        //Definir calendario
        final Calendar calendario = new GregorianCalendar(TimeZone.getTimeZone("-4.30"));
        final java.sql.Date fecha = new java.sql.Date(calendario.getTimeInMillis());

        return fecha;
    }

    /**
     * Obtener la fecha actual en formato de objeto
     * @return
     */
    public final static LocalDate geDate2() {

        //Definir calendario
        final Calendar calendario  = new GregorianCalendar(TimeZone.getTimeZone("-4.30"));
        return calendario.toInstant()
                .atZone(ZoneId.of( "UTC-04:30" ))
                .toLocalDate();
    }
    
    
    /**
     * Calcular el porcentaje de cualquier numero.
     * @param num
     * @param porce
     * @return 
     */
    public static Float calcularPorcentaje(float num, float porce) {
        return num*porce/100;
    }

    public static Date toDate(String date) {
        Date result = new Date(System.currentTimeMillis());
        try {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            result   = new Date(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
