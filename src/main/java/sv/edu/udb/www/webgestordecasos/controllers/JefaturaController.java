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
        import sv.edu.udb.www.webgestordecasos.model.JefaturaModel;


@Controller
@SessionAttributes("usuario")
@RequestMapping("/jefatura")
public class JefaturaController {

    @Autowired
    private JefaturaModel jefaturaModel;

    @Autowired
    private AdministradorModel administradorModel;

    @GetMapping("/casos")
    public ModelAndView listarCasos(@ModelAttribute("usuario") UsuarioEntity usuario) {
        ModelAndView mav = new ModelAndView("jefatura/listarCasos");
            mav.addObject("listarCasos", jefaturaModel.obtenerCaso(String.valueOf(usuario.getIdUsuario())));
        return mav;
    }

    @PostMapping("/insertarCaso")
    public String insertarCaso(@ModelAttribute("usuario") UsuarioEntity usuario, @RequestParam("descripcion") String descripcion,
                               @RequestParam("idUsuario") String idUsuario){

            // Obtener la fecha actual y el formato
            java.sql.Date fechaIngreso = new java.sql.Date(new java.util.Date().getTime());
            int codigoAreaUsuario = usuario.getIdAreaFuncional();
            String area = administradorModel.areaUsuario(codigoAreaUsuario).toString();
            String a = area.substring(0, Math.min(area.length(), 3)).toUpperCase();
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyMMdd");
            String fechaFormato = fechaActual.format(formato);

            // Generar un ID de caso único
            int numeroAleatorio = (int) (Math.random() * 1000);
            String numeroFormateado = String.format("%03d", numeroAleatorio);
            String idCaso = a + fechaFormato + numeroFormateado;

            // Crear una nueva instancia de CasosEntity
            CasosEntity caso = new CasosEntity();
            caso.setIdCasos(idCaso); // Establecer el ID del caso
            caso.setFechaRegistro(fechaIngreso); // Establecer la fecha de registro
            caso.setDetalles(descripcion); // Asegúrate de que 'descripcion' esté disponible en el contexto
            caso.setIdUsuarioCaso(idUsuario); // Establecer el código del usuario que reporta el caso
            caso.setIdEstadoCaso(1);
            jefaturaModel.insertarCaso(caso);
            // Aquí puedes asignar valores adicionales si es necesario, como el estado, progreso, etc.


          /* // Llamar al método en el modelo para insertar la entidad CasosEntity
            if (jefaturaModel.insertarCaso(caso) > 0) {
                session.setAttribute("exito", "El caso fue ingresado de manera correcta.");
            } else {
                session.setAttribute("error", "Hubo un error al momento de ingresar el caso.");
            }*/

            return "redirect:/jefatura/casos";

    }


    /*

    @GetMapping("/jefatura/obtenerCaso")
    public String obtenerCaso(@RequestParam("id") String id, Model model) {
        try {
            CasoBeans casoBeans = jefaturaModel.obtenerDetallesCaso(id);
            model.addAttribute("idCaso", casoBeans.getIdCasos());
            model.addAttribute("descripcion", casoBeans.getDetallesCaso());
            return "jefatura/verCaso"; // Retorna la vista donde se verán los detalles del caso
        } catch (SQLException ex) {
            Logger.getLogger(JefaturaController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @PostMapping("/jefatura/reenvioCaso")
    public String reenvioCaso(@RequestParam("idCaso") String idCaso,
                              @RequestParam("descripcionCaso") String descripcionCaso,
                              HttpSession session) {
        try {
            if (jefaturaModel.actualizarCaso(idCaso, descripcionCaso) > 0) {
                session.setAttribute("exito", "El caso fue enviado a su revisión de manera correcta.");
            } else {
                session.setAttribute("error", "Hubo un error al momento de reenviar el caso.");
            }
            return "redirect:/jefatura/listarCasos";
        } catch (SQLException ex) {
            Logger.getLogger(JefaturaController.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }

    @GetMapping("/jefatura/verBitacoraCaso")
    public ModelAndView verBitacoraCaso(@RequestParam("id") String id) {
        ModelAndView mav = new ModelAndView("jefatura/bitacoras");
        try {
            mav.addObject("bitacoras", utilsModel.obtenerBitacorasCaso(id));
        } catch (SQLException ex) {
            Logger.getLogger(JefaturaController.class.getName()).log(Level.SEVERE, null, ex);
            mav.setViewName("error");
        }
        return mav;
    }
    */

}//
