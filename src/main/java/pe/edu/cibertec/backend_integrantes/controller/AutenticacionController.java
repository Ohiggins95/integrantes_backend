package pe.edu.cibertec.backend_integrantes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.backend_integrantes.dto.IntegrantesResponseDTO;
import pe.edu.cibertec.backend_integrantes.dto.LoginResponseDTO;
import pe.edu.cibertec.backend_integrantes.dto.LoginResquestDTO;
import pe.edu.cibertec.backend_integrantes.service.AutenticacionService;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/")
    public LoginResponseDTO login(@RequestBody LoginResquestDTO loginResquestDTO) {


        try {
            // Thread.sleep(Duration.ofSeconds(60));
            String[] datosAlumnos = autenticacionService.validarAlumno(loginResquestDTO);
            if (datosAlumnos == null) {
                return new LoginResponseDTO("01", "Error: Usuario no Encontrado", "", "");
            }

            return  new LoginResponseDTO("00","",datosAlumnos[0],datosAlumnos[1]);
        } catch (Exception e) {
            return new LoginResponseDTO("99", "Ocurrio un Problema", "", "");
        }

    }

    @GetMapping("/")
    public List<IntegrantesResponseDTO> getIntegrantes() throws IOException {
        List<IntegrantesResponseDTO> integrantes = autenticacionService.listarIntegrantes();
        return integrantes;
    }

}