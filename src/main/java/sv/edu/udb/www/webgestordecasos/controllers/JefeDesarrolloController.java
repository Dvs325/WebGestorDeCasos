package sv.edu.udb.www.webgestordecasos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;
import sv.edu.udb.www.webgestordecasos.entities.CasosEntity;
import sv.edu.udb.www.webgestordecasos.model.AdministradorModel;
import sv.edu.udb.www.webgestordecasos.model.JefeDesarrolloModel;
import sv.edu.udb.www.webgestordecasos.model.JefaturaModel;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/jefeDesarrollo")
public class JefeDesarrolloController {

  @Autowired
  private JefeDesarrolloModel jefeDesarrolloModel;
  @Autowired
  private AdministradorModel administradorModel;

  @GetMapping("/menu")
  public ModelAndView menuAdministrador(@ModelAttribute("usuario") UsuarioEntity usuario) {
    ModelAndView mav = new ModelAndView("JefeDesarrollo/menuJefeDesarrollo");
    String nombreAreaUsuario = administradorModel.areaUsuario(usuario.getIdAreaFuncional());
    mav.addObject("casosPendientes", jefeDesarrolloModel.totalCasosPendientes("CON"));
    mav.addObject("totalCasosArea", jefeDesarrolloModel.totalCasos("CON"));
    mav.addObject("totalCasosProbadores", jefeDesarrolloModel.totalCasosProbadores("CON"));
    return mav;
  }




}//
