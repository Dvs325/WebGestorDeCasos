package sv.edu.udb.www.webgestordecasos.controllers;

import org.springframework.stereotype.Controller;
import sv.edu.udb.www.webgestordecasos.entities.AreafuncionalEntity;
import sv.edu.udb.www.webgestordecasos.model.AdministradorModel;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;
//import sv.edu.udb.www.webgestordecasos.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@Controller
@RequestMapping("/administrador")
public class AdminController {

    @Autowired
    private AdministradorModel administradorModel;

    //private List<String> listaErrores = new ArrayList<>();

    @GetMapping("/menu")
    public ModelAndView menuAdministrador() {
        ModelAndView mav = new ModelAndView("administrador/menuAdministrador");
        mav.addObject("totalusuarios", administradorModel.totalUsuarios());
        mav.addObject("totalareas", administradorModel.totalAreasFuncionales());
        return mav;
    }


    @GetMapping("/usuarios")
    public ModelAndView listarUsuarios() {
        ModelAndView mav = new ModelAndView("administrador/usuarios/listarUsuarios");
        mav.addObject("listarUsuarios", administradorModel.listarUsuarios());
        return mav;
    }

    @PostMapping("/usuarios/editar")
    public ModelAndView obtenerUsuario(@RequestParam("codigoUsuario") String codigoUsuario,
                                       @RequestParam("idAreaFuncional") String idAreaFuncional) {
        ModelAndView mav = new ModelAndView();
            UsuarioEntity usuarioEntity = administradorModel.obtenerUsuario(codigoUsuario);
            if (usuarioEntity != null) {
                mav.setViewName("administrador/usuarios/editarUsuario");
                mav.addObject("codigoUsuario", codigoUsuario);
                mav.addObject("usuariomod", usuarioEntity);
                mav.addObject("tipoUsuario", administradorModel.listarTipoUsuario());
                mav.addObject("areaFuncional", administradorModel.listarAreasFuncionales());

            } else {
                mav.setViewName("error404");
            }

        return mav;
    }




    @PostMapping("/usuarios/actualizar")
    public ModelAndView actualizarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioActualizado) {
        administradorModel.actualizarUsuario(usuarioActualizado); // Llama al model para actualizar el usuario

        ModelAndView mav = new ModelAndView("redirect:/administrador/usuarios"); // Redirigir a la lista de usuarios
        return mav;
    }

    @GetMapping("/usuarios/nuevo")
    public ModelAndView nuevoUsuario() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("administrador/usuarios/nuevoUsuario");
        mav.addObject("tipoUsuario", administradorModel.listarTipoUsuario());
        mav.addObject("areaFuncional", administradorModel.listarAreasFuncionales());
        //mav.addObject("jefesAreaFuncional", administradorModel.obtenerJefeArea(idAreaFuncional));

        return mav;
    }

    @PostMapping("/usuarios/crear")
    public ModelAndView crearUsuario(@ModelAttribute("usuario") UsuarioEntity nuevoUsuario) {
        administradorModel.nuevoUsuario(nuevoUsuario); // Llama al model para registrar el usuario

        ModelAndView mav = new ModelAndView("redirect:/administrador/usuarios"); // Redirigir al form
        return mav;
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public ModelAndView eliminarUsuario(@PathVariable String id) {
        ModelAndView mav = new ModelAndView();

        // Llamada al modelo para eliminar el usuario
        administradorModel.eliminarUsuario(id);

        // Redirigir a la lista de usuarios
        mav.setViewName("redirect:/administrador/usuarios"); //
        return mav;
    }

    // areas:

    @GetMapping("/areas")
    public ModelAndView listarAreas() {
        ModelAndView mav = new ModelAndView("administrador/areas/listarAreas");
            mav.addObject("listarAreas", administradorModel.listarAreasFuncionales());
        return mav;
    }


    @PostMapping("/areas/nueva")
    public ModelAndView insertarArea(@ModelAttribute("area") AreafuncionalEntity nuevaArea) {
        administradorModel.insertAreaFuncional(nuevaArea); // Llama al model para actualizar el area

        ModelAndView mav = new ModelAndView("redirect:/administrador/areas"); // Redirigir listar areas
        return mav;
    }

    @PostMapping("/areas/actualizar")
    public ModelAndView actualizarArea(@ModelAttribute("area") AreafuncionalEntity areaActualizada) {
        administradorModel.actualizarAreaFuncional(areaActualizada); // Llama al model para actualizar el area

        ModelAndView mav = new ModelAndView("redirect:/administrador/areas"); // Redirigir a la lista de areas
        return mav;
    }

    @GetMapping("/areas/detalles/{id}")
    @ResponseBody //genera un json
    public AreafuncionalEntity obtenerArea(@PathVariable int id) {

            return administradorModel.obtenerAreaFuncional(id);
        }


}//
