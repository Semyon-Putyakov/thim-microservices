package ru.javabegin.semyon.lastProject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.semyon.lastProject.dto.SensorDTO;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;
import ru.javabegin.semyon.lastProject.util.ControllerError;
import ru.javabegin.semyon.lastProject.util.ControllerException;
import ru.javabegin.semyon.lastProject.validations.SensorValidator;

import java.util.Date;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        SensorModel sensorModel = modelMapper.map(sensorDTO, SensorModel.class);
        sensorValidator.validate(sensorModel, bindingResult);
        if(bindingResult.hasErrors()){
            throw new ControllerException(bindingResult.getFieldError().getDefaultMessage());
        }

        sensorService.saveSensor(sensorModel);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<ControllerError> error(ControllerException e){
        ControllerError sensorError = new ControllerError(e.getMessage(), new Date());
        return new ResponseEntity<>(sensorError, HttpStatus.BAD_REQUEST);
    }

    private SensorModel convertSensorDTOToModel(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, SensorModel.class);
    }
}
