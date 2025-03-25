package ru.javabegin.semyon.lastProject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.semyon.lastProject.dto.MeasurementsDTO;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.services.MeasurementsService;
import ru.javabegin.semyon.lastProject.util.ControllerError;
import ru.javabegin.semyon.lastProject.util.ControllerException;
import ru.javabegin.semyon.lastProject.validations.MeasurementsValidation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementsValidation measurementsValidation;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementsValidation measurementsValidation) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementsValidation = measurementsValidation;
    }

    @GetMapping
    public List<MeasurementsDTO> getMeasurements(){
        return measurementsService.getMeasurements().stream().map(this::convertToMeasurementsDTO).collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public void getRainyDaysCount(){
        measurementsService.getMeasurementsWhereRainingTrue();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult){
        MeasurementsModel measurementsModel = convertToMeasurementsModel(measurementsDTO);
        measurementsValidation.validate(measurementsModel, bindingResult);
        if(bindingResult.hasErrors()){
            throw new ControllerException(bindingResult.getFieldError().getDefaultMessage());
        }
        measurementsService.save(measurementsModel);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ControllerError> error(ControllerException e){
        ControllerError controllerError = new ControllerError(e.getMessage(), new Date());
        return new ResponseEntity<>(controllerError, HttpStatus.BAD_REQUEST);
    }

    private MeasurementsModel convertToMeasurementsModel(MeasurementsDTO measurementsDTO){
        return modelMapper.map(measurementsDTO, MeasurementsModel.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(MeasurementsModel measurementsModel){
        return modelMapper.map(measurementsModel, MeasurementsDTO.class);
    }

}
