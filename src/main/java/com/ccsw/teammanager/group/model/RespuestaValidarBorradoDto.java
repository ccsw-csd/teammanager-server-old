package com.ccsw.teammanager.group.model;

/**
 * TODO apastorm This type ...
 *
 */
public class RespuestaValidarBorradoDto {
  private String informacion;

  private boolean activo;

  private String titulo;

  /**
   * @return informacion
   */
  public String getInformacion() {

    return this.informacion;
  }

  /**
   * @param informacion new value of {@link #getinformacion}.
   */
  public void setInformacion(String informacion) {

    this.informacion = informacion;
  }

  /**
   * @return activo
   */
  public boolean isActivo() {

    return this.activo;
  }

  /**
   * @param activo new value of {@link #getactivo}.
   */
  public void setActivo(boolean activo) {

    this.activo = activo;
  }

  /**
   * @return titulo
   */
  public String getTitulo() {

    return this.titulo;
  }

  /**
   * @param titulo new value of {@link #gettitulo}.
   */
  public void setTitulo(String titulo) {

    this.titulo = titulo;
  }

}
