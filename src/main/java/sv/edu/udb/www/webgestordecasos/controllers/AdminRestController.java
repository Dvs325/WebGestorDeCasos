package sv.edu.udb.www.webgestordecasos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.www.webgestordecasos.dto.JefeAreaDTO;
import sv.edu.udb.www.webgestordecasos.service.JefeAreaService;

import java.util.List;

@RestController
@RequestMapping("/api/jefes")
public class AdminRestController {

    private final JefeAreaService jefeAreaService;

    // Constructor con inyección de dependencia
    @Autowired
    public AdminRestController(JefeAreaService jefeAreaService) {
        this.jefeAreaService = jefeAreaService;
    }

    @GetMapping(value = "/{areaFuncional}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<JefeAreaDTO>> obtenerJefeArea(@PathVariable String areaFuncional) {
        List<JefeAreaDTO> jefes = jefeAreaService.obtenerJefeArea(areaFuncional);

        if (jefes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(jefes, HttpStatus.OK);
    }


}//
