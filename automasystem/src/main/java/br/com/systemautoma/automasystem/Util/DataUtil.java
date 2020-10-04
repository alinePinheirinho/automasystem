package br.com.systemautoma.automasystem.Util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DataUtil {


    public static boolean fimDeSemana(LocalDate data) {
        DayOfWeek dia = data.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }

    public static LocalDate PulaMesesDiaSemana(int mes) {
        LocalDate hoje = LocalDate.now();
        LocalDate proximoMes = hoje.plusMonths(mes);
        if (fimDeSemana(proximoMes)) {
            return fimDeSemana(proximoMes.plusDays(1)) ? proximoMes.plusDays(2) : proximoMes.plusDays(1);
        } else {
            return proximoMes;
        }
     }
}
