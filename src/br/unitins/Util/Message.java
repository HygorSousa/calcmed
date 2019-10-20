package br.unitins.Util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.util.List;

public class Message {

    /**
     * Metodo responsavel por exibir determinada mensagem com base em sua severidade
     *
     * @param severity Severidade da mensagem
     * @param msg      Conteudo da mensagem
     */
    private static void show(Severity severity, String msg) {
        Message.getFacesContext().addMessage(null, new FacesMessage(severity, msg, null));
    }

    /**
     * Metodo responsavel por exibir determinada mensagem com base em sua severidade
     *
     * @param severity Severidade da mensagem
     * @param msg      Conteudo da mensagem
     */
    private static void show(Severity severity, String title, String msg) {
        Message.getFacesContext().addMessage(null, new FacesMessage(severity, title, msg));
    }

    /**
     * @return Faces Context
     */
    private static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Mensagem padrão de Informacoes
     *
     * @param msg Conteudo da mensagem
     */
    public static void info(String msg) {
        show(FacesMessage.SEVERITY_INFO, msg);
    }

    /**
     * Mensagem padrão de Alertas
     *
     * @param msg Conteudo da mensagem
     */
    public static void warning(String msg) {
        show(FacesMessage.SEVERITY_WARN, msg);
    }

    /**
     * Mensagem padrão de Erro
     *
     * @param msg Conteudo da mensagem
     */
    private static void error(String msg) {
        show(FacesMessage.SEVERITY_ERROR, msg);
    }

    /**
     * Mensagem padrão de Erro
     *
     * @param msg Conteudo da mensagem
     */
    public static void error(String title, String msg) {
        show(FacesMessage.SEVERITY_ERROR, title, msg);
    }

    /**
     * Mensagem padrão de Erro
     *
     * @param msg Conteudo da mensagem
     */
    public static void info(String title, String msg) {
        show(FacesMessage.SEVERITY_INFO, title, msg);
    }

    /**
     * Mensagem padrão de Erro
     *
     * @param msg Conteudo da mensagem
     */
    public static void warn(String title, String msg) {
        show(FacesMessage.SEVERITY_WARN, title, msg);
    }

    /**
     * Mensagem padrão de Fatalidade
     *
     * @param msg Conteudo da mensagem
     */
    private static void fatal(String msg) {
        show(FacesMessage.SEVERITY_FATAL, msg);
    }

    /**
     * Mostra lista de mensagens padrões de Informacoes
     *
     * @param listMessages Lista de mensagens
     */
    public static void showMessagesInfo(List<String> listMessages) {
        for (String message : listMessages) {
            info(message);
        }
    }

    /**
     * Mostra lista de mensagens padrões de Alertas
     *
     * @param listMessages Lista de mensagens
     */
    public static void showMessagesWarning(List<String> listMessages) {
        for (String message : listMessages) {
            warning(message);
        }
    }

    /**
     * Mostra lista de mensagens padrões de Erros
     *
     * @param listMessages Lista de mensagens
     */
    public static void showMessagesError(List<String> listMessages) {
        for (String message : listMessages) {
            error(message);
        }
    }

    /**
     * Mostra lista de mensagens padrões de Fatalidades
     *
     * @param listMessages Lista de mensagens
     */
    public static void showMessagesFatal(List<String> listMessages) {
        for (String message : listMessages) {
            fatal(message);
        }
    }
}
