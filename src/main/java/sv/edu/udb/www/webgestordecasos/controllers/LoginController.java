package sv.edu.udb.www.webgestordecasos.controllers;


import sv.edu.udb.www.webgestordecasos.model.LoginModel;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("usuario")  // Define el atributo "usuario" que se almacenará en la sesión
public class LoginController {

    @Autowired
    private LoginModel loginModel;

    @GetMapping("/login")
    public ModelAndView mostrarFormulario(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // Obtén el usuario de la sesión si está disponible
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

        // Si ya hay un usuario en la sesión, redirige según su rol
        if (usuario != null) {
            mav.setViewName(redirigirSegunRol(usuario.getIdTipoUsuario()));
        } else {
            mav.setViewName("login");  // Muestra el formulario de login si no está autenticado
        }

        return mav;
    }

    @PostMapping("/login/verificarUsuario")  // Verifica las credenciales del usuario
    public ModelAndView verificarUsuario(@RequestParam("codigoUsuario") String codigoUsuario,
                                         @RequestParam("contraseniaUsuario") String contraseniaUsuario,
                                         ModelAndView mav,
                                         HttpSession session) {
        // Autenticar al usuario
        UsuarioEntity usuario = loginModel.verificarUsuario(codigoUsuario, contraseniaUsuario);

        if (usuario != null) {
            // Almacena el usuario en la sesión
            session.setAttribute("usuario", usuario);
            // Redirige según el rol del usuario
            mav.setViewName(redirigirSegunRol(usuario.getIdTipoUsuario()));
        } else {
            // Usuario o contraseña incorrectos
            mav.setViewName("login");
            mav.addObject("error", "Usuario o contraseña incorrectos");
        }

        return mav;
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus, HttpSession session) {
        sessionStatus.setComplete();  // Marca la sesión como completada
        session.invalidate();  // Invalida la sesión
        return "redirect:/login";  // Redirige a la página de login
    }

    // Método para redirigir según el rol del usuario
    private String redirigirSegunRol(int idTipoUsuario) {
        switch (idTipoUsuario) {
            case 1:
                return "redirect:/administrador/menu";  // Administrador
            case 2:
                return "redirect:/jefeDesarrollo/menu";      // Jefe de desarrollo
            case 3:
                return "redirect:/jefatura/casos";           // Jefatura
            case 4:
                return "redirect:/programador";        // Programador
            case 5:
                return "redirect:/probador";           // Probador
            default:
                return "login";  // En caso de que el tipo de usuario no sea válido
        }
    }
}
