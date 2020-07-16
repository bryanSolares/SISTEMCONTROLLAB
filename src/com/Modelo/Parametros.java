package com.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Parametros {

    SIN_DEFINIR(0, "SIN DEFINIR"),
    SOPORTE(10, "Soporte"), NO_SOPORTE(11, "No Soporte"), //tipo cliente
    ALTA(20, "Alta"), MEDIA(21, "Media"), BAJA(22, "Baja"), //prioridad
    INICIAL(30, "Inicial"), PROCESO(31, "En proceso"), PENDIENTE(32, "Pendiente"), FINAL(33, "Finalizado"), PORHACER(34,"Por Hacer"), //estado tarea
    ACTIVO(1, "Activo"), INACTIVO(2, "Inactivo"), //estado cliente
    TECNICO_1(40, "Técnico 1"), PROGRAMACION(41, "Programación"), TECNICO_2(42, "Técnico 2"),
    SICAF(50, "SICAF"), CONTAIVA(51, "CONTAIVA"), FICCONTA(52, "FICCONTA"), SICAFNET(53, "SICAF NET"),
    ACADEMICO(54, "ACADEMICO"), SICAF_HOSPITAL(55, "SICAF HOSPITAL"),
    PRESENCIAL(60, "Presencial"), REMOTO(61, "Remoto"),
    //modulo sistemas
    INVENTARIO(100, "INVENTARIO"), FACTURACION(200, "FACTURACION"), CXC(300, "CUENTA POR COBRAR"), CXP(400,"CUENTA POR PAGAR"), 
    CONTABILIDAD(500,"CONTABILIDAD"), BANCOS(600,"BANCOS"), ACTIFIJOS(700,"ACTIVOS FIJOS"), PLANILLA(800,"PLANILLA"), 
    RRHH(900,"RECURSOS HUMANOS"), PRESUPU(1000,"PRESUPUESTOS"), DISTRI(1100,"DISTRIBUCION"), COSTOS(1200,"COSTOS"),
    HOSPITAL(1300,"HOSPITAL"), COMERCIAL(1400,"COMERCIAL"), ADMINIS(1500,"ADMINISTRATIVO"), CALIFICACIONES(1600,"CALIFICACIONES");
   

    private int id;
    private String nombre;
    private final List<Parametros> listaParametros;
    private final List<Parametros> listaModulos;
    public static final int DEVUELVE_ID = 1, DEVUELVE_VALOR = 2, DEVUELVE_PARAMETRO = 3;

    private Parametros(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        listaParametros = new ArrayList<>();
        listaModulos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public List<Parametros> devuelveParametrosTipoCliente() {
        listaParametros.clear();
        listaParametros.add(Parametros.SOPORTE);
        listaParametros.add(Parametros.NO_SOPORTE);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoPrioridad() {
        listaParametros.clear();
        listaParametros.add(Parametros.ALTA);
        listaParametros.add(Parametros.MEDIA);
        listaParametros.add(Parametros.BAJA);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoEstadoTarea() {
        listaParametros.clear();
        listaParametros.add(Parametros.INICIAL);
        listaParametros.add(Parametros.PROCESO);
        listaParametros.add(Parametros.PENDIENTE);
        listaParametros.add(Parametros.FINAL);
        listaParametros.add(Parametros.PORHACER);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoEstadoCliente() {
        listaParametros.clear();
        listaParametros.add(Parametros.ACTIVO);
        listaParametros.add(Parametros.INACTIVO);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoEncargadoSoporte() {
        listaParametros.clear();
        listaParametros.add(Parametros.TECNICO_1);
        listaParametros.add(Parametros.TECNICO_2);
        listaParametros.add(Parametros.PROGRAMACION);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoSistema() {
        listaParametros.clear();
        listaParametros.add(Parametros.SIN_DEFINIR);
        listaParametros.add(Parametros.SICAF);
        listaParametros.add(Parametros.CONTAIVA);
        listaParametros.add(Parametros.FICCONTA);
        listaParametros.add(Parametros.SICAFNET);
        listaParametros.add(Parametros.ACADEMICO);
        listaParametros.add(Parametros.SICAF_HOSPITAL);
        return listaParametros;
    }

    public List<Parametros> devuelveParametrosTipoCapacitacion() {
        listaParametros.clear();
        listaParametros.add(Parametros.PRESENCIAL);
        listaParametros.add(Parametros.REMOTO);
        return listaParametros;
    }

    public List<Parametros> devuelveListaModulos(Parametros tipoSistema) {
        listaModulos.clear();
        switch (tipoSistema) {
            case SICAFNET:
                listaModulos.add(INVENTARIO);
                listaModulos.add(FACTURACION);
                listaModulos.add(CXC);
                listaModulos.add(CXP);
                listaModulos.add(CONTABILIDAD);
                listaModulos.add(BANCOS);
                listaModulos.add(ACTIFIJOS);
                listaModulos.add(PLANILLA);
                listaModulos.add(RRHH);
                listaModulos.add(PRESUPU);
                listaModulos.add(DISTRI);
                listaModulos.add(COSTOS);
                return listaModulos;
            case SICAF:
                listaModulos.add(INVENTARIO);
                listaModulos.add(FACTURACION);
                listaModulos.add(CXC);
                listaModulos.add(CXP);
                listaModulos.add(CONTABILIDAD);
                listaModulos.add(BANCOS);
                listaModulos.add(ACTIFIJOS);
                listaModulos.add(PLANILLA);
                listaModulos.add(RRHH);
                listaModulos.add(PRESUPU);
                listaModulos.add(DISTRI);
                listaModulos.add(COSTOS);
                return listaModulos;
            case SICAF_HOSPITAL:
                listaModulos.add(INVENTARIO);
                listaModulos.add(FACTURACION);
                listaModulos.add(CXC);
                listaModulos.add(CXP);
                listaModulos.add(CONTABILIDAD);
                listaModulos.add(BANCOS);
                listaModulos.add(ACTIFIJOS);
                listaModulos.add(PLANILLA);
                listaModulos.add(RRHH);
                listaModulos.add(PRESUPU);
                listaModulos.add(DISTRI);
                listaModulos.add(COSTOS);
                listaModulos.add(HOSPITAL);
                return listaModulos;
            case CONTAIVA:
                listaModulos.add(COMERCIAL);
                listaModulos.add(CONTABILIDAD);
                return listaModulos;
            case FICCONTA:
                
                listaModulos.add(COMERCIAL);
                listaModulos.add(CONTABILIDAD);
                listaModulos.add(PLANILLA);
                listaModulos.add(COSTOS);
                return listaModulos;
            case ACADEMICO:
                listaModulos.add(ADMINIS);
                listaModulos.add(CALIFICACIONES);
                return listaModulos;
            default:
                listaModulos.add(SIN_DEFINIR);
                return listaModulos;
        }
    }

    public static Object devuelveValorParametro(Object valorParaBusqueda, int tipoADevolver) {
        List<Parametros> lista;
        switch (tipoADevolver) {
            case DEVUELVE_ID:
                //Pendiente completar
            case DEVUELVE_VALOR:
                lista = Arrays.asList(Parametros.values());
                return lista.stream().filter(valor -> valor.getId() == (int) valorParaBusqueda).map(valor -> valor.toString()).iterator().next();
            case DEVUELVE_PARAMETRO:
                lista = Arrays.asList(Parametros.values());
                return lista.stream().filter(valor -> valor.getId() == (int) valorParaBusqueda).iterator().next();
            default:
                return null;
        }
    }
}
